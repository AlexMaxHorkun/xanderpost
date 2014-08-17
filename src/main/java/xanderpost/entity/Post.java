package xanderpost.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Post")
public class Post implements Serializable {
    private long id;

    private String title;

    private String text;

    private User author;

    private List<PostRating> ratings;

    public Post() {
    }

    public Post(String title, String text) {
        this.text = text;
        this.title = title;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue
    public long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }

    @Column(name = "title", length = 255, unique = false, nullable = false)
    @NotNull
    @Size(max = 255, min = 3)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "text", length = 4000, unique = false, nullable = false)
    @NotNull
    @Size(max = 4000, min = 3)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne(targetEntity = User.class, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "author", nullable = false, referencedColumnName = "id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @OneToMany(targetEntity = PostRating.class, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "post")
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    public List<PostRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<PostRating> ratings) {
        this.ratings = ratings;
    }
}
