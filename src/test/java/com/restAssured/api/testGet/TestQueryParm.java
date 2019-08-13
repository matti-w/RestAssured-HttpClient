package com.restAssured.api.testGet;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static org.hamcrest.Matchers.hasItems;

import java.net.URISyntaxException;

import org.apache.http.HttpStatus;
import org.junit.Test;

import io.restassured.http.ContentType;

public class TestQueryParm extends BaseClass{
	
	
	@Test
	public void testQueryParm() throws URISyntaxException {
		/*
		 * Given Accept the content in Json format
		 * And id as 844
		 * And LaptopName as Latitude
		 * When I perform the GET method
		 * Then the status code 200 OK should be returned
		 * And the response content should have id as 844
		 * And Feature list should contain 1TB Hard Drive
		 */
		
		
		given()
		.accept(ContentType.JSON)
		.param("id", "844")
		.param("laptopName", "Latitude")
		.when()
		.get("/query")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.and()
		.assertThat()
		.body("Features.Feature", hasItems("8GB RAM", "1TB Hard Drive"));
		
		
		
	}

}
