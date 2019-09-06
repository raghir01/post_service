package com.trilogyed.post.service;

import com.trilogyed.post.dao.PostDao;
import com.trilogyed.post.dao.PostDaoJdbcTemplateImpl;
import com.trilogyed.post.model.Post;
import com.trilogyed.post.viewmodel.PostViewModel;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

public class PostServiceTest {

    private PostDao postDao;

    private PostService postService;

    @Before
    public void setUp() throws Exception{
        setUpPostDaoMock();
        postService = new PostService(postDao);
    }

    public void setUpPostDaoMock(){
        postDao = mock(PostDaoJdbcTemplateImpl.class);

        Post post = new Post();
        post.setPostID(1);
        post.setPostDate(LocalDate.of(2019, 8,3));
        post.setPosterName("Today's Post");
        post.setPost("A nice post");

        Post post1 = new Post();
        post1.setPostDate(LocalDate.of(2019, 8,3));
        post1.setPosterName("Today's Post");
        post1.setPost("A nice post");

        List<Post> postList = new ArrayList<>();
        postList.add(post);
        doReturn(post).when(postDao).addPost(post1);
        doReturn(post).when(postDao).getPost(1);
        doReturn(postList).when(postDao).getAllPosts();
    }

    @Test
    public void addPost() {
        PostViewModel postViewModel = new PostViewModel();
        postViewModel.setPostDate(LocalDate.of(2019, 8,3));
        postViewModel.setPosterName("Today's Post");
        postViewModel.setPost("A nice post");

        postViewModel = postService.addPost(postViewModel);
        assertEquals(1, postViewModel.getPostId());
    }

    @Test
    public void getPost() {
        PostViewModel postViewModel = postService.getPost(1);
        assertEquals(postViewModel.getPostId(), 1);
    }

    @Test
    public void getAllPosts() {
        List<PostViewModel> postViewModelList = postService.getAllPosts();
    }

    @Test
    public void updatePost() {
        PostViewModel postViewModel = new PostViewModel();
        postViewModel.setPostId(1);
        postViewModel.setPostDate(LocalDate.of(2019, 8,3));
        postViewModel.setPosterName("Today's Post");
        postViewModel.setPost("A nice post");
    }

    @Test
    public void deletePost() {
        postService.deletePost(1);
    }
}