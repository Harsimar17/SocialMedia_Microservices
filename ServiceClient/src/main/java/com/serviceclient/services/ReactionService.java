package com.serviceclient.services;

import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.coreresources.required.LikeDto;
import com.serviceclient.services.constants.Constants;
@Component
public class ReactionService {
	
	public List<LikeDto> getLikesOfPost(String postId, String jwtToken) 
	{
		RestTemplate restTemplate = new RestTemplate();
		
		try 
		{
			String url = Constants.BASE_URL_FOR_REACTION_SERVICE + "getLikes/" + postId;
			
			 ResponseEntity<List<LikeDto>> response = restTemplate.exchange(
			            url,
			            HttpMethod.GET,
			            HeaderUtil.getHeaders(jwtToken),
			            new ParameterizedTypeReference<List<LikeDto>>() {}
			        );

			List<LikeDto> likes = response.getBody();

			return likes;
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
