package xanderpost.repository;

import org.springframework.stereotype.Repository;
import xanderpost.security.UserRole;

import java.util.List;

@Repository
public interface UserRoleDaoInterface {
    public List<UserRole> findByRoles(String[] roles);

    public void persist(UserRole r);

    public void remove(UserRole r);
}
