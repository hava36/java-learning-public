package main.config;

import main.dto.UserDto;
import main.mapper.TaskMapper;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDto userDto = userService.findByLogin(login);
        return new CustomUserDetails(userDto);
    }

}
