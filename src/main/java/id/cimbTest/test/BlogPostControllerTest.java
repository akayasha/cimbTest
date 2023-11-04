package id.cimbTest.test;

import id.cimbTest.controller.BlogPostController;
import id.cimbTest.model.entity.BlogPost;
import id.cimbTest.model.repository.BlogPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.NestedServletException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Enable Mockito for JUnit 5
public class BlogPostControllerTest {

    @InjectMocks
    private BlogPostController blogPostController;

    @Mock
    private BlogPostRepository blogPostRepository;



    private BlogPost testBlogPost;
    private Long testBlogPostId = 1L;

    @BeforeEach
    public void setUp() {
        testBlogPost = new BlogPost();
        testBlogPost.setId(testBlogPostId);
        testBlogPost.setTitle("Test Blog Post Title");
        testBlogPost.setBody("Test Blog Post Content");
    }

    @Test
    public void testCreateBlogPost() {
        when(blogPostRepository.save(Mockito.any())).thenReturn(testBlogPost);

        ResponseEntity<BlogPost> responseEntity = blogPostController.createBlogPost(testBlogPost);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(testBlogPost, responseEntity.getBody());
    }

    @Test
    public void testGetBlogPost() {
        when(blogPostRepository.findById(testBlogPostId)).thenReturn(Optional.of(testBlogPost));

        ResponseEntity<BlogPost> responseEntity = blogPostController.getBlogPost(testBlogPostId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testBlogPost, responseEntity.getBody());
    }

    @Test
    public void testGetNonExistentBlogPost() {
        when(blogPostRepository.findById(testBlogPostId)).thenReturn(Optional.empty());

        ResponseEntity<BlogPost> responseEntity = blogPostController.getBlogPost(testBlogPostId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAllBlogPosts() {
        Page<BlogPost> page = Mockito.mock(Page.class);
        when(blogPostRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);

        ResponseEntity<Page<BlogPost>> responseEntity = blogPostController.getAllBlogPosts(Mockito.mock(Pageable.class));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(page, responseEntity.getBody());
    }

    @Test
    public void testUpdateBlogPost() {
        when(blogPostRepository.existsById(testBlogPostId)).thenReturn(true);
        when(blogPostRepository.save(Mockito.any())).thenReturn(testBlogPost);

        ResponseEntity<BlogPost> responseEntity = blogPostController.updateBlogPost(testBlogPostId, testBlogPost);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testBlogPost, responseEntity.getBody());
    }

    @Test
    public void testUpdateNonExistentBlogPost() {
        when(blogPostRepository.existsById(testBlogPostId)).thenReturn(false);

        ResponseEntity<BlogPost> responseEntity = blogPostController.updateBlogPost(testBlogPostId, testBlogPost);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteBlogPost() {
        when(blogPostRepository.existsById(testBlogPostId)).thenReturn(true);

        ResponseEntity<Void> responseEntity = blogPostController.deleteBlogPost(testBlogPostId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteNonExistentBlogPost() {
        when(blogPostRepository.existsById(testBlogPostId)).thenReturn(false);

        ResponseEntity<Void> responseEntity = blogPostController.deleteBlogPost(testBlogPostId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
