package lk.ijse.greenshadowbackendapi.controller;

import lk.ijse.greenshadowbackendapi.dto.impl.UserDTO;
import lk.ijse.greenshadowbackendapi.entity.UserEntity;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;
import lk.ijse.greenshadowbackendapi.exception.UserNotFound;
import lk.ijse.greenshadowbackendapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        try{
            userService.saveUser(userDTO);
            return new ResponseEntity<UserDTO>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<UserDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing user
    @PutMapping("/{email}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String email, @RequestBody UserDTO userDTO) {
        try {
            userService.updateUser(email, userDTO);
            return new ResponseEntity<UserDTO>(userDTO, HttpStatus.NO_CONTENT);
        } catch (UserNotFound e) {
            return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity<UserDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // Delete a user by email
    @DeleteMapping("/{email}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable String email) {
        try {
            userService.deleteUser(email);
            return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
        } catch (UserNotFound e) {
            return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<UserDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
