package de.htw.ETFootball.Controller;

import de.htw.ETFootball.repo.CommentRepository;
import de.htw.ETFootball.web.API.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer id, @RequestBody Comment updatedComment) {
        return commentRepository.findById(id)
                .map(comment -> {
                    comment.setTitle(updatedComment.getTitle());
                    comment.setContent(updatedComment.getContent());
                    Comment savedComment = commentRepository.save(comment);
                    return ResponseEntity.ok(savedComment);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
