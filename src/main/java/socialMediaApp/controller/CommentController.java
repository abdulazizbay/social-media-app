package socialMediaApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import socialMediaApp.domain.Comment;
import socialMediaApp.exception.UserException;
import socialMediaApp.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add/{postId}/{userId}")
    public ResponseEntity<?> addComment(@RequestBody Comment comment, @PathVariable Integer postId, @PathVariable Integer userId) {
        try {
            Comment createdComment = commentService.addComment(comment, postId, userId);
            return ResponseEntity.ok(createdComment);
        } catch (UserException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<?> findCommentById(@PathVariable Integer commentId) {
        try {
            Comment comment = commentService.findCommentById(commentId);
            return ResponseEntity.ok(comment);
        } catch (UserException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/like/{commentId}/{userId}")
    public ResponseEntity<?> likeComment(@PathVariable Integer commentId, @PathVariable Integer userId) {
        try {
            Comment likedComment = commentService.likeComment(commentId, userId);
            return ResponseEntity.ok(likedComment);
        } catch (UserException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/unlike/{commentId}/{userId}")
    public ResponseEntity<?> unlikeComment(@PathVariable Integer commentId, @PathVariable Integer userId) {
        try {
            Comment unlikedComment = commentService.unlikeComment(commentId, userId);
            return ResponseEntity.ok(unlikedComment);
        } catch (UserException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
