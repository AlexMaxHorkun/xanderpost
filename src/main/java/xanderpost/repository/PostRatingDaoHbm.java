package xanderpost.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import xanderpost.entity.PostRating;

public class PostRatingDaoHbm implements PostRatingDaoInterface {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void persist(PostRating rating) {
        Session orm = sessionFactory.getCurrentSession();
        orm.persist(rating);
        orm.flush();
    }
}
