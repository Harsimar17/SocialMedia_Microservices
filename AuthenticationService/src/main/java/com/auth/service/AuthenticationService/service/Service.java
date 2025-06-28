package com.auth.service.AuthenticationService.service;

import java.util.*;

import com.clone.DTOs.UserDto;

public interface Service {

    
    
    UserDto getUserByEmail(String email);
    
    UserDto authenticate(String username, String password) throws Exception;
}
