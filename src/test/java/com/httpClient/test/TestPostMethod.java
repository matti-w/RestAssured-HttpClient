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



public class TestPostMethod extends Base{
	
	
	@Test
	public void testPost() {
		
		String id = (int)(1000*(Math.random()))+"";
		String url = baseURL + EndPointURL.POST.getResourcePath();
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		
		String jsonBody = "{ \"BrandName\":\"Lenovo\", \"Features\":{ \"Feature\":[ \"8GB RAM\", \"1TB Hard Drive\"] }, \"Id\":"+id+", \"LaptopName\":\"IdeaPad\" }";
		
		RestResponse response = Http_ClientHelper.performPostRequest(url, jsonBody, ContentType.APPLICATION_JSON, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		
		String getUrl = baseURL + EndPointURL.GET_BY_ID.getResourcepath(id);
		response = Http_ClientHelper.performGetRequest(getUrl, headers);
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		ResponseBody responseBody = gson.fromJson(response.getResponseBody(), ResponseBody.class);
		
		Assert.assertEquals(id, responseBody.Id);
		Assert.assertEquals("IdeaPad", responseBody.LaptopName);
	}
	

}
