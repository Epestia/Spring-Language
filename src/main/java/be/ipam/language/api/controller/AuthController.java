package be.ipam.language.api.controller;

import be.ipam.language.api.dto.jwt.JwtTokenRequestDto;
import be.ipam.language.api.dto.jwt.JwtTokenResponseDto;
import be.ipam.language.bll.services.UserService;
import be.ipam.language.config.security.jwt.JwtTokenUtil;
import be.ipam.language.dao.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Scope("singleton")
public class AuthController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponseDto> login(@RequestBody JwtTokenRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserEntity user = userService.findByEmail(request.getEmail());
        String token = jwtTokenUtil.generateToken(user.getEmail(), user.getRoles());
        return ResponseEntity.ok(new JwtTokenResponseDto(token));
    }
}
