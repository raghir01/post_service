package com.trilogyed.stwitter.service;

import com.trilogyed.stwitter.clients.CommentClient;
import com.trilogyed.stwitter.clients.PostClient;
import com.trilogyed.stwitter.viewmodel.CommentViewModel;
import com.trilogyed.stwitter.viewmodel.PostViewModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServiceLayerTest {

    private ServiceLayer serviceLayer;
    private PostClient postClient;
    private CommentClient commentClient;
    private RabbitTemplate rabbitTemplate;

    @Before
    public void setUP() throws Exception{
        mockCommentsClient();
        mockPostClient();
        mockRabbitTemplate();
        serviceLayer = new ServiceLayer();
        serviceLayer.setCommentClient(commentClient);
        serviceLayer.setPostClient(postClient);
        serviceLayer.setRabbitTemplate(rabbitTemplate);

    }

    private void mockRabbitTemplate(){
        rabbitTemplate = mock(RabbitTemplate.class);
    }

    private void mockPostClient(){
        postClient = mock(PostClient.class);

        PostViewModel postViewModel = new PostViewModel();
        postViewModel.setPostId(1);
        postViewModel.setPostDate(LocalDate.of(2019,07,25));
        postViewModel.setPosterName("My post");
        postViewModel.setPost("Coollll");
        List<CommentViewModel> comments = new ArrayList<>();
        CommentViewModel commentViewModel = new CommentViewModel();
        commentViewModel.setCreateDate(LocalDate.of(2019,07,25));
        commentViewModel.setCommenterName("Gipsy");
        commentViewModel.setComment("comment1");
        comments.add(commentViewModel);
        postViewModel.setComments(comments);

        PostViewModel postViewModel1 = new PostViewModel();
        postViewModel1.setPostDate(LocalDate.of(2019,07,25));
        postViewModel1.setPosterName("My post");
        postViewModel1.setPost("Coollll");
        List<CommentViewModel> comments1 = new ArrayList<>();
        CommentViewModel commentViewModel1 = new CommentViewModel();
        commentViewModel1.setCreateDate(LocalDate.of(2019,07,25));
        commentViewModel1.setCommenterName("Gipsy");
        commentViewModel1.setComment("comment1");
        comments1.add(commentViewModel1);
        postViewModel1.setComments(comments1);

        List<PostViewModel> postViewModelList = new ArrayList<>();
        postViewModelList.add(postViewModel);
        doReturn(postViewModel).when(postClient).addPost(any(PostViewModel.class));
        doReturn(postViewModel).when(postClient).getPost(1);
        doReturn(postViewModelList).when(postClient).getPostsByPosterName(anyString());
        doReturn(postViewModelList).when(postClient).getAllPosts();
    }

    private void mockCommentsClient(){

        commentClient = mock(CommentClient.class);
        CommentViewModel commentViewModel = new CommentViewModel();
        commentViewModel.setCommentId(1);
        commentViewModel.setPostId(11);
        commentViewModel.setCreateDate(LocalDate.of(2019,07,25));
        commentViewModel.setCommenterName("Nidhi");
        commentViewModel.setComment("Very Nice");

        CommentViewModel commentViewModel1 = new CommentViewModel();
        commentViewModel1.setPostId(11);
        commentViewModel1.setCreateDate(LocalDate.of(2019,07,25));
        commentViewModel1.setCommenterName("Nidhi");
        commentViewModel1.setComment("Very Nice");

        List<CommentViewModel> commentViewModelList = new ArrayList<>();
        commentViewModelList.add(commentViewModel);
        doReturn(commentViewModel).when(commentClient).addComment(any(CommentViewModel.class));
        doReturn(commentViewModel).when(commentClient).getComment(1);
        doReturn(commentViewModelList).when(commentClient).getCommentsByPostId(1);


    }

    @Test
    public void addPost() {

        PostViewModel postViewModel1 = new PostViewModel();
        postViewModel1.setPostDate(LocalDate.of(2019,07,25));
        postViewModel1.setPosterName("My post");
        postViewModel1.setPost("Coollll");
        List<CommentViewModel> comments1 = new ArrayList<>();
        CommentViewModel commentViewModel1 = new CommentViewModel();
        commentViewModel1.setCreateDate(LocalDate.of(2019,07,25));
        commentViewModel1.setCommenterName("Gipsy");
        commentViewModel1.setComment("comment1");
        comments1.add(commentViewModel1);
        postViewModel1.setComments(comments1);

        PostViewModel newPost = serviceLayer.addPost(postViewModel1);
        assertEquals(1, newPost.getPostId());
        List<CommentViewModel> comments = newPost.getComments();
        assertEquals(1, comments.size());

    }

    @Test
    public void getPost() {
        PostViewModel post = serviceLayer.getPost(1);
        assertEquals(1, post.getPostId());
        List<CommentViewModel> comments = post.getComments();
        assertEquals(1, comments.size());
    }

    @Test
    public void getPostsByPosterName() {
        List<PostViewModel> posts = serviceLayer.getPostsByPosterName("My post");
        assertEquals(1, posts.size());
        List<CommentViewModel> comments = posts.get(0).getComments();
        assertEquals(1, comments.size());
    }

    @Test
    public void getAllPosts() {
        List<PostViewModel> posts = serviceLayer.getAllPosts();
        assertEquals(1, posts.size());
        List<CommentViewModel> comments = posts.get(0).getComments();
        assertEquals(1, comments.size());
    }
}