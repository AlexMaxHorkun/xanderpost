package xanderpost.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "PostRating", uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "user_id"}))
public class PostRating implements Serializable {

    private Post post;

    private User user;

    private int rate;

    private long id;

    public PostRating() {
    }

    public PostRating(Post p, User u, int r) {
        setPost(p);
        setUser(u);
        setRate(r);
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }

    @ManyToOne(targetEntity = Post.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    @NotNull
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @NotNull
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "rate", nullable = false)
    @NotNull
    @Min(1)
    @Max(10)
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
