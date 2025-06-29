package com.serviceclient.services;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.serviceclient.services.constants.Constants;
import com.socialmediaapp.required.PostDto;

public class PostService{

	public PostDto getPostByPostId(String postId) 
	{
		RestTemplate restTemplate = new RestTemplate();
		
		try 
		{
			String url = Constants.BASE_URL_FOR_POST_SERVICE + "getSpecificPost/" + postId;
			
			return restTemplate.exchange(url, HttpMethod.GET, HeaderUtil.getHeaders(), PostDto.class).getBody();
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
