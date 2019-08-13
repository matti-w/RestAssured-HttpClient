package com.restAssured.api.auth;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;
import static io.restassured.RestAssured.*;


import org.junit.BeforeClass;

public class BaseSecureClass {
	
	
	@BeforeClass
	public static void setUp() {
		
		baseURI = "http://localhost";
		port = 8443;
		basePath = "/laptop-bag/webapi/sslres";
		//authentication = preemptive().basic("username", "password");
		authentication = certificate("C:\\Program Files\\Java\\jdk-12.0.2\\bin\\mykey.keystore", "server12");
	}

}
