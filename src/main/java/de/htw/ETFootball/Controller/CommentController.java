package de.htw.ETFootball.Controller;

import de.htw.ETFootball.repo.CommentRepository;
import de.htw.ETFootball.web.API.Comment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    private final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/comments")
    public Iterable<Comment> findAllComments() {
        return this.commentRepository.findAll();
    }

    @PostMapping("/comments")
    public Comment addOneComments(@RequestBody Comment comment) {
        return this.commentRepository.save(comment);
    }
}