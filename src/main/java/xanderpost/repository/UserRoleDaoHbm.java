package xanderpost.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import xanderpost.security.UserRole;

import java.util.List;

@Repository
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

    public void persist(UserRole r) {
        sessionFactory.getCurrentSession().persist(r);
    }

    public void remove(UserRole r) {
        sessionFactory.getCurrentSession().delete(r);
    }
}
