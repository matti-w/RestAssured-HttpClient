package com.restAssured.api.auth;

import static io.restassured.RestAssured.given;

import org.junit.Test;

import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
public class TestGetWith_SSL {
	
	
	/*
	 * 1. by pass the certificate check
	 * 
	 * 2. supply valid certificate
	 */
	
	
	@Test
	public void testGetWithoutCertificate() {
		
		String response = given()
		.accept(ContentType.XML)
		.when()
		.get("http://localhost:8080/laptop-bag/webapi/sslres/all")
		.thenReturn()
		.asString();
	}
	
	@Test
	public void testGet() {
	
/*
 * .relaxedHTTPSValidation()
 * This means that you'll trust all hosts regardless if the SSL certificate is invalid.
 * By using this method you don't need to specify a keystore 
 */

		String s = given()
				.relaxedHTTPSValidation()
		.accept(ContentType.XML)
		.when()
		.get("/all")
		.thenReturn()
		.asString();
		System.out.println(s);
		
		/**
		 * 1. To bypass the certificate check
		 * 2. Is to supply valid certificate along with request
		 * 
		 * */
		
	}
	
	@Test
	public void testGetWithCertificate() {
		
		given()
		.log()
		.all()
		.when()
		.get("/all")
		.then()
		.assertThat()
		.body("$", is(notNullValue()));
		
	}

}
