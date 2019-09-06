package com.trilogyed.stwitter.service;

import com.trilogyed.stwitter.clients.CommentClient;
import com.trilogyed.stwitter.clients.PostClient;
import com.trilogyed.stwitter.viewmodel.CommentViewModel;
import com.trilogyed.stwitter.viewmodel.PostViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ServiceLayer {

    @Autowired
    private PostClient postClient;
    @Autowired
    private CommentClient commentClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static final String EXCHANGE = "comment-exchange";
    public static final String ROUTING_KEY = "comment.create.#";

    public PostViewModel addPost(PostViewModel postViewModel){
        PostViewModel post =  postClient.addPost(postViewModel);
        post.setComments(postViewModel.getComments());
        if(postViewModel.getComments() != null){
            for(CommentViewModel comment : postViewModel.getComments()){
                comment.setPostId(post.getPostId());
                rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, comment);
                System.out.println(">>>>>>> Written to queue" + comment);
            }
        }

        return post;
    }

    public PostViewModel getPost(int postId){
        PostViewModel postViewModel = postClient.getPost(postId);
        List<CommentViewModel> comments = commentClient.getCommentsByPostId(postId);
        postViewModel.setComments(comments);
        return postViewModel;
    }

    public List<PostViewModel> getPostsByPosterName(String posterName){
        List<PostViewModel> posts = postClient.getPostsByPosterName(posterName);
        for(PostViewModel post: posts){
            List<CommentViewModel> comments = commentClient.getCommentsByPostId(post.getPostId());
            post.setComments(comments);
        }
        return posts;
    }

    public List<PostViewModel> getAllPosts(){
        List<PostViewModel> posts = postClient.getAllPosts();
        for(PostViewModel post: posts){
            List<CommentViewModel> comments = commentClient.getCommentsByPostId(post.getPostId());
            post.setComments(comments);
        }
        return posts;
    }

    public void deletePost(int postId){
        PostViewModel post = getPost(postId);
        if(post!= null && post.getComments() != null){
            for(CommentViewModel comment : post.getComments()){
                commentClient.deleteComment(comment.getCommentId());
            }
        }
        postClient.deletePost(postId);
    }

    public void updatePost(@RequestBody PostViewModel postViewModel){
        postClient.updatePost(postViewModel.getPostId(), postViewModel);
        if(postViewModel.getComments() != null){
            for(CommentViewModel comment : postViewModel.getComments()){
                comment.setPostId(postViewModel.getPostId());
                rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, comment);
                System.out.println(">>>>>>> Written to queue" + comment);
            }
        }
    }

    // These setters are for making mocking easier for testing

    public void setPostClient(PostClient postClient) {
        this.postClient = postClient;
    }

    public void setCommentClient(CommentClient commentClient) {
        this.commentClient = commentClient;
    }

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
}
