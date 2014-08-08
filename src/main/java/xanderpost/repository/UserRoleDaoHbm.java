package xanderpost.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import xanderpost.security.UserRole;

import java.util.List;

public class UserRoleDaoHbm implements UserRoleDaoInterface {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<UserRole> findByRoles(String[] roles) {
        Session orm = sessionFactory.getCurrentSession();
        return orm.createCriteria(UserRole.class).add(Restrictions.in("role", roles)).list();
    }
}
