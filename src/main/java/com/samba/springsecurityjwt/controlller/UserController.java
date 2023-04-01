package com.samba.springsecurityjwt.controlller;


import com.samba.springsecurityjwt.config.JwtService;
import com.samba.springsecurityjwt.domain.User;
import com.samba.springsecurityjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/api/v1/users")
public class UserController {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<User> getUserById(@RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer ", "");
        String email = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(email).orElseThrow();
        if(user.getEmail()==null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }
}
