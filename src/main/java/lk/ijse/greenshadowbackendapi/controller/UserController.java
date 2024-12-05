package lk.ijse.greenshadowbackendapi.controller;

import lk.ijse.greenshadowbackendapi.entity.UserEntity;
import lk.ijse.greenshadowbackendapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    // Get all users
    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by email
    @GetMapping("/{email}")
    public UserEntity getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User with email " + email + " not found!"));
    }

    // Save a new user
    @PostMapping
    public void saveUser(@RequestBody UserEntity userEntity) {
        userService.saveUser(userEntity);
    }

    // Update an existing user
    @PutMapping("/{email}")
    public void updateUser(@PathVariable String email, @RequestBody UserEntity userEntity) {
        userService.updateUser(email, userEntity);
    }

    // Delete a user by email
    @DeleteMapping("/{email}")
    public void deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
    }
}
