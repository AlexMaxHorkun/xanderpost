package xanderpost.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Post")
public class Post {
    private long id;

    @NotNull
    @Size(max = 255, min = 3)
    private String title;

    @NotNull
    @Size(max = 4000, min = 3)
    private String text;

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
}
