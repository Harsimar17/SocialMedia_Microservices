
package com.gateway.service.Gateway.security;

import org.springframework.stereotype.Service;

import com.coreresources.required.UserDto;
import com.gateway.service.Gateway.callmicroservice.UserService;

@Service
public class CustomUserDetailsService {

    public UserDto loadUserByUsername(String username)
    {
    	UserService userService = new UserService();
        UserDto user = userService.getUserByEmail(username);
        
        if (user == null) 
        {
            return null;
        }
        return user;
    }

}
