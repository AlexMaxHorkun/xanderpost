package xanderpost.converter;

import org.springframework.core.convert.converter.Converter;
import xanderpost.entity.Post;
import xanderpost.repository.PostDaoInterface;

public class StringToPostConverter implements Converter<String, Post> {
    private PostDaoInterface postDao;

    public PostDaoInterface getPostDao() {
        return postDao;
    }

    public void setPostDao(PostDaoInterface postDao) {
        this.postDao = postDao;
    }

    public Post convert(String id) {
        return getPostDao().find(Long.parseLong(id));
    }
}
