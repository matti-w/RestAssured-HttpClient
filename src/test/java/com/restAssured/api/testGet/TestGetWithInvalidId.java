package com.restAssured.api.testGet;

import static io.restassured.RestAssured.given;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpStatus;
import org.junit.Test;

import io.restassured.http.ContentType;

public class TestGetWithInvalidId extends BaseClass{
	
	@Test
	public void testGetWithInvalidId() throws URISyntaxException {
		/*
		 * Given I Accept the response in Json format
		 * when i perform the GET request with invalid id
		 * Then status code 404 not found should be returned
		 "*/
		
		given()
		.accept(ContentType.JSON)
		.when()
		.get(new URI("/find/511"))
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_NOT_FOUND);
	}

}
