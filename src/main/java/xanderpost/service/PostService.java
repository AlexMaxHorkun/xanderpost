package xanderpost.service;

import org.springframework.transaction.annotation.Transactional;
import xanderpost.entity.Post;
import xanderpost.repository.PostDaoInterface;

import java.util.Collection;

public class PostService {
    private PostDaoInterface postDao;

    public PostDaoInterface getPostDao() {
        return postDao;
    }

    public void setPostDao(PostDaoInterface postDao) {
        this.postDao = postDao;
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
    public Collection<Post> findAll() {
        return postDao.findAll();
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
}
