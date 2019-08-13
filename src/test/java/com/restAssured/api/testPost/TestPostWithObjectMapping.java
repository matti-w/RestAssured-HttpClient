package com.restAssured.api.testPost;

import com.restAssured.api.testGet.BaseClass;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.junit.Test;

public class TestPostWithObjectMapping extends BaseClass{
	
	@Test
	public void testPostMethod() {
		/*
		 * create the mapping class
		 * create the object of mapping class
		 * initialize the field value present in mapping class
		 * send the object along with post request
		 */
		
		String id = (int)(1000*(Math.random()))+"";
		
		LaptopBag bag = new LaptopBag();
		bag.setBrandName("Apple");
		bag.setId(id);
		bag.setLaptopName("Mac Book");
		
		Features fet = new Features();
		fet.setFeature(new ArrayList<String>(Arrays.asList("8GB RAM","1TB Hard Drive")));
		bag.setFeatures(fet);
		
		
		

		
		given()
				.log()
				.all()   //.body(), .headers()
		.accept(ContentType.XML)
		.with()
		.contentType(ContentType.XML)
		.body(bag)
		.post("/add")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.body("Id", equalTo(Integer.parseInt(id)));
	
		
	}

}
