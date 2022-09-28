package com.armaan.examserver.security.controller;

import com.armaan.examserver.security.config.JwtTokenUtil;
import com.armaan.examserver.security.entity.JwtTokenRequest;
import com.armaan.examserver.security.entity.JwtTokenResponse;
import com.armaan.examserver.security.serviceImpl.JwtUserDetailsServiceImpl;
import com.armaan.examserver.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin (origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("User disabled "+ e.getMessage());
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials "+ e.getMessage());
        }
    }

    // Generate Token
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtTokenRequest jwtTokenRequest) throws Exception {
        try {
            authenticate(jwtTokenRequest.getUsername(), jwtTokenRequest.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("User not found");
        }
        UserDetails userDetails = this.jwtUserDetailsServiceImpl.loadUserByUsername(jwtTokenRequest.getUsername());
        String token = this.jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtTokenResponse(token));
    }

    // Get current logged-in user; The user for which the token was generated
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal) { // User Entity not User from UserDetails
        return (User) this.jwtUserDetailsServiceImpl.loadUserByUsername(principal.getName());
    }
}
