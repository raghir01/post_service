package com.trilogyed.post.service;

import com.trilogyed.post.dao.PostDao;
import com.trilogyed.post.model.Post;
import com.trilogyed.post.viewmodel.PostViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    PostDao postDao;

    @Autowired
    public PostService(PostDao postDao){
        this.postDao = postDao;
    }

    @Transactional
    public PostViewModel addPost(PostViewModel postViewModel){
        Post post = new Post();
        post.setPostDate(postViewModel.getPostDate());
        post.setPosterName(postViewModel.getPosterName());
        post.setPost(postViewModel.getPost());

        post = postDao.addPost(post);
        postViewModel.setPostId(post.getPostID());
        return postViewModel;
    }

    public PostViewModel getPost(int postId){
        Post post = postDao.getPost(postId);
        PostViewModel postViewModel = new PostViewModel();
        postViewModel.setPostId(post.getPostID());
        postViewModel.setPostDate(post.getPostDate());
        postViewModel.setPosterName(post.getPosterName());
        postViewModel.setPost(post.getPost());
        return postViewModel;
    }

    public List<PostViewModel> getAllPosts(){
        List<Post> postList = postDao.getAllPosts();
        List<PostViewModel> postViewModelList = new ArrayList<>();
        for(Post post : postList){
            PostViewModel postViewModel = new PostViewModel();
            postViewModel.setPostId(post.getPostID());
            postViewModel.setPostDate(post.getPostDate());
            postViewModel.setPosterName(post.getPosterName());
            postViewModel.setPost(post.getPost());
            postViewModelList.add(postViewModel);

        }
        return postViewModelList;
    }

    public PostViewModel updatePost(PostViewModel postViewModel){
        Post post = new Post();
        post.setPostID(postViewModel.getPostId());
        post.setPostDate(postViewModel.getPostDate());
        post.setPosterName(postViewModel.getPosterName());
        post.setPost(postViewModel.getPost());
        postDao.updatePost(post);
        return postViewModel;
    }

    public void deletePost(int postId){
        postDao.deletePost(postId);
    }

    public List<PostViewModel> getPostsByPosterName(String posterNmae){
        List<Post> postList = postDao.getPostsByPosterName(posterNmae);
        List<PostViewModel> postViewModelList = new ArrayList<>();
        for(Post post : postList){
            PostViewModel postViewModel = new PostViewModel();
            postViewModel.setPostId(post.getPostID());
            postViewModel.setPostDate(post.getPostDate());
            postViewModel.setPosterName(post.getPosterName());
            postViewModel.setPost(post.getPost());
            postViewModelList.add(postViewModel);

        }
        return postViewModelList;
    }

}
