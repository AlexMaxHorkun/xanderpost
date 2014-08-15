package xanderpost.service;

import org.springframework.transaction.annotation.Transactional;
import xanderpost.entity.Post;
import xanderpost.entity.PostRating;
import xanderpost.repository.PostDaoInterface;
import xanderpost.repository.PostRatingDaoInterface;

import java.util.Collection;

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
    public Collection<Post> findAll(boolean withRatings) {
        return postDao.findAll((withRatings)? PostDaoInterface.FetchMode.FETCH_WITH_RATINGS : PostDaoInterface.FetchMode.FETCH_PLAIN);
    }

    @Transactional(readOnly = true)
    public Collection<Post> findAll() {
        return findAll(false);
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
        postDao.save(p);
    }

    @Transactional
    public void persistRate(PostRating rating) {
        postRatingDao.persist(rating);
    }
}
