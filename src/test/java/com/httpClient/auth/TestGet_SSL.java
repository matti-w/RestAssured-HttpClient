package com.httpClient.auth;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.rest.api.helper.Http_ClientHelper;
import com.rest.api.util.RestResponse;

public class TestGet_SSL {

	public static void main(String[] args) {

//		=== to by pass ssl certificate verification process
		
		
		RestResponse response = Http_ClientHelper.performGetRequest("http://localhost:8080/laptop-bag/webapi/sslres/ping", null);
		System.out.println(response.toString());

	}

}
