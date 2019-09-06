package com.trilogyed.stwitter.clients;

import com.trilogyed.stwitter.viewmodel.CommentViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(url="localhost:6868", name = "commentclient")
public interface CommentClient {

    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public CommentViewModel addComment(@RequestBody @Valid CommentViewModel commentViewModel);

    @RequestMapping(value = "/comments/{commentId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public CommentViewModel getComment(@PathVariable int commentId);

    @RequestMapping(value = "/comments/posts/{postId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<CommentViewModel> getCommentsByPostId(@PathVariable int postId);

    @RequestMapping(value = "/comments/{commentId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteComment(@PathVariable int commentId);

    @RequestMapping(value = "/comments/{commentId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateComment(@RequestBody CommentViewModel commentViewModel);


}
