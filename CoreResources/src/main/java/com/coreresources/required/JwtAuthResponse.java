package com.coreresources.required;


public class JwtAuthResponse {
    String token;
    UserDto user;
    String message;
	String statusCode = "200";
    
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

}
