package com.restAssured.api.testGet;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

import io.restassured.http.ContentType;

public class TestContentWithNestedObject extends BaseClass{
	
	@Test
	public void testContentWithNestedObject() throws URISyntaxException {
		
		/*{
			"MyObject": {
				"BrandName": "Dell",
				"Features": {
					"Feature": [
						"8GB RAM",
						"1TB Hard Drive"
					]
				},
				"Id": 441,
				"LaptopName": {
					"Model": "Latitude"
				}
			}
		}
		============================
		
		---- nest object path ----
		MyObject.BrandName, "Dell"
		MyObject.Id, 441
		MyObject.LaptopName.Model, "Latitude
		
		--- testMethods -- 
		hasItem()
		hasItems()
		hasSize()
		=============================
		*/
		
		
		given()
		.accept(ContentType.JSON)
		.when()
		.get(new URI("/find/844"))
		.then()
		.body("Features.Feature", hasItems("8GB RAM", "1TB Hard Drive"))
		.body("Features.Feature", hasSize(2));
		
	}

}
