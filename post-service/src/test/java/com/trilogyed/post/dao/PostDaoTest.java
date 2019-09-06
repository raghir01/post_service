package com.trilogyed.post.dao;

import com.trilogyed.post.model.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PostDaoTest {

    @Autowired
    PostDao postDao;

    @Before
    public void setUp() throws Exception{
        List<Post> postList = postDao.getAllPosts();
        for(Post post : postList){
            postDao.deletePost(post.getPostID());
        }
    }


    @Test
    public void addPost() {
        Post post = new Post();
        post.setPostDate(LocalDate.of(2018,8,3));
        post.setPosterName("Nidhi");
        post.setPost("Today's update");
        post = postDao.addPost(post);

        Post post1 = postDao.getPost(post.getPostID());
        assertEquals(post1, post);
    }

    @Test
    public void getPost() {
        Post post1 = new Post();
        post1.setPostDate(LocalDate.of(2018,8,3));
        post1.setPosterName("Nidhi");
        post1.setPost("Today's update");
        post1 = postDao.addPost(post1);

        Post post2 = new Post();
        post2.setPostDate(LocalDate.of(2018,8,3));
        post2.setPosterName("Nidhi");
        post2.setPost("Today's update");
        post2 = postDao.addPost(post2);

        Post post3 = postDao.getPost(post1.getPostID());
        assertEquals(post1, post3);
        post3 = postDao.getPost(post2.getPostID());
        assertEquals(post2, post3);
    }

    @Test
    public void getAllPosts() {
        Post post1 = new Post();
        post1.setPostDate(LocalDate.of(2018,8,3));
        post1.setPosterName("Nidhi");
        post1.setPost("Today's update");
        post1 = postDao.addPost(post1);

        Post post2 = new Post();
        post2.setPostDate(LocalDate.of(2018,8,3));
        post2.setPosterName("Nidhi");
        post2.setPost("Today's update");
        post2 = postDao.addPost(post2);

        List<Post> postList = postDao.getAllPosts();
        assertEquals(postList.size(), 2);
    }

    @Test
    public void deletePost() {
        Post post = new Post();
        post.setPostDate(LocalDate.of(2018,8,3));
        post.setPosterName("Nidhi");
        post.setPost("Today's update");
        post = postDao.addPost(post);

        postDao.deletePost(post.getPostID());
        Post post1 = postDao.getPost(post.getPostID());
        assertNull(post1);
    }

    @Test
    public void updatePost() {
        Post post = new Post();
        post.setPostDate(LocalDate.of(2018,8,3));
        post.setPosterName("Nidhi");
        post.setPost("Today's update");
        post = postDao.addPost(post);

        post.setPosterName("Gopika");
        post.setPost("Today's news");
        postDao.updatePost(post);

        Post post1 = postDao.getPost(post.getPostID());
        assertEquals(post1, post);
    }

    @Test
    public void getPostsByPosterName(){
        Post post = new Post();
        post.setPostDate(LocalDate.of(2018,8,3));
        post.setPosterName("Nidhi");
        post.setPost("Today's news");
        post = postDao.addPost(post);

        List<Post> postList = postDao.getPostsByPosterName("Nidhi");
        assertEquals(1, postList.size());
        assertEquals("Nidhi", postList.get(0).getPosterName());
    }

}