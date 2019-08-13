package com.httpClient.auth;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import com.rest.api.helper.Http_ClientHelper;
import com.rest.api.util.RestResponse;

public class TestGet_Secure {
	
	
	
	@Test
	public void testSecureGet() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		
//		--- username/password encoding using postman
		//headers.put("Authorization", "Basic YWRtaW46d2VsY29tZQ==");
		
//		-- username/password encoding using Base64 java class
		headers.put("Authorization", Base64.encodeBase64String("admin:welcome".getBytes()));
		RestResponse response = Http_ClientHelper.performGetRequest("http://localhost:8080/laptop-bag/webapi/secure/ping/Hello", headers);
		
		System.out.println(response.toString());
		
	}

}
