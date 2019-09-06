package com.trilogyed.comment.controller;


import com.trilogyed.comment.service.CommentService;
import com.trilogyed.comment.viewmodel.CommentViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    @ResponseStatus(value =  HttpStatus.CREATED)
    public CommentViewModel addComment(@RequestBody @Valid CommentViewModel commentViewModel){
        System.out.println("Adding comment: " + commentViewModel);
        return commentService.addComment(commentViewModel);
    }

    @RequestMapping(value = "/comments/{commentId}", method = RequestMethod.GET)
    @ResponseStatus(value =  HttpStatus.OK)
    public CommentViewModel getComment(@PathVariable int commentId){
        System.out.println("Getting comment: " + commentId);
        return commentService.getComment(commentId);
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    @ResponseStatus(value =  HttpStatus.OK)
    public List<CommentViewModel> getAllComments(){
        return commentService.getAllComments();
    }

    @RequestMapping(value = "/comments", method = RequestMethod.PUT)
    @ResponseStatus(value =  HttpStatus.OK)
    public CommentViewModel updateComment(@RequestBody @Valid CommentViewModel commentViewModel){
        System.out.println("Updating comment: " + commentViewModel);
        return commentService.updateComment(commentViewModel);
    }

    @RequestMapping(value = "/comments/{commentId}", method = RequestMethod.DELETE)
    @ResponseStatus(value =  HttpStatus.OK)
    public void deleteComment(@PathVariable int commentId){
        System.out.println("Deleting comment: " + commentId);
        commentService.deleteComment(commentId);
    }

    @RequestMapping(value = "/comments/posts/{postId}", method = RequestMethod.GET)
    @ResponseStatus(value =  HttpStatus.OK)
    public List<CommentViewModel> getCommentsByPostId(@PathVariable int postId){
        System.out.println("Getting comment: " + postId);
        return commentService.getCommentsByPostId(postId);
    }

}
