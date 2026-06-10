package com.estudos.estoques.user.api;

import com.estudos.estoques.user.application.CreateUserCommand;
import com.estudos.estoques.user.application.CreateUserUseCase;
import com.estudos.estoques.user.application.UserLoginCommand;
import com.estudos.estoques.user.application.UserLoginUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

  private final CreateUserUseCase createUserUseCase;
  private final UserLoginUseCase userLoginUseCase;

  public UserController(
    CreateUserUseCase createUserUseCase,
    UserLoginUseCase userLoginUseCase
  ) {
    this.createUserUseCase = createUserUseCase;
    this.userLoginUseCase = userLoginUseCase;
  }

  @PostMapping("create")
  public ResponseEntity<CreateUserResponse> create(
    @RequestBody CreateUserRequest request
  ) {
    try {
      CreateUserCommand command = new CreateUserCommand(
        request.getName(),
        request.getEmail(),
        request.getRawPassword()
      );
      CreateUserResponse response = CreateUserResponse.fromDomain(
        createUserUseCase.execute(command)
      );
      return new ResponseEntity<>(response, HttpStatus.CREATED);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @PostMapping("login")
  public ResponseEntity<UserLoginResponse> login(
    @RequestBody UserLoginRequest request
  ) {
    try {
      UserLoginCommand command = new UserLoginCommand(
        request.getEmail(),
        request.getRawPassword()
      );
      UserLoginResponse response = UserLoginResponse.fromDomain(
        userLoginUseCase.execute(command)
      );
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }

}
