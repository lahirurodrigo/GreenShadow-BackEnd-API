package lk.ijse.greenshadowbackendapi.service.impl;

import lk.ijse.greenshadowbackendapi.dao.UserDAO;
import lk.ijse.greenshadowbackendapi.entity.UserEntity;
import lk.ijse.greenshadowbackendapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import lk.ijse.greenshadowbackendapi.exception.UserNotFound;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userRepository;

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> getUserByEmail(String email) {
        return userRepository.findById(email);
    }

    @Override
    public void saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public void updateUser(String email, UserEntity userEntity) {
        if (userRepository.existsById(email)) {
            userRepository.save(userEntity);
        } else {
            throw new RuntimeException("User with email " + email + " not found!");
        }
    }

    @Override
    public void deleteUser(String email) {
        if (userRepository.existsById(email)) {
            userRepository.deleteById(email);
        } else {
            throw new RuntimeException("User with email " + email + " not found!");
        }
    }

    @Override
    public UserDetailsService userDetailsService() {

        return userName ->
                userRepository.findByEmail(userName)
                        .orElseThrow(()-> new UserNotFound("User Not Found"));
    }
}
