package xanderpost.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xanderpost.entity.Post;
import xanderpost.service.PostService;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml", "classpath:spring-security-config.xml"})
public class PostServiceTest {
    private PostService postService;
    private Validator validator;

    private List<Post> testPosts;

    public PostServiceTest() {
    }

    public PostService getPostService() {
        return postService;
    }

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public Validator getValidator() {
        return validator;
    }

    @Autowired
    @Qualifier("validator")
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    private List<Post> generateValidPosts() {
        List<Post> posts = new ArrayList<Post>();
        for (int i = 0; i < 100; i++) {
            posts.add(new Post("testValidPost" + String.valueOf(i), "some test text " + String.valueOf(i)));
        }
        return posts;
    }

    public List<Post> generateInvalidPosts() {
        List<Post> posts = new ArrayList<Post>();
        for (int i = 0; i < 100; i++) {
            posts.add(new Post("t" + String.valueOf(i), ""));
        }
        return posts;
    }

    @Test
    public void testValidation() {
        Logger logger = Logger.getLogger(getClass().toString());
        logger.info("Validation test started");
        List<Post> posts = generateInvalidPosts();
        logger.info("Loaded " + posts.size() + " invalid posts to validate");
        for (Post p : posts) {
            assertFalse(validator.validate(p).isEmpty());
        }
        posts = generateValidPosts();
        logger.info("Loaded " + posts.size() + " valid posts to validate");
        for (Post p : posts) {
            assertTrue(validator.validate(p).isEmpty());
        }
        testPosts = posts;
    }
}
