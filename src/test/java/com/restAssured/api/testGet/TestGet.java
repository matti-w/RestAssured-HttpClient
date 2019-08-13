package com.restAssured.api.testGet;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class TestGet extends BaseClass{

	
	@Test
	public void testGet() throws URISyntaxException {
		
		/*
		 * Given I Accept the response in Json format
		 * when i perform the GET request
		 */
		
		

		URI uri = new URI("/find/844");
		Response response = given()
				.accept(ContentType.JSON)
				.when()
				.get(uri);
		System.out.println(response.asString());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
