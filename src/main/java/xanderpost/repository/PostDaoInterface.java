package xanderpost.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xanderpost.entity.Post;

import java.util.Collection;

@Repository
public interface PostDaoInterface {

    public Post find(long id);

    public Collection<Post> findByTitle(String t);

    @Transactional(readOnly = true)
    public Collection<Post> findAll();

    public void persist(Post p);

    public void delete(Post p);

    public void save(Post p);
}
