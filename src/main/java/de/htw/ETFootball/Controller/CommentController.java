package de.htw.ETFootball.Controller;

import de.htw.ETFootball.repo.CommentRepository;
import de.htw.ETFootball.web.API.Comment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = {"https://etfootball-frontend.onrender.com", "http://localhost:5173", "http://localhost:8080"})
public class CommentController {

    private final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @PostMapping("/")
    public Comment addOneComments(@RequestBody Comment comment) {
        return this.commentRepository.save(comment);
    }

    @GetMapping("/")
    public Iterable<Comment> findAllComments() {
        return this.commentRepository.findAll();
    }
}
