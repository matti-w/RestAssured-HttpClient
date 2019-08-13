package com.restAssured.api.testPost;

import com.restAssured.api.testGet.BaseClass;

import io.restassured.http.ContentType;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

public class TestPostWithWithDeserialization extends BaseClass{
	
	/*
	 * Object to request body = OM Serialization
	 * body (response body) to Object = Deserialization
	 * create mapping class same as response body
	 * use @XmlRootElement, @XmlElement
	 */
	
	@Test
	public void testPostMethod() {
		/*
		 * create the mapping class same as response body
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
		
		
		

		
				LaptopBag responseBag = given()
				.log()
				.body()
				.accept(ContentType.XML)
				.with()
				.contentType(ContentType.XML)
				.body(bag)
				.post("/add")
				.thenReturn()
				.as(LaptopBag.class);
				
				Assert.assertEquals("Apple", responseBag.getBrandName());
				Assert.assertEquals(id, responseBag.getId());
				
		
	
		
	}

}
