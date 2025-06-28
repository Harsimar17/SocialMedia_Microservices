
package com.auth.service.AuthenticationService.security;

import org.springframework.stereotype.Service;
import com.auth.service.AuthenticationService.callmicroservice.UserService;
import com.clone.DTOs.UserDto;

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
