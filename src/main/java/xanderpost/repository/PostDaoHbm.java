package xanderpost.repository;

import org.hibernate.Query;
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

    public Collection<Post> findAll(FetchMode fetchMode, int limit, int offset) {
        Session orm = sessionFactory.getCurrentSession();
        Query query = null;
        switch (fetchMode) {
            case FETCH_PLAIN:
                query = orm.createQuery("from Post post");
                break;
            case FETCH_WITH_RATINGS:
                query = orm.createQuery("from Post post join fetch post.ratings");
            case FETCH_WITH_AVG_RATING:
                query = orm.createQuery("select p.id, p.title, p.text, p.author, p.created, p.lastEdited, avg(r.rate) as avgRating from Post p left join p.ratings as r group by p.id");
        }
        if (query != null) {
            if (limit > 0) {
                query.setMaxResults(limit);
            }
            if (offset > 0) {
                query.setFirstResult(offset);
            }
            return query.list();
        } else {
            return null;
        }
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

    public Post fetchRatings(Post p) {
        Session orm = sessionFactory.getCurrentSession();
        Post p2 = (Post) orm.merge(p);
        p2.getRatings().size();
        return p2;
    }
}
