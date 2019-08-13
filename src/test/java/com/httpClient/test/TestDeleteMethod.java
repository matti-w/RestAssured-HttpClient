package com.httpClient.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;

import com.rest.api.helper.Http_ClientHelper;
import com.rest.api.util.Base;
import com.rest.api.util.EndPointURL;
import com.rest.api.util.RestResponse;

public class TestDeleteMethod extends Base{
	
	/*
	 * 1. post data and validate status as 200ok
	 * 2. delete data and validate status as 200 ok
	 * 3. call get end-point and validate status as 404 not found
	 */
	
	RestResponse response;
	
	@Test
	public void testDelete() {
		
		String id = (int)(1000*(Math.random()))+"";
		String url = baseURL + EndPointURL.POST.getResourcePath();
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		
		String jsonBody = "{ \"BrandName\":\"Lenovo\", \"Features\":{ \"Feature\":[ \"8GB RAM\", \"1TB Hard Drive\"] }, \"Id\":"+id+", \"LaptopName\":\"IdeaPad\" }";
		
		RestResponse response = Http_ClientHelper.performPostRequest(url, jsonBody, ContentType.APPLICATION_JSON, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		
		response = Http_ClientHelper.performDeleteRequest(baseURL + EndPointURL.DELETE.getResourcepath(id), null);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		
		response = Http_ClientHelper.performGetRequest(baseURL+EndPointURL.GET_BY_ID.getResourcepath(id), null);
		Assert.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
	}

}
