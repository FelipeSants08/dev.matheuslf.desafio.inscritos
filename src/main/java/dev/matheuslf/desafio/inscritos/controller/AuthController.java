package dev.matheuslf.desafio.inscritos.controller;

import dev.matheuslf.desafio.inscritos.config.TokenConfig;
import dev.matheuslf.desafio.inscritos.domain.dto.LoginRequest;
import dev.matheuslf.desafio.inscritos.domain.dto.LoginResponse;
import dev.matheuslf.desafio.inscritos.domain.dto.UserRegistreRequest;
import dev.matheuslf.desafio.inscritos.domain.dto.UserRegistreResponse;
import dev.matheuslf.desafio.inscritos.domain.model.User;
import dev.matheuslf.desafio.inscritos.domain.model.UserRole;
import dev.matheuslf.desafio.inscritos.repository.UserRepository;
import dev.matheuslf.desafio.inscritos.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final TokenConfig tokenConfig;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody LoginRequest request){

        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.senha());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        User user = (User) authentication.getPrincipal();

        String token = tokenConfig.generetedToken(user);
        return new LoginResponse(token);

    }

    @PostMapping("/registre")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegistreResponse registre(@RequestBody UserRegistreRequest request){

        User newUser = new User();
        newUser.setName(request.name());
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setUserRole(UserRole.ADMIN);
        userRepository.save(newUser);

        return new UserRegistreResponse(request.email(), request.name());
    }


}
