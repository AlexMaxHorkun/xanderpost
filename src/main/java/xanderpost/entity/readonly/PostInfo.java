package xanderpost.entity.readonly;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.*;
import java.util.Date;

@Entity
@Subselect("select p.id, p.title, p.text, p.created, p.last_edited, p.author, avg(r.rate) as avg_rating, count(distinct r.id) as ratings_count, count(distinct pw.id) as views_count "
        + "from Post p left join PostRating r on r.post_id=p.id left join PostView pw on p.id=pw.post group by p.id")
@Synchronize({"Post", "PostRatings"})
public class PostInfo {
    private long id;

    private String title;

    private String text;

    private UserInfo author;

    private Date created;

    private Date lastEdited;

    private Float avgRating;

    private int ratingsCount;

    private int viewsCount;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }

    @Column(name = "title", length = 255, unique = false, nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "text", length = 4000, unique = false, nullable = false)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne(targetEntity = UserInfo.class, optional = false, fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "author", nullable = false, referencedColumnName = "id")
    public UserInfo getAuthor() {
        return author;
    }

    public void setAuthor(UserInfo author) {
        this.author = author;
    }

    @Column(name = "avg_rating")
    public Float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Float avgRating) {
        this.avgRating = avgRating;
    }

    @Column(name = "created", nullable = false)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Column(name = "last_edited", nullable = true)
    public Date getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
    }

    @Column(name = "ratings_count")
    public int getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    @Column(name = "views_count")
    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }
}
