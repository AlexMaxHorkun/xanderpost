package xanderpost.repository;

import xanderpost.security.UserRole;

import java.util.List;

public interface UserRoleDaoInterface {
    public List<UserRole> findByRoles(String[] roles);
}
