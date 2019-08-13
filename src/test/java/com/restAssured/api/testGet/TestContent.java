package com.restAssured.api.testGet;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

import io.restassured.http.ContentType;

public class TestContent extends BaseClass{
	
	@Test
	public void testContent() throws URISyntaxException {
		
		/*
		 * Given Accept content in json format
		 * when I perform the GET method with valid id (
		 * then the response should have BrandName as Dell
		 */
		
		given()
		.accept(ContentType.JSON)
		.when()
		.get(new URI("/find/844"))
		.then()
		.body("BrandName", equalToIgnoringCase("Dell"), "Id", equalTo(844), "LaptopName", equalToIgnoringCase("Latitude"));
		
		
		
	}

}
