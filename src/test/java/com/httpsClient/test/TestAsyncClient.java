package com.httpsClient.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rest.api.helper.Http_AsyncClientHelper;
import com.rest.api.util.ResponseBody;
import com.rest.api.util.RestResponse;



public class TestAsyncClient {

	/**
	 * Step 1 : Post the data , verify the 200 OK
	 * Step 2 : Get the data using GET end point , verify the id also verify the status code
	 * Step 3 : Update the data in the container using PUT end point, verify the status code
	 * Step 4 : Get the data using GET end point , verify the id also verify the status code
	 * Step 5 : Delete the data using DELETE end point, verify the status code
	 * Step 6 : Get the data using GET end point , verify the id also verify the status code
	 * **/
	
	@Test
	public void testClient() {
		
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
		
		String jsonPutBody = "{" +
				"\"BrandName\": \"Dell\"," +
				"\"Features\": {" +
					"\"Feature\": [\"8GB RAM\"," +
					"\"1TB Hard Drive\","+
					"\"16 GB of SSD\"," +
					"\"15.5 inch of display\"]"+
				"}," +
				"\"Id\": " + id + "," +
				"\"LaptopName\": \"Latitude Z Series\"" +
			"}";
		
		
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		RestResponse response = Http_AsyncClientHelper.performPost_Async("http://localhost:8080/laptop-bag/webapi/api/add", jsonBody, ContentType.APPLICATION_JSON, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		
		response = Http_AsyncClientHelper.performGetRequest_Async("http://localhost:8080/laptop-bag/webapi/api/find/" + id, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		
		response = Http_AsyncClientHelper.performPutRequest_Async("http://localhost:8080/laptop-bag/webapi/api/update", jsonPutBody, ContentType.APPLICATION_JSON, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		
		response = Http_AsyncClientHelper.performGetRequest_Async("http://localhost:8080/laptop-bag/webapi/api/find/" + id, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		ResponseBody body = gson.fromJson(response.getResponseBody(), ResponseBody.class);
		//Assert.assertEquals("Latitude Z Series", body.getLaptopName());
		
		response = Http_AsyncClientHelper.performDeleteRequest_Async("http://localhost:8080/laptop-bag/webapi/api/delete/" + id, null);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		
		response = Http_AsyncClientHelper.performGetRequest_Async("http://localhost:8080/laptop-bag/webapi/api/find/" + id, headers);
		Assert.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
		
		
	}
}
