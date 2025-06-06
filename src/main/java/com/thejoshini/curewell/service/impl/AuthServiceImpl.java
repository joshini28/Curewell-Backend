package com.thejoshini.curewell.service.impl;

import com.thejoshini.curewell.entity.User;
import com.thejoshini.curewell.exception.AuthException;
import com.thejoshini.curewell.payload.LoginDto;
import com.thejoshini.curewell.payload.RegisterDto;
import com.thejoshini.curewell.repository.UserRepository;
import com.thejoshini.curewell.security.JwtTokenProvider;
import com.thejoshini.curewell.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
@Autowired
private     UserRepository userRepository;
@Autowired
private PasswordEncoder passwordEncoder;
@Autowired
private AuthenticationManager authenticationManager;
@Autowired
private JwtTokenProvider jwtTokenProvider;
    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        System.out.println(token);
        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {
if(userRepository.existsByUsername(registerDto.getUsername())){
    throw  new AuthException(HttpStatus.BAD_REQUEST,"username already taken");

}
if(userRepository.existsByEmail(registerDto.getEmail())){
    throw new AuthException(HttpStatus.BAD_REQUEST,"email already exists");
}
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userRepository.save(user);
        return "user Registered sucessfully";
    }
}
