package com.restAssured.api.testPut;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.junit.Test;

import com.restAssured.api.testGet.BaseClass;
import com.restAssured.api.testPost.Features;
import com.restAssured.api.testPost.LaptopBag;

import io.restassured.http.ContentType;

public class TestPut extends BaseClass{
	
	
	@Test
	public void testput() throws URISyntaxException {
		/*
		 * Given Accept the content in JSON format
		 * With Content Type as JSON
		 * When I perform the PUT with valid id
		 * Then HTTP status code 200 ok should return
		 * And data should be updated
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
		.everything()   //.body(), .headers()
		.accept(ContentType.XML)
		.with()
		.contentType(ContentType.XML)
		.body(bag)
		.post("/add");
		
		//--- perform update in arraylist
		fet.setFeature(new ArrayList<String>(Arrays.asList("8GB RAM","1TB Hard Drive", "17 inch Touch Screen")));
		bag.setFeatures(fet);
		
		given()
		.accept(ContentType.XML)
		.with()
		.contentType(ContentType.XML)
		.and()
		.body(bag)
		.put("/update")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);


		given()
		.accept(ContentType.JSON)
		.when()
		.get(new URI("/find/"+id))
		.then()
		.body("Features.Feature", hasItems("17 inch Touch Screen"))
		.body("Features.Feature", hasSize(3));
		
	}

}
