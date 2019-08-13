package com.httpClient.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rest.api.helper.Http_ClientHelper;
import com.rest.api.util.Base;
import com.rest.api.util.EndPointURL;
import com.rest.api.util.ResponseBody;
import com.rest.api.util.RestResponse;

public class TestPutMethod extends Base{
	
	/*
	 * 1. call post method to post data, 200ok validation
	 * 2. call put method to update data 200ok validation
	 * 3. call get method to validate data, content validation
	 */
	
	@Test
	public void testPut() {
		
		String id = (int)(1000*(Math.random()))+"";
		String url = baseURL + EndPointURL.POST.getResourcePath();
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		
		String jsonBody = "{ \"BrandName\":\"Lenovo\", \"Features\":{ \"Feature\":[ \"8GB RAM\", \"1TB Hard Drive\"] }, \"Id\":"+id+", \"LaptopName\":\"IdeaPad\" }";
		
		RestResponse response = Http_ClientHelper.performPostRequest(url, jsonBody, ContentType.APPLICATION_JSON, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		
		headers.clear();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		
		String jsonUpdate = "{ \"BrandName\":\"Lenovo\", \"Features\":{ \"Feature\":[ \"8GB RAM\", \"15.5 inc LCD\", \"256 GB SSD\", \"1TB Hard Drive\"] }, \"Id\":"+id+", \"LaptopName\":\"ThinkPad\" }";
		response = Http_ClientHelper.performPutRequest(baseURL + EndPointURL.PUT.getResourcePath(), jsonUpdate, ContentType.APPLICATION_JSON, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		
		headers.clear();
		headers.put("Accept", "application/json");
		
		response = Http_ClientHelper.performGetRequest(baseURL+EndPointURL.GET_BY_ID.getResourcepath(id), headers);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		ResponseBody body = gson.fromJson(response.getResponseBody(), ResponseBody.class);
		
		Assert.assertEquals("ThinkPad", body.LaptopName);
		
	}

}
