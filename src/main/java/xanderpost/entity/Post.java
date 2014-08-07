package xanderpost.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    public long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
