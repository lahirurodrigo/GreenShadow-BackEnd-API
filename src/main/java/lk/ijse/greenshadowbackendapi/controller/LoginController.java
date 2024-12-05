package lk.ijse.greenshadowbackendapi.controller;

import lk.ijse.greenshadowbackendapi.dto.impl.LoginDTO;
import lk.ijse.greenshadowbackendapi.dto.impl.UserDTO;
import lk.ijse.greenshadowbackendapi.exception.UserNotFound;
import lk.ijse.greenshadowbackendapi.response.JWTAuthResponse;
import lk.ijse.greenshadowbackendapi.service.impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signUp")
    public ResponseEntity<JWTAuthResponse> signUp(@RequestBody UserDTO signUp){
        System.out.println(signUp.toString());
        try {
            return ResponseEntity.ok(authenticationService.signUp(signUp));
        }catch (UserNotFound e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/signIn")
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody LoginDTO signIn){
        return ResponseEntity.ok(authenticationService.signIn(signIn));
    }

    @PostMapping("/refresh/{token}")
    public ResponseEntity<JWTAuthResponse> refreshToken(@PathVariable("token") String token){
        return ResponseEntity.ok(authenticationService.refreshToken(token));
    }
}
