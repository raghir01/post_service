package com.company.commentqueueconsumer;

import com.company.commentqueueconsumer.util.messages.CommentListEntry;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    @Autowired
    CommentsClient commentsClient;

    @RabbitListener(queues = CommentQueueConsumerApplication.QUEUE_NAME)
    public void receiveMessage(CommentListEntry msg) {

        System.out.println("Invoking comment service for " + msg.toString());
        if(msg.getCommentId()!=0){
            commentsClient.updateComment(msg);
            System.out.println("Comment updated" + msg);
        }else {
            CommentListEntry newcomment = commentsClient.addComment(msg);
            System.out.println("Comment added " + newcomment.toString());
        }
    }
}
