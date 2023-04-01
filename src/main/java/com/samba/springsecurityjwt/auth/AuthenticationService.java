package com.samba.springsecurityjwt.auth;


import com.samba.springsecurityjwt.config.JwtService;
import com.samba.springsecurityjwt.domain.Role;
import com.samba.springsecurityjwt.domain.User;
import com.samba.springsecurityjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user1 = userRepository.findByEmail(request.getEmail());
        if (user1.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var authToke = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(authToke)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var authToke = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(authToke)
                .build();
    }
}
