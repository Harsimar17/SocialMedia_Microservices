package com.serviceclient.services;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.coreresources.required.PostDto;
import com.serviceclient.services.constants.Constants;

@Component
public class PostService{

	public PostDto getPostByPostId(String postId, String jwtToken) 
	{
		RestTemplate restTemplate = new RestTemplate();
		
		try 
		{
			String url = Constants.BASE_URL_FOR_POST_SERVICE + "getSpecificPost/" + postId;
			
			return restTemplate.exchange(url, HttpMethod.GET, HeaderUtil.getHeaders(jwtToken), PostDto.class).getBody();
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
