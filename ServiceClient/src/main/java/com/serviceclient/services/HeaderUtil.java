package com.serviceclient.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class HeaderUtil {

	public static HttpEntity<Void> getHeaders(String jwtToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.AUTHORIZATION, jwtToken);
		
		HttpEntity<Void> getEntity  = new HttpEntity<>(headers);
		return getEntity;
	}
	

	public static HttpEntity<Void> getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("interna-call", "T");
		
		HttpEntity<Void> getEntity  = new HttpEntity<>(headers);
		return getEntity;
	}

}
