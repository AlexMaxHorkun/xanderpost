package xanderpost.entity;

import java.util.Collection;

public interface PostDaoInterface {
    public Post find(long id);

    public Collection<Post> findByTitle(String t);

    public Collection<Post> findAll();
}
