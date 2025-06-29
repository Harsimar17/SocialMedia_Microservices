package com.gateway.service.Gateway.service;

import java.util.*;

import com.coreresources.required.UserDto;

public interface Service {

    
    
    UserDto getUserByEmail(String email);
    
    UserDto authenticate(String username, String password) throws Exception;
}
