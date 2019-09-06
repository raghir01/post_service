package com.trilogyed.comment.service;

import com.trilogyed.comment.dao.CommentDao;
import com.trilogyed.comment.model.Comment;
import com.trilogyed.comment.viewmodel.CommentViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    CommentDao commentDao;

    @Autowired
    public CommentService(CommentDao commentDao){
        this.commentDao = commentDao;
    }

    @Transactional
    public CommentViewModel addComment(CommentViewModel commentViewModel){
        Comment comment = new Comment();
        comment.setPostId(commentViewModel.getPostId());
        comment.setCreateDate(commentViewModel.getCreateDate());
        comment.setCommenterName(commentViewModel.getCommenterName());
        comment.setComment(commentViewModel.getComment());

        comment = commentDao.addComment(comment);
        commentViewModel.setCommentId(comment.getCommentId());
        return commentViewModel;
    }

    public CommentViewModel getComment(int commentId){
        Comment comment = commentDao.getComment(commentId);
        CommentViewModel commentViewModel = new CommentViewModel();
        commentViewModel.setCommentId(comment.getCommentId());
        commentViewModel.setPostId(comment.getPostId());
        commentViewModel.setCreateDate(comment.getCreateDate());
        commentViewModel.setCommenterName(comment.getCommenterName());
        commentViewModel.setComment(comment.getComment());
        return commentViewModel;
    }

    public List<CommentViewModel> getAllComments(){
        List<Comment> commentList = commentDao.getAllComments();
        List<CommentViewModel> commentViewModelList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentViewModel commentViewModel = new CommentViewModel();
            commentViewModel.setCommentId(comment.getCommentId());
            commentViewModel.setPostId(comment.getPostId());
            commentViewModel.setCreateDate(comment.getCreateDate());
            commentViewModel.setCommenterName(comment.getCommenterName());
            commentViewModel.setComment(comment.getComment());

            commentViewModelList.add(commentViewModel);
        }
        return commentViewModelList;
    }

    public CommentViewModel updateComment(CommentViewModel commentViewModel){
        Comment comment = new Comment();
        comment.setCommentId(commentViewModel.getCommentId());
        comment.setPostId(commentViewModel.getPostId());
        comment.setCreateDate(commentViewModel.getCreateDate());
        comment.setCommenterName(commentViewModel.getCommenterName());
        comment.setComment(commentViewModel.getComment());
        return commentViewModel;
    }

    public void deleteComment(int commentId){
        commentDao.deleteComment(commentId);
    }

    public List<CommentViewModel> getCommentsByPostId(int postId){
        List<Comment> commentList = commentDao.getCommentsByPostId(postId);
        List<CommentViewModel> commentViewModelList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentViewModel commentViewModel = new CommentViewModel();
            commentViewModel.setCommentId(comment.getCommentId());
            commentViewModel.setPostId(comment.getPostId());
            commentViewModel.setCreateDate(comment.getCreateDate());
            commentViewModel.setCommenterName(comment.getCommenterName());
            commentViewModel.setComment(comment.getComment());

            commentViewModelList.add(commentViewModel);
        }
        return commentViewModelList;
    }

}
