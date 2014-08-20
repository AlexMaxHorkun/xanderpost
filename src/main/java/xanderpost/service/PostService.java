package xanderpost.service;

import org.springframework.transaction.annotation.Transactional;
import xanderpost.entity.Post;
import xanderpost.entity.PostRating;
import xanderpost.repository.PostDaoInterface;
import xanderpost.repository.PostRatingDaoInterface;

import java.util.Collection;
import java.util.Date;

public class PostService {
    private PostDaoInterface postDao;
    private PostRatingDaoInterface postRatingDao;

    public PostDaoInterface getPostDao() {
        return postDao;
    }

    public void setPostDao(PostDaoInterface postDao) {
        this.postDao = postDao;
    }

    public PostRatingDaoInterface getPostRatingDao() {
        return postRatingDao;
    }

    public void setPostRatingDao(PostRatingDaoInterface postRatingDao) {
        this.postRatingDao = postRatingDao;
    }

    @Transactional(readOnly = true)
    public Post find(long id) {
        return postDao.find(id);
    }

    @Transactional(readOnly = true)
    public Collection<Post> findByTitle(String t) {
        return postDao.findByTitle(t);
    }

    @Transactional(readOnly = true)
    public Collection<Post> findAll(boolean withRatings, int limit, int offset) {
        return postDao.findAll((withRatings) ? PostDaoInterface.FetchMode.FETCH_WITH_AVG_RATING : PostDaoInterface.FetchMode.FETCH_PLAIN, limit, offset);
    }

    @Transactional(readOnly = true)
    public Collection<Post> findAll() {
        return findAll(false, 0, 0);
    }

    @Transactional(readOnly = true)
    public Collection<Post> findAll(boolean withRatings, int limit) {
        return postDao.findAll((withRatings) ? PostDaoInterface.FetchMode.FETCH_WITH_AVG_RATING : PostDaoInterface.FetchMode.FETCH_PLAIN, limit, 0);
    }

    @Transactional
    public void persist(Post p) {
        postDao.persist(p);
    }

    @Transactional
    public void delete(Post p) {
        postDao.delete(p);
    }

    @Transactional
    public void save(Post p) {
        p.setLastEdited(new Date());
        postDao.save(p);
    }

    @Transactional
    public void persistRate(PostRating rating) {
        postRatingDao.persist(rating);
    }

    @Transactional(readOnly = true)
    public Post fetchRatings(Post p) {
        return postDao.fetchRatings(p);
    }
}
