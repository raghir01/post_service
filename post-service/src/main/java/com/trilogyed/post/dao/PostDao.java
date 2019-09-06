package com.trilogyed.post.dao;

import com.trilogyed.post.model.Post;

import java.util.List;

public interface PostDao {

    Post getPost(int postId);

    List<Post> getAllPosts();

    Post addPost(Post post);

    void deletePost(int postId);

    void updatePost(Post post);

    List<Post> getPostsByPosterName(String posterName);
}
