package xanderpost.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import xanderpost.entity.Post;
import xanderpost.entity.User;
import xanderpost.entity.readonly.UserInfo;
import xanderpost.repository.UserDaoInterface;
import xanderpost.repository.UserInfoDaoInterface;
import xanderpost.repository.UserRoleDaoInterface;
import xanderpost.security.UserRole;

import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

public class UserService {
    private UserDaoInterface userDao;

    private UserRoleDaoInterface userRoleDao;

    private PasswordEncoder passwordEncoder;

    private String[] stdRoles;

    private UserInfoDaoInterface userInfoDao;

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

    public UserInfoDaoInterface getUserInfoDao() {
        return userInfoDao;
    }

    public void setUserInfoDao(UserInfoDaoInterface userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    protected void fetchUser(User u, FetchMode fetchMode) {
        if (fetchMode == FetchMode.FETCH_WITH_ROLES) {
            u.getAuthorities();
        }
    }

    @Transactional
    public void persist(User u) {
        if (u.getRoles() == null || u.getRoles().isEmpty()) {
            persist(u, stdRoles);
        } else {
            Logger log = Logger.getLogger(getClass().toString());
            log.info("new User's [" + u.getEmail() + "] password = " + u.getPassword());
            u.setPassword(passwordEncoder.encode(u.getPassword()));
            log.info("new User's [" + u.getEmail() + "] encoded password = " + u.getPassword());
            userDao.persist(u);
        }
    }

    @Transactional
    public void persist(User u, String[] roles) {
        List<UserRole> roleList = userRoleDao.findByRoles(roles);
        u.setRoles(new HashSet<UserRole>(roleList));
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

    @Transactional(readOnly = true)
    public UserInfo findUserInfo(long id) {
        return userInfoDao.find(id);
    }

    @Transactional(readOnly = true)
    public UserInfo findUserInfo(User u) {
        return findUserInfo(u.getId());
    }

    public boolean isEditableBy(User u, Post p) {
        return (u.equals(p.getAuthor()) || u.hasRole("ROLE_ADMIN"));
    }

    @Transactional
    public void persistRole(UserRole role) {
        userRoleDao.persist(role);
    }
}
