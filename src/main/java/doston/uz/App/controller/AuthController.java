package doston.uz.App.controller;
import doston.uz.App.dto.userDTO.SignUpDTO;
import doston.uz.App.dto.userDTO.UserCreationDTO;
import doston.uz.App.model.User;
import doston.uz.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpDTO signUpDTO) {

        UserCreationDTO userDTO = new UserCreationDTO();
        userDTO.setUsername(signUpDTO.getUsername());
        userDTO.setPassword(signUpDTO.getPassword());
        userDTO.setRoles(Collections.singleton("ROLE_USER"));

        User createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }
}