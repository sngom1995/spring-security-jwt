package com.samba.springsecurityjwt.demo;

import com.samba.springsecurityjwt.config.JwtService;
import com.samba.springsecurityjwt.domain.User;
import com.samba.springsecurityjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/demo")
public class DemoController {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    @GetMapping
    public ResponseEntity<User> demo(@RequestHeader("Authorization") String authorization){
        String token = authorization.replace("Bearer ", "");
        String email = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(email).orElseThrow();
        if(user.getEmail()==null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }
}
