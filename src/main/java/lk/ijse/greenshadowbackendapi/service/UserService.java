package lk.ijse.greenshadowbackendapi.service;

import lk.ijse.greenshadowbackendapi.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> getAllUsers();

    Optional<UserEntity> getUserByEmail(String email);

    void saveUser(UserEntity userEntity);

    void updateUser(String email, UserEntity userEntity);

    void deleteUser(String email);

    UserDetailsService userDetailsService();
}
