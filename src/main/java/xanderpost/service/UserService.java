package xanderpost.service;

import xanderpost.entity.User;
import xanderpost.repository.UserDaoInterface;
import xanderpost.repository.UserRoleDaoInterface;
import xanderpost.security.UserRole;

import javax.transaction.Transactional;
import java.util.List;

public class UserService {
    private UserDaoInterface userDao;

    private UserRoleDaoInterface userRoleDao;

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

    @Transactional
    public void persist(User u) {
        userDao.persist(u);
    }

    @Transactional
    public void persist(User u, String[] roles) {
        List<UserRole> roleList = userRoleDao.findByRoles(roles);
        u.setAuthorities(roleList);
        persist(u);
    }
}
