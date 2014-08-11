package xanderpost.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import xanderpost.entity.User;

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
        Session orm = getSessionFactory().getCurrentSession();
        return (User) orm.get(User.class, id);
    }

    public User findByEmail(String e) {
        Session orm = getSessionFactory().getCurrentSession();
        return (User) orm.createCriteria(User.class).add(Restrictions.eq("email", e)).uniqueResult();
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
