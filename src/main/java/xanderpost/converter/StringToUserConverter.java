package xanderpost.converter;

import org.springframework.core.convert.converter.Converter;
import xanderpost.entity.User;
import xanderpost.service.UserService;

public class StringToUserConverter implements Converter<String, User> {
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User convert(String id) {
        return userService.find(Long.parseLong(id));
    }
}
