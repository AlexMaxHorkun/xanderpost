package xanderpost.repository;

import org.springframework.stereotype.Repository;
import xanderpost.entity.User;

@Repository
public interface UserDaoInterface {
    public enum FetchMode {
        FETCH_PLAIN, FETCH_WITH_RATINGS
    }

    public User find(long id);

    public User find(long id, FetchMode fetchMode);

    public User findByEmail(String e);

    public void persist(User u);

    public void save(User u);

    public void remove(User u);
}
