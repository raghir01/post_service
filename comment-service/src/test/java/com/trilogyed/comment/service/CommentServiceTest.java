package com.trilogyed.comment.service;

import com.trilogyed.comment.dao.CommentDao;
import com.trilogyed.comment.dao.CommentDaoJdbcTemplateImpl;
import com.trilogyed.comment.model.Comment;
import com.trilogyed.comment.viewmodel.CommentViewModel;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CommentServiceTest {

    private CommentDao commentDao;

    private CommentService commentService;

    @Before
    public void setUp() throws Exception{
        setUpCommentDaoMock();
        commentService = new CommentService(commentDao);
    }

    public void setUpCommentDaoMock(){
        commentDao = mock(CommentDaoJdbcTemplateImpl.class);

        Comment comment = new Comment();
        comment.setCommentId(1);
        comment.setPostId(11);
        comment.setCreateDate(LocalDate.of(2019, 05, 15));
        comment.setCommenterName("Raghi");
        comment.setComment("A nice comment");

        Comment comment1 = new Comment();
        comment1.setPostId(11);
        comment1.setCreateDate(LocalDate.of(2019, 05, 15));
        comment1.setCommenterName("Raghi");
        comment1.setComment("A nice comment");

        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);
        doReturn(comment).when(commentDao).addComment(comment1);
        doReturn(comment).when(commentDao).getComment(1);
        doReturn(commentList).when(commentDao).getAllComments();
    }


    @Test
    public void addComment() {
        CommentViewModel commentViewModel = new CommentViewModel();
        commentViewModel.setPostId(11);
        commentViewModel.setCreateDate(LocalDate.of(2019, 05, 15));
        commentViewModel.setCommenterName("Raghi");
        commentViewModel.setComment("A nice comment");

        commentViewModel = commentService.addComment(commentViewModel);
        assertEquals(1,commentViewModel.getCommentId());
    }

    @Test
    public void getComment() {
        CommentViewModel commentViewModel = commentService.getComment(1);
        assertEquals(commentViewModel.getCommentId(), 1);
    }

    @Test
    public void getAllComments(){
        List<CommentViewModel> commentViewModels = commentService.getAllComments();
    }

    @Test
    public void updateComment(){
        CommentViewModel commentViewModel = new CommentViewModel();
        commentViewModel.setCommentId(1);
        commentViewModel.setPostId(11);
        commentViewModel.setCreateDate(LocalDate.of(2019, 05, 15));
        commentViewModel.setCommenterName("Raghi");
        commentViewModel.setComment("A nice comment");

    }

    @Test
    public void deleteComment(){
        commentService.deleteComment(1);
    }
}