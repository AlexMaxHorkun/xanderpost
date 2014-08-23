package xanderpost.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import xanderpost.entity.User;

import java.util.List;

@Repository
public class UserDaoHbm implements UserDaoInterface {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User find(long id) {
        return find(id, FetchMode.FETCH_PLAIN);
    }

    public User find(long id, FetchMode fetchMode) {
        Session orm = getSessionFactory().getCurrentSession();
        User user = null;
        switch (fetchMode) {
            case FETCH_PLAIN:
                user = (User) orm.get(User.class, id);
                break;
            case FETCH_WITH_RATINGS:
                user = (User) orm.createQuery("from User u left join fetch u.ratings where id=:id").setParameter("id", id).uniqueResult();
        }
        return user;
    }

    public User findByEmail(String e) {
        Session orm = getSessionFactory().getCurrentSession();
        return (User) orm.createCriteria(User.class).add(Restrictions.eq("email", e)).uniqueResult();
    }

    public List<User> findAll(int limit, int offset) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User u");
        if (limit > 0) {
            query.setMaxResults(limit);
        }
        if (offset > 0) {
            query.setFirstResult(offset);
        }
        return query.list();
    }

    public void persist(User u) {
        Session orm = sessionFactory.getCurrentSession();
        orm.persist(u);
        orm.flush();
    }

    public void save(User u) {
        Session orm = sessionFactory.getCurrentSession();
        orm.update(u);
        orm.flush();
    }

    public void remove(User u) {
        Session orm = sessionFactory.getCurrentSession();
        orm.delete(u);
        orm.flush();
    }
}
