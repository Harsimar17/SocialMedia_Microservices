package com.socialmediaapp.required.JWTThings;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ValidateEveryRequest extends OncePerRequestFilter {
	
	@Autowired
	RouteValidator validator;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String pathInfo = request.getRequestURI();
		String internalCallHeader = request.getHeader("internal-call");
		
		if (!RouteValidator.openApiEndpoints.contains(pathInfo) && internalCallHeader == null)
		{
			if (request.getHeader(HttpHeaders.AUTHORIZATION) == null) {
				throw new RuntimeException("User not authorized");
			}
	
			String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
			if (authHeader != null && authHeader.startsWith("Bearer ")) {
				authHeader = authHeader.substring(7);
			}
			try {
				JWTTokenHelper tokenHelper = new JWTTokenHelper();
				tokenHelper.validateToken(authHeader);
	
			} catch (Exception e) {
				throw new RuntimeException("un authorized access to application");
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
