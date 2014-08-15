package xanderpost.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import xanderpost.entity.Post;
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

    public enum FetchMode {
        FETCH_PLAIN, FETCH_WITH_ROLES
    }

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

    protected void fetchUser(User u, FetchMode fetchMode) {
        if (fetchMode == FetchMode.FETCH_WITH_ROLES) {
            u.getAuthorities();
        }
    }

    @Transactional
    public void persist(User u) {
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        if (u.getRoles() == null || u.getRoles().isEmpty()) {
            persist(u, stdRoles);
        }
        userDao.persist(u);
    }

    @Transactional
    public void persist(User u, String[] roles) {
        List<UserRole> roleList = userRoleDao.findByRoles(roles);
        u.setRoles(roleList);
        persist(u);
    }

    @Transactional(readOnly = true)
    public User find(long id) {
        User u = userDao.find(id);
        fetchUser(u, FetchMode.FETCH_WITH_ROLES);
        return u;
    }

    @Transactional(readOnly = true)
    public User findByEmail(String e) {
        User u = userDao.findByEmail(e);
        fetchUser(u, FetchMode.FETCH_WITH_ROLES);
        return u;
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

    @Transactional
    public void save(User u) {
        userDao.save(u);
    }

    @Transactional
    public void save(User u, boolean encodePasswordFirst) {
        if (encodePasswordFirst) {
            u.setPassword(passwordEncoder.encode(u.getPassword()));
        }
        save(u);
    }

    public boolean isEditableBy(User u, Post p) {
        return (u.equals(p.getAuthor()) || u.hasRole("ROLE_ADMIN"));
    }
}
