package xanderpost.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import xanderpost.entity.Post;

import java.util.Collection;

@Repository
public class PostDaoHbm implements PostDaoInterface {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Post find(long id) {
        Session orm = getSessionFactory().getCurrentSession();
        return (Post) orm.get(Post.class, id);
    }

    public Collection<Post> findByTitle(String t) {
        Session orm = sessionFactory.getCurrentSession();
        return (Collection<Post>) orm.createCriteria(Post.class).add(Restrictions.eq("title", t)).list();
    }

    public Collection<Post> findAll(FetchMode fetchMode) {
        Session orm = sessionFactory.getCurrentSession();
        switch (fetchMode) {
            case FETCH_PLAIN:
                return orm.createQuery("from Post post").list();
            case FETCH_WITH_RATINGS:
                return orm.createQuery("from Post post join fetch post.ratings").list();
            default:
                return null;
        }
    }

    public Collection<Post> findAll() {
        return findAll(FetchMode.FETCH_PLAIN);
    }

    public void persist(Post p) {
        Session orm = sessionFactory.getCurrentSession();
        orm.persist(p);
        orm.flush();
    }

    public void delete(Post p) {
        Session orm = sessionFactory.getCurrentSession();
        orm.delete(p);
        orm.flush();
    }

    public void save(Post p) {
        Session orm = sessionFactory.getCurrentSession();
        orm.update(p);
        orm.flush();
    }
}
