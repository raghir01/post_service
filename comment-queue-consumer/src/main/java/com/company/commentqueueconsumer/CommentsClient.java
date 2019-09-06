package com.company.commentqueueconsumer;

import com.company.commentqueueconsumer.util.messages.CommentListEntry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(url = "localhost:6868", name = "comments")
public interface CommentsClient {

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    List<String> getComments();

    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    CommentListEntry addComment(CommentListEntry comment);

    @RequestMapping(value = "/comments", method = RequestMethod.PUT)
    CommentListEntry updateComment(CommentListEntry comment);
}
