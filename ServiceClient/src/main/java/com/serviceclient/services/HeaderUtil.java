package com.serviceclient.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class HeaderUtil {

	public static HttpEntity<Void> getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("internal-call", "T");
		
		HttpEntity<Void> getEntity  = new HttpEntity<>(headers);
		return getEntity;
	}

}
