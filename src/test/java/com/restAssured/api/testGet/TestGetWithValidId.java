package com.restAssured.api.testGet;

import static io.restassured.RestAssured.given;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

import io.restassured.http.ContentType;

public class TestGetWithValidId extends BaseClass{
	
	@Test
	public void testGetWithId() throws URISyntaxException {
		/*
		 * Given I Accept the response in Json format
		 * when i perform the GET request with valid id
		 * Then status code 200 ok should be returned
		 "*/
		
		
		
		/*given()
		.accept(ContentType.JSON)
		.when()
		.get(new URI("/find/844"))
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);*/
		
		
		String body = given()
				.accept(ContentType.JSON)
				.when()
				.get(new URI("/find/844"))
				.thenReturn()
				.body()
				.asString();
		
		System.out.println(body);
		
	}

}
