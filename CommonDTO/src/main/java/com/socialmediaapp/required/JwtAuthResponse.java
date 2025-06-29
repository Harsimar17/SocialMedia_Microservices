package com.socialmediaapp.required;


public class JwtAuthResponse {
    String token;
    public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}

	UserDto user;
}
