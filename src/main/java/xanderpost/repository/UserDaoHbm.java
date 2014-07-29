package xanderpost.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import xanderpost.entity.User;

public class UserDaoHbm implements UserDaoInterface{
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User find(long id){
        Session orm=getSessionFactory().openSession();
        User u=(User)orm.get(User.class,id);
        orm.close();
        return u;
    }

    public User findByEmail(String e){
        Session orm=getSessionFactory().openSession();
        User u=(User)orm.createCriteria(User.class).add(Restrictions.eq("email",e)).uniqueResult();
        orm.close();
        return u;
    }
}