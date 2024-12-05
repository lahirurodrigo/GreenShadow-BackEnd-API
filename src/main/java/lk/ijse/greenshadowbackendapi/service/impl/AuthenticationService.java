package lk.ijse.greenshadowbackendapi.service.impl;

import lk.ijse.greenshadowbackendapi.dao.UserDAO;
import lk.ijse.greenshadowbackendapi.dto.impl.LoginDTO;
import lk.ijse.greenshadowbackendapi.dto.impl.UserDTO;
import lk.ijse.greenshadowbackendapi.entity.UserEntity;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;
import lk.ijse.greenshadowbackendapi.exception.UserNotFound;
import lk.ijse.greenshadowbackendapi.response.JWTAuthResponse;
import lk.ijse.greenshadowbackendapi.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserDAO userDAO;
    private final Mapping mapping;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public JWTAuthResponse signUp(UserDTO signUp) throws DataPersistException {
        signUp.setPassword(passwordEncoder.encode(signUp.getPassword()));
        UserEntity user = mapping.toUserEntity(signUp);
        if (userDAO.existsById(signUp.getEmail())) throw new DataPersistException("Staff with the same ID already exists!");;
        userDAO.save(user);
        var jwtToken = jwtService.generateToken(user);
        return JWTAuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public JWTAuthResponse signIn(LoginDTO signIn) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signIn.getEmail(),
                        signIn.getPassword()
                )
        );
        UserEntity user = userDAO.findByEmail(signIn.getEmail()).orElseThrow(() -> new UserNotFound("User not Found"));
        var jwtToken = jwtService.generateToken(user);
        return JWTAuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public JWTAuthResponse refreshToken(String token) {
        String email = jwtService.extractUserName(token);
        UserEntity user = userDAO.findByEmail(email).orElseThrow(() -> new UserNotFound("User not Found"));
        var jwtToken = jwtService.generateToken(user);
        return JWTAuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
