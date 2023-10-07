package com.ada.Bookshop.controller;

import com.ada.Bookshop.Infra.security.TokenService;
import com.ada.Bookshop.controller.dto.LoginRequest;
import com.ada.Bookshop.controller.dto.TokenResponse;
import com.ada.Bookshop.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest){

        var autheticate = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        var authentication = authenticationManager.authenticate(autheticate);
        var token = tokenService.tokenGenerate((User) authentication.getPrincipal());

        return ResponseEntity.ok().body(new TokenResponse(token));
    }
}
