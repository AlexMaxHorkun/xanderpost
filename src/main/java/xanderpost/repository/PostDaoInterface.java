package xanderpost.repository;

import org.springframework.stereotype.Repository;
import xanderpost.entity.Post;

import java.util.Collection;

@Repository
public interface PostDaoInterface {

    public enum FetchMode {
        FETCH_WITH_RATINGS, FETCH_PLAIN
    }

    public Post find(long id);

    public Collection<Post> findByTitle(String t);

    public Collection<Post> findAll(FetchMode fetchMode, int limit, int offset);

    public void persist(Post p);

    public void delete(Post p);

    public void save(Post p);

    public Post fetchRatings(Post p);
}
