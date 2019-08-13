package com.httpClient.test;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;

import com.rest.api.helper.Http_ClientHelper;
import com.rest.api.helper.Https_ClientHelper;
import com.rest.api.util.RestResponse;

public class TestQueryParameter {

	
	@Test
	public void testQuery() throws URISyntaxException {
		URI url = new URIBuilder()
		.setScheme("http") // https
		.setHost("localhost:8080/")
		.setPath("laptop-bag/webapi/api/query")
		.setParameter("id", "444")
		.setParameter("laptopName", "Latitude")
		.build();
		
		RestResponse response = Http_ClientHelper.performGetRequest(url, null);
		System.out.println(response.toString());
		
		
	}
}
