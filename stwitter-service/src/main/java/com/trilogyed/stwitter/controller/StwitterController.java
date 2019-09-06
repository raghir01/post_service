package com.trilogyed.stwitter.controller;

import com.trilogyed.stwitter.service.ServiceLayer;
import com.trilogyed.stwitter.viewmodel.PostViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@CacheConfig(cacheNames = {"posts"})
public class StwitterController {


    ServiceLayer service;

    @Autowired
    public StwitterController(ServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public PostViewModel addPost(@RequestBody @Valid PostViewModel postViewModel){
        return service.addPost(postViewModel);
    }

    @Cacheable
    @RequestMapping(value = "/posts/{postId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public PostViewModel getPost(@PathVariable int postId){
        System.out.println("GETTING FROM DATABASE");
        return service.getPost(postId);
    }

    @Cacheable
    @RequestMapping(value = "/posts/user/{posterName}", method = RequestMethod.GET)
    public List<PostViewModel> getPostsByPosterName(@PathVariable String posterName) {
        System.out.println("GETTING FROM DATABASE");
        return service.getPostsByPosterName(posterName);
    }

    @Cacheable
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public List<PostViewModel> getAllPosts() {
        System.out.println("GETTING FROM DATABASE");
        return service.getAllPosts();
    }

    @CacheEvict(value = "posts", allEntries = true)
    @RequestMapping(value = "/posts/{postId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePost(@PathVariable int postId){
        service.deletePost(postId);
    }

    @CacheEvict(value = "posts", allEntries = true)
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updatePost(@RequestBody PostViewModel postViewModel){
        service.updatePost(postViewModel);
    }
}
