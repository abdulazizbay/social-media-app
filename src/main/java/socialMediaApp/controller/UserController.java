package socialMediaApp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import socialMediaApp.domain.User;
import socialMediaApp.exception.UserException;
import socialMediaApp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (UserException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        try {
            User user = userService.findByUserId(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/{reqUserId}/follow/{followUserId}")
    public ResponseEntity<String> followUser(@PathVariable int reqUserId, @PathVariable int followUserId) {
        try {
            String username = userService.followUser(reqUserId, followUserId);
            return new ResponseEntity<>("You are now following " + username, HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{reqUserId}/unfollow/{followUserId}")
    public ResponseEntity<String> unfollowUser(@PathVariable int reqUserId, @PathVariable int followUserId) {
        try {
            String message = userService.unfollowUser(reqUserId, followUserId);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUser(@RequestParam String query) {
        try {
            List<User> users = userService.searchUser(query);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserDetails(@PathVariable int id, @RequestBody User updatedUser) {
        try {
            User updated = userService.updateUserDetails(id, updatedUser);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
