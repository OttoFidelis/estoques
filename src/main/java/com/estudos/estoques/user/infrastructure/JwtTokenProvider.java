package com.estudos.estoques.user.infrastructure;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import com.estudos.estoques.user.domain.TokenProvider;
import com.estudos.estoques.user.domain.User;
import com.estudos.estoques.user.domain.UserId;

import io.github.cdimascio.dotenv.Dotenv;

@Component
public class JwtTokenProvider implements TokenProvider {

    private static final String HMAC_ALGO = Dotenv.load().get("HMAC_ALGO");
    private static final String SECRET = Dotenv.load().get("SECRET");
    private static final String HEADER_JSON = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
    private Long expirationSeconds = 3600L;

    public JwtTokenProvider() {
    }

    @Value("${jwt.expiration-seconds:3600}")
    public void setExpirationSeconds(Long expirationSeconds) {
        this.expirationSeconds = expirationSeconds;
    }

    @Override
    public String generateToken(User user) {
        try {
            long iat = Instant.now().getEpochSecond();
            long exp = iat + expirationSeconds;

            StringBuilder payload = new StringBuilder();
            payload.append("{");
            payload.append("\"sub\":\"").append(escapeJson(String.valueOf(user.getId().value()))).append("\"");
            payload.append(",\"iat\":").append(iat);
            payload.append(",\"exp\":").append(exp);
            if (user.getRole() != null) {
                payload.append(",\"role\":\"").append(escapeJson(user.getRole().toString())).append("\"");
            }
            payload.append("}");

            String headerB64 = base64Url(HEADER_JSON.getBytes(StandardCharsets.UTF_8));
            String payloadB64 = base64Url(payload.toString().getBytes(StandardCharsets.UTF_8));
            String signingInput = headerB64 + "." + payloadB64;
            String signature = base64Url(hmacSha256(signingInput));
            return signingInput + "." + signature;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean validateToken(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) return false;
            String signingInput = parts[0] + "." + parts[1];
            String expectedSig = base64Url(hmacSha256(signingInput));
            if (!constantTimeEquals(expectedSig, parts[2])) return false;

            String payloadJson = new String(base64UrlDecode(parts[1]), StandardCharsets.UTF_8);
            Long exp = extractLongField(payloadJson, "exp");
            if (exp == null) return false;
            long now = Instant.now().getEpochSecond();
            return now < exp;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public UserId getUserIdFromToken(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) return null;
            String payloadJson = new String(base64UrlDecode(parts[1]), StandardCharsets.UTF_8);
            String sub = extractStringField(payloadJson, "sub");
            if (sub == null) return null;
            return new UserId(Long.parseLong(sub));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Long getExpirationSeconds() {
        return expirationSeconds;
    }

    private byte[] hmacSha256(String data) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(HMAC_ALGO);
        mac.init(new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), HMAC_ALGO));
        return mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
    }

    private String base64Url(byte[] bytes) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    private byte[] base64UrlDecode(String str) {
        return Base64.getUrlDecoder().decode(str);
    }

    private String extractStringField(String json, String field) {
        Matcher matcher = Pattern.compile("\\\"" + Pattern.quote(field) + "\\\"\\s*:\\s*\\\"([^\\\"]*)\\\"").matcher(json);
        return matcher.find() ? matcher.group(1) : null;
    }

    private Long extractLongField(String json, String field) {
        Matcher matcher = Pattern.compile("\\\"" + Pattern.quote(field) + "\\\"\\s*:\\s*(\\d+)").matcher(json);
        return matcher.find() ? Long.valueOf(matcher.group(1)) : null;
    }

    private String escapeJson(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private boolean constantTimeEquals(String a, String b) {
        if (a.length() != b.length()) return false;
        int result = 0;
        for (int i = 0; i < a.length(); i++) result |= a.charAt(i) ^ b.charAt(i);
        return result == 0;
    }
}
