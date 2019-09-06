package com.trilogyed.post.controller;

import com.trilogyed.post.model.Post;
import com.trilogyed.post.service.PostService;
import com.trilogyed.post.viewmodel.PostViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public PostViewModel createPost(@RequestBody PostViewModel postViewModel) {
        System.out.println("Adding Post: " + postViewModel);
        return postService.addPost(postViewModel);
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public List<PostViewModel> getAllPosts() {
        return postService.getAllPosts();
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public PostViewModel getPost(@PathVariable int id) {
        System.out.println("Getting post for: " + id);
        return postService.getPost(id);
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PUT)
    public void updatePost(@RequestBody PostViewModel postViewModel) {
        System.out.println("Updating Post: " + postViewModel);
        postService.updatePost(postViewModel);
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable int id) {
        System.out.println("Deleting post for: " + id);
        postService.deletePost(id);
    }

    @RequestMapping(value = "/posts/user/{posterName}", method = RequestMethod.GET)
    public List<PostViewModel> getPostsByPosterName(@PathVariable String posterName) {
        System.out.println("Getting post by poster_name : " + posterName);
        return postService.getPostsByPosterName(posterName);
    }
}
