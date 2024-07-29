package socialMediaApp.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socialMediaApp.domain.Comment;
import socialMediaApp.domain.Post;
import socialMediaApp.domain.User;
import socialMediaApp.exception.UserException;
import socialMediaApp.repository.CommentRepository;
import socialMediaApp.repository.PostRepository;
import socialMediaApp.repository.UserRepository;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Transactional
    @Override
    public Comment addComment(Comment comment, Integer postId, Integer userId) throws UserException {
        Optional<User> OptionalUser = userRepository.findById(userId);
        if (OptionalUser.isEmpty()) {
            throw new UserException("User not found");
        }
        Optional<Post> OptionalPost = postRepository.findById(postId);
        if (OptionalPost.isEmpty()) {
            throw new UserException("Post not found");
        }
        User user = OptionalUser.get();
        Post post = OptionalPost.get();
        comment.setUser(user);
        comment.setPost(post);
        Comment createdComment = commentRepository.save(comment);
        post.getComments().add(createdComment);
        postRepository.save(post);
        return createdComment;
    }

    @Override
    public Comment findCommentById(Integer commentId) throws UserException {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            return optionalComment.get();
        }
        throw new UserException("Comment not found");
    }
    @Transactional
    @Override
    public Comment likeComment(Integer commentId, Integer userId) throws UserException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserException("User not found");
        }
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isEmpty()) {
            throw new UserException("Comment not found");
        }
        User user = optionalUser.get();
        Comment comment = optionalComment.get();
        comment.getLikedByUsers().add(user);
        return commentRepository.save(comment);
    }
    @Transactional
    @Override
    public Comment unlikeComment(Integer commentId, Integer userId) throws UserException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserException("User not found");
        }
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isEmpty()) {
            throw new UserException("Comment not found");
        }
        User user = optionalUser.get();
        Comment comment = optionalComment.get();
        comment.getLikedByUsers().remove(user);
        return commentRepository.save(comment);
    }
}
