package xanderpost.repository;

import org.springframework.stereotype.Repository;
import xanderpost.entity.User;

@Repository
public interface UserDaoInterface {
    public User find(long id);

    public User findByEmail(String e);

    public void persist(User u);
}
