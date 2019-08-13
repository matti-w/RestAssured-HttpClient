package com.restAssured.api.auth;

import static io.restassured.RestAssured.*;

import org.apache.http.HttpStatus;
import org.junit.Test;



public class TestGetWithSecure extends BaseSecureClass{
	
	
	@Test
	public void testGetWithoutAuth() {
		
		expect()
		.log()
		.all()
		.statusCode(HttpStatus.SC_UNAUTHORIZED)
		.when()
		.get("/all");
	}
	
	
	@Test
	public void testGetWithAuth() {
		
		/*
		 * 1. using header
		 * 2. method from restAssured api
		 */
		
		given()
		.log()
		.all()
		.header("Authorization", "") //increpted value of username/password
		.when()
		.get("/all")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
	}
	
	
	@Test
	public void testBasicAuth() {
		
//		== two types of basic auth ===
		/*Preemptive basic auth - it will always send the username/password along with request
		 * 
		 * Challenged basic auth - it will send only when the server asks
		 */
		
		given()
		
//		=== auth info inherited from base class ===
//		.auth()
//		.preemptive()
//		.basic("userName", "password")
		
		.log()
		.all()
		.when()
		.get("/all")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
		
	}

}
