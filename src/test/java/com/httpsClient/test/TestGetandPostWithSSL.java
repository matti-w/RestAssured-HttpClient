package com.httpsClient.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rest.api.helper.Https_ClientHelper;
import com.rest.api.util.ResponseBody;
import com.rest.api.util.RestResponse;

public class TestGetandPostWithSSL {

	
	@Test
	public void testGetnPost() {
		
		String id = (int)(1000*(Math.random())) + "";
		
		String jsonBody = "{" +
				"\"BrandName\": \"Dell\"," +
				"\"Features\": {" +
					"\"Feature\": [\"8GB RAM\"," +
					"\"1TB Hard Drive\"]"+
				"}," +
				"\"Id\": " + id + "," +
				"\"LaptopName\": \"Latitude\"" +
			"}";
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		RestResponse response = Https_ClientHelper.performPostWithSSL("https://localhost:8443/laptop-bag/webapi/sslres/add", jsonBody, ContentType.APPLICATION_JSON, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		response = Https_ClientHelper.performGetRequestWithSSL("https://localhost:8443/laptop-bag/webapi/sslres/find/" + id, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		ResponseBody body = gson.fromJson(response.getResponseBody(), ResponseBody.class);
//		Assert.assertEquals(id, body.getId());
//		Assert.assertEquals("Latitude", body.getLaptopName());
		
	}
}
