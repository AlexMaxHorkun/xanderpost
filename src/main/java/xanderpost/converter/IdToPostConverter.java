package xanderpost.converter;

import org.springframework.core.convert.converter.Converter;
import xanderpost.entity.Post;
import xanderpost.repository.PostDaoInterface;

public class IdToPostConverter implements Converter<Long, Post>{
    private PostDaoInterface postDao;

    public PostDaoInterface getPostDao() {
        return postDao;
    }

    public void setPostDao(PostDaoInterface postDao) {
        this.postDao = postDao;
    }

    public Post convert(Long id){
        return getPostDao().find(id);
    }
}
