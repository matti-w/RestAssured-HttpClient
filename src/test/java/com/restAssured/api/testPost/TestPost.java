package com.restAssured.api.testPost;

import com.restAssured.api.testGet.BaseClass;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.apache.http.HttpStatus;
import org.junit.Test;

public class TestPost extends BaseClass{
	
	@Test
	public void testPostMethod() {
		/*
		 * given Accept the content in xml format
		 * With content type as json
		 * And boyd
		 * When I perform the Post request
		 * Then Status code 200 OK should be returned
		 * And the response should contain the ID
		 */
		
		String id = (int)(1000*(Math.random()))+"";
		String jsonBody = "{ \"BrandName\":\"Lenovo\", \"Features\":{ \"Feature\":[ \"8GB RAM\", \"1TB Hard Drive\"] }, \"Id\":"+id+", \"LaptopName\":\"IdeaPad\" }";
		
		
		given()
		.accept(ContentType.XML)
		.with()
		.contentType(ContentType.JSON)
		.and()
		.body(jsonBody)
		.when()
		.post("/add")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.and()
		.body("Laptop.Id", equalTo(id));
		
	}

}
