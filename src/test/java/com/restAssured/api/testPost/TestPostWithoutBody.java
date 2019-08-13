package com.restAssured.api.testPost;

import com.restAssured.api.testGet.BaseClass;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.apache.http.HttpStatus;
import org.junit.Test;

public class TestPostWithoutBody extends BaseClass{
	
	@Test
	public void testPostMethod() {
		/*
		 * given Accept the content in xml format
		 * With content type as json
		 * When I perform the Post request without body
		 * Then Status code 400 should be returned
		 * 
		 */
		
		String id = (int)(1000*(Math.random()))+"";
		String jsonBody = "{ \"BrandName\":\"Lenovo\", \"Features\":{ \"Feature\":[ \"8GB RAM\", \"1TB Hard Drive\"] }, \"Id\":"+id+", \"LaptopName\":\"IdeaPad\" }";
		
		
		given()
		.accept(ContentType.XML)
		.with()
		.contentType(ContentType.JSON)
		.and()
		.when()
		.post("/add")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_BAD_REQUEST);
	
		
	}

}
