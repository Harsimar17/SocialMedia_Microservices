package com.serviceclient.services;

import org.springframework.web.client.RestTemplate;

import com.serviceclient.services.constants.Constants;
import com.socialmediaapp.required.UserDto;

public class UserService 
{
	public UserDto getUserByEmail(String email) 
	{
		RestTemplate restTemplate = new RestTemplate();
	
		try 
		{
			String url = Constants.BASE_URL_FOR_USER_SERVICE + "getByEmail/" + email;

			return restTemplate.getForObject(url, UserDto.class);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	public UserDto getUserByUserId(String userId) 
	{
		RestTemplate restTemplate = new RestTemplate();
		try 
		{
			String url = Constants.BASE_URL_FOR_USER_SERVICE + "getUserById/" + userId;

			return restTemplate.getForObject(url, UserDto.class);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	
	public UserDto authenticate(String username, String password) throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
		
		UserDto userDto = null;
		
		try 
		{
			String url = Constants.BASE_URL_FOR_USER_SERVICE + "getByEmailAndPassword/" + username + "/" + password;

			userDto =  restTemplate.getForObject(url, UserDto.class);
		}
		catch (Exception e)
		{
			throw e;
		}
		
		if( userDto == null) throw new Exception("User is not present");
		
		return userDto;
	}
}
