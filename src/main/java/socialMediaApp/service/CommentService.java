package socialMediaApp.service;

import socialMediaApp.domain.Comment;
import socialMediaApp.exception.UserException;

public interface CommentService {
    public Comment addComment(Comment comment, Integer postId, Integer userId) throws UserException;
    public Comment findCommentById(Integer commentId) throws UserException;
    public Comment likeComment(Integer commentId, Integer userId) throws UserException;
    public Comment unlikeComment(Integer commentId, Integer userId) throws UserException;
}
