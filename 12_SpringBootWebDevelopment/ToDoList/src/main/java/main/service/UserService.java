package main.service;

import main.controller.auth.AuthRequest;
import main.controller.auth.AuthResponse;
import main.dto.UserDto;

public interface UserService extends Service<UserDto> {

    UserDto findByLogin(String login);

    UserDto findByLoginAndPassword(String login, String password);

    AuthResponse auth(AuthRequest request);

}
