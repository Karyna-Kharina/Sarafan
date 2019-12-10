package org.homeproject.demo.sarafan.service;

import org.homeproject.demo.sarafan.domain.Comment;
import org.homeproject.demo.sarafan.domain.Message;
import org.homeproject.demo.sarafan.domain.User;
import org.homeproject.demo.sarafan.domain.Views;
import org.homeproject.demo.sarafan.dto.EventType;
import org.homeproject.demo.sarafan.dto.ObjectType;
import org.homeproject.demo.sarafan.repository.CommentRepo;
import org.homeproject.demo.sarafan.util.WsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
public class CommentService {

    private final CommentRepo commentRepo;
    private final BiConsumer<EventType, Comment> wsSender;

    @Autowired
    public CommentService(CommentRepo commentRepo, WsSender wsSender) {
        this.commentRepo = commentRepo;
        this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
    }

    public Comment create(Comment comment, User user) {

        comment.setAuthor(user);

        Comment commentFromDB = commentRepo.save(comment);
        wsSender.accept(EventType.CREATE, comment);

        return commentFromDB;
    }
}
