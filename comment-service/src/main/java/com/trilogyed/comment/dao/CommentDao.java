package com.trilogyed.comment.dao;

import com.trilogyed.comment.model.Comment;

import java.util.List;

public interface CommentDao {
    Comment getComment(int commentId);

    List<Comment> getAllComments();

    Comment addComment(Comment comment);

    void deleteComment(int commentId);

    void updateComment(Comment comment);

    List<Comment> getCommentsByPostId(int postId);

}
