package api.backend.controller;

import api.backend.config.UserAuthProvider;
import api.backend.dto.requestRecords.AuthenticationRequest;
import api.backend.dto.requestRecords.SignUpRequest;
import api.backend.dto.user.UserDTO;
import api.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private UserService userService;

    private final UserAuthProvider userAuthProvider;

    //method to log in a user
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody AuthenticationRequest request){
        UserDTO user= userService.login(request);
        user.setToken(userAuthProvider.creatToken(user));

        return  ResponseEntity.ok(user);
    }
    //method to register a user
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody SignUpRequest request){
        UserDTO user= userService.register(request);
        user.setToken(userAuthProvider.creatToken(user));

        return  ResponseEntity.created(URI.create("/user/"+user.getEmail())).body(user);}
}
