package socialMediaApp.service;

import socialMediaApp.domain.Post;
import socialMediaApp.exception.UserException;

import java.util.List;
import java.util.Optional;

public interface PostService {
    public Post createPost(Post post, Integer userId) throws UserException;
    public Post deletePost(Integer userId, int postId) throws UserException;
    public List<Post> findPostByUserId(Integer userId) throws UserException;
    public Optional<Post> findPostById(Integer postId) throws UserException;
    public List<Post> findAllPostsByUserIds(List<Integer> userIds) throws UserException;
}
