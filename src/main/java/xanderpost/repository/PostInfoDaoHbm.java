package xanderpost.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import xanderpost.entity.readonly.PostInfo;

import java.util.List;

@Repository
public class PostInfoDaoHbm implements PostInfoDaoInterface {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<PostInfo> findAll(int limit, int offset) {
        Session orm = sessionFactory.getCurrentSession();
        Criteria query = orm.createCriteria(PostInfo.class);
        if (limit > 0) {
            query.setMaxResults(limit);
        }
        if (offset > 0) {
            query.setFirstResult(offset);
        }
        return query.list();
    }
}
