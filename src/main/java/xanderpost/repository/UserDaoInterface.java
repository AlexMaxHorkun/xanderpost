package xanderpost.repository;

import xanderpost.entity.User;

public interface UserDaoInterface {
    public User find(long id);

    public User findByEmail(String e);
}
