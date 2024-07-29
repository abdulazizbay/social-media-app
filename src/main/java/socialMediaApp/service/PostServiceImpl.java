package socialMediaApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socialMediaApp.domain.Post;
import socialMediaApp.domain.User;
import socialMediaApp.exception.UserException;
import socialMediaApp.repository.PostRepository;
import socialMediaApp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Post createPost(Post post, Integer userId) throws UserException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserException("user not exists");
        }
        User user = userOptional.get();
        post.setUser(user);
        return postRepository.save(post);
    }

    @Override
    public Post deletePost(Integer userId, int postId) throws UserException {
        return null;
    }

    @Override
    public List<Post>  findPostByUserId(Integer userId) throws UserException {
        List<Post> posts = postRepository.findByUserId(userId);
        if (posts.isEmpty()) {
            throw new UserException("user do not have any posts");
        }

        return posts;
    }

    @Override
    public Optional<Post> findPostById(Integer postId) throws UserException {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isEmpty()) {
            throw new UserException("post with this id does not exist");
        }
        return postOptional;
    }

    @Override
    public List<Post> findAllPostsByUserIds(List<Integer> userIds) throws UserException {
        List<Post> posts = postRepository.findAllPostByUserId(userIds);
        return posts;
    }

}
