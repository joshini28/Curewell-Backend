package com.thejoshini.curewell.service;

import com.thejoshini.curewell.payload.LoginDto;
import com.thejoshini.curewell.payload.RegisterDto;


public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
