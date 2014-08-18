package xanderpost.repository;

import org.springframework.stereotype.Repository;
import xanderpost.entity.Post;

import java.util.Collection;

@Repository
public interface PostDaoInterface {

    public enum FetchMode {
        FETCH_WITH_RATINGS, FETCH_PLAIN, FETCH_WITH_AVG_RATING
    }

    public Post find(long id);

    public Collection<Post> findByTitle(String t);

    public Collection<Post> findAll(FetchMode fetchMode);

    public Collection<Post> findAll();

    public void persist(Post p);

    public void delete(Post p);

    public void save(Post p);
}
