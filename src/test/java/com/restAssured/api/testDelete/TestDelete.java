package com.restAssured.api.testDelete;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.junit.Test;

import com.restAssured.api.testGet.BaseClass;
import com.restAssured.api.testPost.Features;
import com.restAssured.api.testPost.LaptopBag;

import io.restassured.http.ContentType;

public class TestDelete extends BaseClass{
	
	
	@Test
	public void testDeleteMethod() {
		
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
		
		
		expect()
		.statusCode(HttpStatus.SC_OK)
		.when()
		.delete("/delete/"+id);
	}

}
