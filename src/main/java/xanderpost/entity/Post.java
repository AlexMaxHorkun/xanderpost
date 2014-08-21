package xanderpost.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Post")
public class Post implements Serializable {
    private long id;

    private String title;

    private String text;

    private User author;

    private Set<PostRating> ratings;

    private Date created;

    private Date lastEdited;

    public Post() {
    }

    public Post(String title, String text) {
        this.text = text;
        this.title = title;
    }

    public Post(String t, String text, User author) {
        this(t, text);
        setAuthor(author);
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

    @ManyToOne(targetEntity = User.class, optional = false, fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "author", nullable = false, referencedColumnName = "id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @OneToMany(targetEntity = PostRating.class, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "post")
    @JsonIgnore
    public Set<PostRating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<PostRating> ratings) {
        this.ratings = ratings;
    }

    @Column(name = "created", nullable = false, columnDefinition = "default CURRENT_TIMESTAMP")
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Column(name = "last_edited", nullable = true, columnDefinition = "default CURRENT_TIMESTAMP")
    public Date getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
    }
}
