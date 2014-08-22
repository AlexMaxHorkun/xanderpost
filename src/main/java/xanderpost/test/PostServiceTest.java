package xanderpost.test;

import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xanderpost.entity.Post;
import xanderpost.entity.User;
import xanderpost.security.UserRole;
import xanderpost.service.PostService;
import xanderpost.service.UserService;

import javax.validation.Validator;
import java.util.*;
import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml", "classpath:spring-security-config.xml", "classpath:spring-test-config.xml"})
public class PostServiceTest {
    private PostService postService;
    private UserService userService;
    private Validator validator;

    private ApplicationContext applicationContext;

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

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Autowired
    @Qualifier("validator")
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private List<Post> generateValidPosts(List<User> users) {
        Random random = new Random();
        List<Post> posts = new ArrayList<Post>();
        for (int i = 0; i < 100; i++) {
            posts.add(new Post("testValidPost" + String.valueOf(i), "some test text " + String.valueOf(i), users.get(random.nextInt(users.size()))));
        }
        return posts;
    }

    private List<Post> generateValidPosts() {
        return generateValidPosts(generateAuthors());
    }

    private List<Post> generateInvalidPosts() {
        List<Post> posts = new ArrayList<Post>();
        for (int i = 0; i < 100; i++) {
            posts.add(new Post("t" + String.valueOf(i), ""));
        }
        return posts;
    }

    private List<User> generateAuthors() {
        List<User> users = new ArrayList<User>();
        Set<UserRole> roles = new HashSet<UserRole>();
        roles.add(new UserRole("ROLE_USER"));
        for (int i = 0; i < 20; i++) {
            users.add(new User("test" + (i + 1) + "@test.com", "123456", roles));
        }
        return users;
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
    }

    @Test
    @DependsOn("testValidation")
    public void testPersist() {
        List<User> authors = generateAuthors();
        for (User u : authors) {
            userService.persist(u);
        }
        for (Post p : generateValidPosts(authors)) {
            postService.persist(p);
        }
    }
}
