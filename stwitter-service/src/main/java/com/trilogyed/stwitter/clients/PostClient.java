package com.trilogyed.stwitter.clients;

import com.trilogyed.stwitter.viewmodel.PostViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url="localhost:1777", name = "postclient")
public interface PostClient {

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public PostViewModel addPost(PostViewModel postViewModel);

    @RequestMapping(value = "/posts/{postId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public PostViewModel getPost(@PathVariable int postId);

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updatePost(@PathVariable int id, @RequestBody PostViewModel postViewModel);

    @RequestMapping(value = "/posts/{postId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePost(@PathVariable int postId);

    @RequestMapping(value = "/posts/user/{posterName}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<PostViewModel> getPostsByPosterName(@PathVariable String posterName);

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public List<PostViewModel> getAllPosts();

}
