package xanderpost.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "PostView", uniqueConstraints = @UniqueConstraint(columnNames = {"post", "user"}))
public class PostView {
    private Post post;
    private User user;
    private Date time=new Date();
    private long id;

    public PostView(){}

    public PostView(Post p, User u){
        setPost(p);
        setUser(u);
    }

    public PostView(Post p, User u, Date t){
        this(p,u);
        setTime(t);
    }

    @ManyToOne(targetEntity = Post.class, cascade = { CascadeType.REMOVE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinColumn(name = "post", referencedColumnName = "id", unique = false, nullable = false)
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @ManyToOne(targetEntity = User.class, cascade = { CascadeType.REMOVE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinColumn(name = "user", referencedColumnName = "id", unique = false, nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "time", nullable = false, unique = false)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }
}
