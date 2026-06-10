package com.estudos.estoques.user.application;

import com.estudos.estoques.user.domain.PasswordHasher;
import com.estudos.estoques.user.domain.UserEmail;
import com.estudos.estoques.user.domain.UserId;
import com.estudos.estoques.user.domain.UserName;
import com.estudos.estoques.user.domain.UserRawPassword;
import com.estudos.estoques.user.domain.UserRole;
import com.estudos.estoques.user.domain.User;
import com.estudos.estoques.user.infrastructure.UserMapper;
import com.estudos.estoques.user.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

  private final UserRepository userRepository;
  private final PasswordHasher passwordHasher;

  public CreateUserUseCase(
    UserRepository userRepository,
    PasswordHasher passwordHasher
  ) {
    this.userRepository = userRepository;
    this.passwordHasher = passwordHasher;
  }

  public User execute(CreateUserCommand command) {
    var hashedPassword = passwordHasher.hashPassword(
      new UserRawPassword(command.getRawPassword())
    );
    var user = new User(
      new UserId(null),
      new UserName(command.getName()),
      new UserEmail(command.getEmail()),
      hashedPassword,
      UserRole.EMPLOYEE
    );
    userRepository.save(UserMapper.toEntity(user));
    return user;
  }
}
