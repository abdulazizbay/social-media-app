package socialMediaApp.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socialMediaApp.domain.User;
import socialMediaApp.exception.UserException;
import socialMediaApp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public User registerUser(User user) throws UserException {
        Optional<User> isEmailExist = userRepository.findByEmail(user.getEmail());
        if(isEmailExist.isPresent()){
            throw new UserException("Email already exists");
        }
        Optional<User> isUsernameExist = userRepository.findByUsername(user.getUsername());
        if(isUsernameExist.isPresent()){
            throw new UserException("Username is already taken");
        }
        if(user.getEmail()==null || user.getPassword()==null || user.getUsername() == null || user.getName()==null){
            throw new UserException("All fields are required");
        }
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setUsername(user.getUsername());
        newUser.setName(user.getName());
        return userRepository.save(newUser);
    }

    @Override
    public User findByUserId(int id) throws UserException {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new UserException("user not found with id: "+ id);
    }

    @Override
    public User findByProfile(String token) throws UserException {
        return null;
    }

    @Override
    public User findByUsername(String username) throws UserException {
        return null;
    }


    @Override
    @Transactional
    public String followUser(int reqUserId, int followUserId) throws UserException {
        User reqUser = findByUserId(reqUserId);
        User followUser = findByUserId(followUserId);

        reqUser.getFollowing().add(followUser);
        followUser.getFollowers().add(reqUser);
        userRepository.save(reqUser);
        userRepository.save(followUser);
        return followUser.getUsername();
    }

    @Override
    public String unfollowUser(int reqUserId, int followUserId) throws UserException {
        User reqUser = findByUserId(reqUserId);
        User followUser = findByUserId(followUserId);

        reqUser.getFollowing().remove(followUser);
        followUser.getFollowers().remove(reqUser);
        userRepository.save(reqUser);
        userRepository.save(followUser);
        return "You have unfollowed " + followUser.getUsername();
    }

    @Override
    public List<User> findByUserIds(List<Integer> userIds) throws UserException {
        List<User> users = userRepository.findAllByUserIds(userIds);
        return users;
    }

    @Override
    public List<User> searchUser(String query) throws UserException {
        List<User> users = userRepository.findByQuery(query);
        if(users.isEmpty()){
            throw new UserException("user not found");
        }
        return users;
    }
    @Transactional
    @Override
    public User updateUserDetails(int id, User updatedUser) throws UserException {
        Optional<User> existingUserOpt = userRepository.findById(id);

        if(existingUserOpt.isEmpty()){
            throw new UserException("User with ID " + id + " not found");
        }
        User user = existingUserOpt.get();
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setMobile(updatedUser.getMobile());
        user.setWebsite(updatedUser.getWebsite());
        user.setBio(updatedUser.getBio());
        user.setGender(updatedUser.getGender());
        user.setImage(updatedUser.getImage());
        user.setPassword(updatedUser.getPassword());
        return userRepository.save(user);
    }
}
