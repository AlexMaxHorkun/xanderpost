package xanderpost.repository;

import org.hibernate.SessionFactory;
import xanderpost.entity.readonly.UserInfo;

public class UserInfoDaoHbm implements UserInfoDaoInterface {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserInfo find(long id) {
        return (UserInfo) sessionFactory.getCurrentSession().get(UserInfo.class, id);
    }
}
