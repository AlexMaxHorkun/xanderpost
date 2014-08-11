package xanderpost.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import xanderpost.entity.User;
import xanderpost.repository.UserDaoInterface;
import xanderpost.repository.UserRoleDaoInterface;
import xanderpost.security.UserRole;

import java.util.List;

public class UserService {
    private UserDaoInterface userDao;

    private UserRoleDaoInterface userRoleDao;

    private PasswordEncoder passwordEncoder;

    private String[] stdRoles;

    public UserDaoInterface getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDaoInterface userDao) {
        this.userDao = userDao;
    }

    public UserRoleDaoInterface getUserRoleDao() {
        return userRoleDao;
    }

    public void setUserRoleDao(UserRoleDaoInterface userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String[] getStdRoles() {
        return stdRoles;
    }

    public void setStdRoles(String[] stdRoles) {
        this.stdRoles = stdRoles;
    }

    @Transactional
    public void persist(User u) {
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        if (u.getAuthorities() == null) {
            persist(u, stdRoles);
        }
        userDao.persist(u);
    }

    @Transactional
    public void persist(User u, String[] roles) {
        List<UserRole> roleList = userRoleDao.findByRoles(roles);
        u.setAuthorities(roleList);
        persist(u);
    }

    @Transactional(readOnly = true)
    public User find(long id) {
        return userDao.find(id);
    }

    @Transactional(readOnly = true)
    public User findByEmail(String e) {
        return userDao.findByEmail(e);
    }

    @Transactional
    public void remove(User u) {
        u.setEnabled(false);
        userDao.save(u);
    }

    @Transactional
    public void remove(User u, boolean forReal) {
        if (forReal) {
            userDao.remove(u);
        } else {
            remove(u);
        }
    }
}
