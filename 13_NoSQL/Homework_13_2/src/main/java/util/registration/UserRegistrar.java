package util.registration;

import lombok.AllArgsConstructor;
import model.User;
import service.Service;

@AllArgsConstructor
public class UserRegistrar implements RegisterHandler<User> {

    Service<String, User> userService;

    @Override
    public void register(User user) {
        userService.add(user);
    }

}
