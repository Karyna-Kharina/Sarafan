package org.homeproject.demo.sarafan.service;

import org.homeproject.demo.sarafan.domain.Comment;
import org.homeproject.demo.sarafan.domain.User;
import org.homeproject.demo.sarafan.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepo commentRepo;

    @Autowired
    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public Comment create(Comment comment, User user) {

        comment.setAuthor(user);
        commentRepo.save(comment);

        return comment;
    }
}
