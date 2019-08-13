package com.restAssured.api.testGet;

import static io.restassured.RestAssured.given;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestGetWithIdWithHeaders extends BaseClass{
	
	@Test
	public void testGetWithIdWithHeaders() throws URISyntaxException {
		/*
		 * Given I Accept the response in Json format
		 * when i perform the GET request with valid id
		 * Then status code 200 ok should be returned
		 "*/
		
		Map<String, String> header = new HashMap<String, String>();
		header.put("Accept", "application/xml");
		
		String body = given()
				.headers(header)
				.when()
				.get(new URI("/find/844"))
				.thenReturn()
				.body()
				.asString();
		
		System.out.println(body);
		
	}

}
