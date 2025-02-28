package ProductBackendTest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ProductBackendTest.dto.UserDTO;
import ProductBackendTest.dto.UserLoginDTO;
import ProductBackendTest.dto.UserRegistrationDTO;
import ProductBackendTest.service.UserService;


@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        try {
            UserDTO newUser = userService.registerUser(userRegistrationDTO);
            return ResponseEntity.ok(newUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body("Username is already in use.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserLoginDTO loginDTO) {
        return userService.login(loginDTO.getUsername(), loginDTO.getPassword())
                .map(u -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Login successful");
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.status(401).body(Map.of("error", "Incorrect username or password")));
    }
}
