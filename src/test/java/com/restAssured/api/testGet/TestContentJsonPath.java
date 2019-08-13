package com.restAssured.api.testGet;

import static io.restassured.RestAssured.given;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class TestContentJsonPath extends BaseClass{
	
	@Test
	public void testContentJsonPath() throws URISyntaxException {
		/*
		 * Given Accept the content in Json format
		 * When I perform the GET method with id as 844
		 * Then the response should have BrandName Dell
		 * And the features should have 8GB RAM
		 */
		
		String response = given()
		.accept(ContentType.JSON)
		.when()
		.get(new URI("/find/844"))
		.thenReturn()
		.asString();
		System.out.println(response);
		
		JsonPath json = new JsonPath(response);
		Assert.assertEquals(844, json.getInt("Id"));
		Assert.assertEquals("Dell", json.getString("BrandName"));
		Assert.assertEquals("Latitude", json.getString("LaptopName"));
		Assert.assertTrue(json.getList("Features.Feature").contains("1TB Hard Drive"));
	}

}
