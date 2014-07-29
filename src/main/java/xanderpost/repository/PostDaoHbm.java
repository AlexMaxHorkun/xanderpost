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

    public Post find(long id){
        Session orm=getSessionFactory().openSession();
        Post post=(Post)orm.get(Post.class, id);
        orm.close();
        return post;
    }

    public Collection<Post> findByTitle(String t){
        Session orm=sessionFactory.openSession();
        Collection<Post> posts=(Collection<Post>)orm.createCriteria(Post.class).add(Restrictions.eq("title", t)).list();
        orm.close();
        return posts;
    }

    public Collection<Post> findAll(){
        Session orm=sessionFactory.openSession();
        Collection<Post> posts=(Collection<Post>)orm.createCriteria(Post.class).list();
        orm.close();
        return posts;
    }

    public void persist(Post p){
        Session orm=sessionFactory.openSession();
        orm.persist(p);
        orm.flush();
        orm.close();
    }

    public void delete(Post p){
        Session orm=sessionFactory.openSession();
        orm.delete(p);
        orm.flush();
        orm.close();
    }

    public void save(Post p){
        Session orm=sessionFactory.openSession();
        orm.update(p);
        orm.flush();
        orm.close();
    }
}
