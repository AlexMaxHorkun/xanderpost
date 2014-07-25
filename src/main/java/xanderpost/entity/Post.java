package xanderpost.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="post")
public class Post {
    private long id;

    private String title;

    private String text;

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
