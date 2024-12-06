package lk.ijse.greenshadowbackendapi.service;

import lk.ijse.greenshadowbackendapi.dto.impl.UserDTO;
import lk.ijse.greenshadowbackendapi.entity.UserEntity;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> getAllUsers();

    Optional<UserEntity> getUserByEmail(String email);

    void saveUser(UserDTO userDTO) throws DataPersistException;

    void updateUser(String email, UserDTO userDTO) throws DataPersistException;

    void deleteUser(String email);

    UserDetailsService userDetailsService();
}
