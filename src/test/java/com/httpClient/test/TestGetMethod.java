package com.httpClient.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.testng.Assert;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rest.api.helper.Http_ClientHelper;
import com.rest.api.util.ResponseBody;
import com.rest.api.util.RestResponse;

public class TestGetMethod {
	
	@Test
	public void testGetPingAlive() {
		String url = "http://localhost:8080/laptop-bag/webapi/api/ping/Hello";
		RestResponse response = Http_ClientHelper.performGetRequest(url, null);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		Assert.assertEquals("Hi! Hello", response.getResponseBody());
		System.out.println(response.getResponseBody());
	}
	
	//Status code1: 200 OK	
	//Status code2: 204 No Content
	@Test
	public void testGetAll() {
		String url = "http://localhost:8080/laptop-bag/webapi/api/all";
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		
		RestResponse response = Http_ClientHelper.performGetRequest(url, headers);
		Assert.assertTrue((HttpStatus.SC_OK == response.getStatusCode() || HttpStatus.SC_NO_CONTENT == response.getStatusCode()), "Expected status code not found.");
		System.out.println(response.getResponseBody());
	}
	
	//Status code1: 200 OK	
	//Status code2: 404 Not found
	@Test
	public void testGetWithId() {
		String url = "http://localhost:8080/laptop-bag/webapi/api/find/844";
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		
		RestResponse response = Http_ClientHelper.performGetRequest(url, headers);
		
		Assert.assertTrue((HttpStatus.SC_OK == response.getStatusCode() || HttpStatus.SC_NOT_FOUND == response.getStatusCode()), "Expected status code not found.");
		System.out.println(response.getResponseBody());
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		ResponseBody body = gson.fromJson(response.getResponseBody(), ResponseBody.class);
		
		Assert.assertEquals("Dell", body.BrandName);
		Assert.assertEquals("126", body.Id);
		Assert.assertEquals("Latitude", body.LaptopName);
	}

}
