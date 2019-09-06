package com.trilogyed.comment.dao;

import com.trilogyed.comment.model.Comment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CommentDaoTest {

    @Autowired
    CommentDao commentDao;

    @Before
    public void setUp() throws Exception {
        List<Comment> commentList = commentDao.getAllComments();
        for(Comment comment : commentList){
            commentDao.deleteComment(comment.getCommentId());
        }
    }

    @Test
    public void addComment() {
        Comment comment = new Comment();
        comment.setPostId(11);
        comment.setCreateDate(LocalDate.of(2018,01,20));
        comment.setCommenterName("Raghi");
        comment.setComment("A nice comment.");
        comment = commentDao.addComment(comment);

        Comment comment1 =commentDao.getComment(comment.getCommentId());
        assertEquals(comment1, comment);
    }

    @Test
    public void getComment() {
        Comment comment1 = new Comment();
        comment1.setPostId(11);
        comment1.setCreateDate(LocalDate.of(2018,1,20));
        comment1.setCommenterName("Raghi");
        comment1.setComment("A nice comment.");
        comment1 = commentDao.addComment(comment1);

        Comment comment2 = new Comment();
        comment2.setPostId(11);
        comment2.setCreateDate(LocalDate.of(2018,1,20));
        comment2.setCommenterName("Raghi");
        comment2.setComment("A nice comment.");
        comment2 = commentDao.addComment(comment2);

        Comment comment3 = commentDao.getComment(comment1.getCommentId());
        assertEquals(comment1, comment3);
        comment3 = commentDao.getComment(comment2.getCommentId());
        assertEquals(comment2,comment3);
    }

    @Test
    public void getAllComments() {
        Comment comment1 = new Comment();
        comment1.setPostId(11);
        comment1.setCreateDate(LocalDate.of(2018,1,20));
        comment1.setCommenterName("Raghi");
        comment1.setComment("A nice comment.");
        comment1 = commentDao.addComment(comment1);

        Comment comment2 = new Comment();
        comment2.setPostId(11);
        comment2.setCreateDate(LocalDate.of(2018,1,20));
        comment2.setCommenterName("Raghi");
        comment2.setComment("A nice comment.");
        comment2 = commentDao.addComment(comment2);

        List<Comment> commentList = commentDao.getAllComments();
        assertEquals(commentList.size(),2);
    }

    @Test
    public void deleteComment() {
        Comment comment = new Comment();
        comment.setPostId(11);
        comment.setCreateDate(LocalDate.of(2018,1,20));
        comment.setCommenterName("Raghi");
        comment.setComment("A nice comment.");
        comment = commentDao.addComment(comment);

        commentDao.deleteComment(comment.getCommentId());
        Comment comment1 = commentDao.getComment(comment.getCommentId());
        assertNull(comment1);
    }

    @Test
    public void updateComment() {
        Comment comment = new Comment();
        comment.setPostId(11);
        comment.setCreateDate(LocalDate.of(2018,1,20));
        comment.setCommenterName("Raghi");
        comment.setComment("A nice comment.");
        comment = commentDao.addComment(comment);

        comment.setPostId(12);
        comment.setCommenterName("Raghi Ramachandran");
        comment.setComment("Have a nice day!.");

        commentDao.updateComment(comment);

        Comment comment1 = commentDao.getComment(comment.getCommentId());
        assertEquals(comment1, comment);
    }

    @Test
    public void getCommentsByPostId(){
        Comment comment = new Comment();
        comment.setPostId(11);
        comment.setCreateDate(LocalDate.of(2018,1,20));
        comment.setCommenterName("Raghi");
        comment.setComment("A nice comment.");
        comment = commentDao.addComment(comment);

        List<Comment> commentList = commentDao.getCommentsByPostId(11);
        assertEquals(1, commentList.size());
        assertEquals(11, commentList.get(0).getPostId());

    }
}