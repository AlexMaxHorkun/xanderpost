package xanderpost.service;

import org.springframework.transaction.annotation.Transactional;
import xanderpost.entity.Post;
import xanderpost.entity.PostRating;
import xanderpost.entity.readonly.PostInfo;
import xanderpost.repository.PostDaoInterface;
import xanderpost.repository.PostInfoDaoInterface;
import xanderpost.repository.PostRatingDaoInterface;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class PostService {
    private PostDaoInterface postDao;
    private PostRatingDaoInterface postRatingDao;
    private PostInfoDaoInterface postInfoDao;

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

    public PostInfoDaoInterface getPostInfoDao() {
        return postInfoDao;
    }

    public void setPostInfoDao(PostInfoDaoInterface postInfoDao) {
        this.postInfoDao = postInfoDao;
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
        return postDao.findAll((withRatings) ? PostDaoInterface.FetchMode.FETCH_WITH_RATINGS : PostDaoInterface.FetchMode.FETCH_PLAIN, limit, offset);
    }

    @Transactional(readOnly = true)
    public Collection<Post> findAll() {
        return findAll(false, 0, 0);
    }

    @Transactional(readOnly = true)
    public Collection<Post> findAll(boolean withRatings, int limit) {
        return postDao.findAll((withRatings) ? PostDaoInterface.FetchMode.FETCH_WITH_RATINGS : PostDaoInterface.FetchMode.FETCH_PLAIN, limit, 0);
    }

    @Transactional(readOnly = true)
    public List<PostInfo> findAllInfo(int limit, int offset) {
        return postInfoDao.findAll(limit, offset);
    }

    @Transactional(readOnly = true)
    public List<PostInfo> findAllInfo(int limit) {
        return findAllInfo(limit, 0);
    }

    @Transactional(readOnly = true)
    public List<PostInfo> findAllInfo() {
        return findAllInfo(0, 0);
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
