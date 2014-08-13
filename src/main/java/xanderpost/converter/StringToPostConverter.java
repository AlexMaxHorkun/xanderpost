package xanderpost.converter;

import org.springframework.core.convert.converter.Converter;
import xanderpost.entity.Post;
import xanderpost.service.PostService;

public class StringToPostConverter implements Converter<String, Post> {
    private PostService postService;

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public Post convert(String id) {
        return getPostService().find(Long.parseLong(id));
    }
}
