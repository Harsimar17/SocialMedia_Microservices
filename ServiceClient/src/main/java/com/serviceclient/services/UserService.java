package com.serviceclient.services;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.coreresources.required.UserDto;
import com.serviceclient.services.constants.Constants;
@Component
public class UserService
{
	public UserDto getUserByEmail(String email, String jwtToken) 
	{
		RestTemplate restTemplate = new RestTemplate();
	
		try 
		{
			String url = Constants.BASE_URL_FOR_USER_SERVICE + "getByEmail/" + email;
			
			return restTemplate.exchange(url, HttpMethod.GET, HeaderUtil.getHeaders(jwtToken), UserDto.class).getBody();
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	public UserDto getUserByUserId(String userId, String jwtToken) 
	{
		RestTemplate restTemplate = new RestTemplate();
		try 
		{
			String url = Constants.BASE_URL_FOR_USER_SERVICE + "getUserById/" + userId;

			return restTemplate.exchange(url, HttpMethod.GET, HeaderUtil.getHeaders(jwtToken), UserDto.class).getBody();
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
