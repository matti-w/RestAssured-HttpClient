package com.restAssured.api.testGet;

import static io.restassured.RestAssured.given;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.http.ContentType;

public class TestStatusCode extends BaseClass{
	
	@Test
	public void testStatusCode() throws URISyntaxException {
		/*
		 * Given I Accept the response in Json format
		 * when i perform the GET request
		 * Then status code 200 ok should be retured
		 "*/
		
		
		// .thenReturn()-- to capture response code or content vs 
	    // .then() -- to directly validate response
	
		
		int code = given()
		.accept(ContentType.JSON)
		.when()
		.get(new URI("/find/844"))
		.thenReturn()
		.statusCode();
		System.out.println(code);
		Assert.assertEquals(HttpStatus.SC_OK, code);
		
		// using then()
		
//		given()
//		.accept(ContentType.JSON)
//		.when()
//		.get(new URI(url))
//		.then()
//		.assertThat()
//		.statusCode(HttpStatus.SC_OK);
		
	}

}
