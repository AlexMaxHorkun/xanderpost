package xanderpost.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import xanderpost.entity.User;
import xanderpost.service.UserService;

public class UserProvider implements UserDetailsService {
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User loadUserByUsername(String n) {
        return getUserService().findByEmail(n);
    }
}
