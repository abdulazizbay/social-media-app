package socialMediaApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import socialMediaApp.domain.Post;
import socialMediaApp.exception.UserException;
import socialMediaApp.service.PostService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Integer userId) {
        try {
            Post createdPost = postService.createPost(post, userId);
            return ResponseEntity.ok(createdPost);
        } catch (UserException e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @DeleteMapping("/delete/{userId}/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Integer userId, @PathVariable int postId) {
        try {
            postService.deletePost(userId, postId);
            return ResponseEntity.ok("Post deleted successfully");
        } catch (UserException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> findPostsByUserId(@PathVariable Integer userId) {
        try {
            List<Post> posts = postService.findPostByUserId(userId);
            return ResponseEntity.ok(posts);
        } catch (UserException e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable Integer postId) {
        try {
            Optional<Post> post = postService.findPostById(postId);
            return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).body(null));
        } catch (UserException e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @PostMapping("/all")
    public ResponseEntity<List<Post>> findAllPostsByUserIds(@RequestBody List<Integer> userIds) {
        try {
            List<Post> posts = postService.findAllPostsByUserIds(userIds);
            return ResponseEntity.ok(posts);
        } catch (UserException e) {
            return ResponseEntity.status(400).body(null);
        }
    }
}
