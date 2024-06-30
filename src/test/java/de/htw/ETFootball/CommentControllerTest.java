package de.htw.ETFootball;

import de.htw.ETFootball.Controller.CommentController;
import de.htw.ETFootball.repo.CommentRepository;
import de.htw.ETFootball.web.API.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class CommentControllerTest {

    @InjectMocks
    private CommentController commentController;

    @Mock
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddOneComment() {
        Comment comment = new Comment();
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        Comment result = commentController.addOneComments(comment);

        assertEquals(comment, result);
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    void testFindAllComments() {
        Iterable<Comment> comments = mock(Iterable.class);
        when(commentRepository.findAll()).thenReturn(comments);

        Iterable<Comment> result = commentController.findAllComments();

        assertEquals(comments, result);
        verify(commentRepository, times(1)).findAll();
    }

    @Test
    void testUpdateComment() {
        Comment existingComment = new Comment();
        existingComment.setId(1L);
        existingComment.setTitle("Old Title");
        existingComment.setContent("Old Content");

        Comment updatedComment = new Comment();
        updatedComment.setTitle("New Title");
        updatedComment.setContent("New Content");

        when(commentRepository.findById(anyInt())).thenReturn(Optional.of(existingComment));
        when(commentRepository.save(any(Comment.class))).thenReturn(existingComment);

        ResponseEntity<Comment> responseEntity = commentController.updateComment(1, updatedComment);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("New Title", responseEntity.getBody().getTitle());
        assertEquals("New Content", responseEntity.getBody().getContent());
    }

    @Test
    void testUpdateCommentNotFound() {
        Comment updatedComment = new Comment();
        updatedComment.setTitle("New Title");
        updatedComment.setContent("New Content");

        when(commentRepository.findById(anyInt())).thenReturn(Optional.empty());

        ResponseEntity<Comment> responseEntity = commentController.updateComment(1, updatedComment);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(commentRepository, never()).save(any(Comment.class));
    }

    @Test
    void testDeleteComment() {
        when(commentRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(commentRepository).deleteById(anyInt());

        ResponseEntity<Void> responseEntity = commentController.deletePost(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(commentRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteCommentNotFound() {
        when(commentRepository.existsById(anyInt())).thenReturn(false);

        ResponseEntity<Void> responseEntity = commentController.deletePost(1);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(commentRepository, never()).deleteById(1);
    }
}
