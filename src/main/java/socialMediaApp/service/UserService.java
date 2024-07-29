package socialMediaApp.service;

import socialMediaApp.domain.User;
import socialMediaApp.exception.UserException;

import java.util.List;

public interface UserService {
    public User registerUser(User user) throws UserException;
    public User findByUserId(int id) throws UserException;
    public User findByProfile(String token) throws UserException;
    public User findByUsername(String username) throws UserException;
    public String followUser(int reqUserId, int followUserId) throws UserException;
    public String unfollowUser(int reqUserId, int followUserId) throws UserException;
    public List<User> findByUserIds(List<Integer> userIds) throws UserException;
    public List<User> searchUser(String query) throws UserException;
    public User updateUserDetails(int id, User updatedUser) throws UserException;
}
