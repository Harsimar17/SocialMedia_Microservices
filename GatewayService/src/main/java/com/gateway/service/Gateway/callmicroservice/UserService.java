package com.gateway.service.Gateway.callmicroservice;

import com.gateway.service.Gateway.service.Service;
import com.socialmediaapp.required.UserDto;

@org.springframework.stereotype.Service
public class UserService implements Service{
	
	public UserDto getUserByEmail(String email) 
	{
		try 
		{
			com.serviceclient.services.UserService userService = new com.serviceclient.services.UserService();
			
			return userService.getUserByEmail(email);
			
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	@Override
	public UserDto authenticate(String username, String password) throws Exception {
		
		UserDto userDto = null;
		
		try 
		{
			com.serviceclient.services.UserService userService = new com.serviceclient.services.UserService();
			
			
			userDto = userService.authenticate(username, password);
		}
		catch (Exception e)
		{
			throw e;
		}
		
		if( userDto == null) throw new Exception("User is not present");
		
		return userDto;
	}
}
