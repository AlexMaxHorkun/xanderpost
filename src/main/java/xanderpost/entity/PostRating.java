package xanderpost.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import xanderpost.entity.validation.PostRatingNotAuthor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PostRating", uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "user_id"}))
@PostRatingNotAuthor
public class PostRating implements Serializable {

    private Post post;

    private User user;

    private int rate;

    private Date created;

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

    @ManyToOne(targetEntity = Post.class, fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    @NotNull
    @JsonIgnore
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @NotNull
    @JsonIgnore
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

    @Column(name = "created", nullable = false)
    public Date getCreated() {
        if(created==null){
            created=new Date();
        }
        return created;
    }

    protected void setCreated(Date created) {
        this.created = created;
    }
}
