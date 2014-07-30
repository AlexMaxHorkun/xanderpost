package xanderpost.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import xanderpost.entity.User;
import xanderpost.repository.UserDaoInterface;

public class UserProvider implements UserDetailsService{
    private UserDaoInterface userDao;

    public UserDaoInterface getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDaoInterface userDao) {
        this.userDao = userDao;
    }

    public User loadUserByUsername(String n){
        return getUserDao().findByEmail(n);
    }
}
