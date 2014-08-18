package xanderpost.entity.readonly;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Subselect("select u.id, u.email, count(distinct p.id) as postsWritten, avg(pr.rate) as avgPostsWrittenRating from User u left join Post as p on u.id=p.author left join PostRating as pr on p.id=pr.post_id group by u.id")
@Synchronize({"User", "Post", "PostRating"})
public class UserInfo {
    private long id;

    private String email;

    private int postsWritten;

    private Float avgPostsWrittenRating;

    @Id
    public long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPostsWritten() {
        return postsWritten;
    }

    public void setPostsWritten(int postsWritten) {
        this.postsWritten = postsWritten;
    }

    public Float getAvgPostsWrittenRating() {
        return avgPostsWrittenRating;
    }

    public void setAvgPostsWrittenRating(Float avgPostsWrittenRating) {
        this.avgPostsWrittenRating = avgPostsWrittenRating;
    }
}
