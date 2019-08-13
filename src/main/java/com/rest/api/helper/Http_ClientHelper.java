package com.rest.api.helper;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import com.rest.api.util.RestResponse;

public class Http_ClientHelper {
	
	
	public static RestResponse performGetRequest(String url, Map<String, String> headers) {
		try {
			return performGetRequest(new URI(url), headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	
	
	public static RestResponse performGetRequest(URI uri, Map<String, String> headers) {
		HttpGet get = new HttpGet(uri);
		
		if(headers != null) {
			get.setHeaders(getCustomHeaders(headers));
		}
		return performRequest(get);
	}
	
	
	
	public static RestResponse performPostRequest(URI uri, Object content, ContentType type, Map<String, String> headers) {
		
		HttpPost post = new HttpPost(uri);
		
		if(headers != null) {
			/*for (String key : headers.keySet()) {
				post.addHeader(key, headers.get(key));	
			}*/
			post.setHeaders(getCustomHeaders(headers));
		}
		
		post.setEntity(getHttpEntity(content, type));
		
		 CloseableHttpResponse response = null;
		   try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
			response = client.execute(post);
			ResponseHandler<String> body = new BasicResponseHandler();
			return new RestResponse(response.getStatusLine().getStatusCode(), body.handleResponse(response));
			
		} catch (Exception e) {
			//to handle HttpResponseException when the response code is >=300
			if(e instanceof HttpResponseException)
				return new RestResponse(response.getStatusLine().getStatusCode(), e.getMessage());
			throw new RuntimeException(e.getMessage(),e);
		}
		
		//return performRequest(post);
	}
	
	
	
	public static RestResponse performPostRequest(String url, Object content, ContentType type, Map<String, String> headers) {
		
		try {
			return performPostRequest(new URI(url), content, type, headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
	}
	
	
	
	public static RestResponse performDeleteRequest (String url, Map<String, String> headers) {
		
		try {
			return performDeleteRequest(new URI(url), headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	
	
	public static RestResponse performDeleteRequest (URI uri, Map<String, String> headers) {
		
		HttpUriRequest delete = RequestBuilder.delete(uri).build();
		
		if(headers != null) {
			delete.setHeaders(getCustomHeaders(headers));
		}
		return performRequest(delete);
	}
	
	
	
	public static RestResponse performPutRequest(URI uri, Object content, ContentType type, Map<String, String> headers) {
		HttpUriRequest put = RequestBuilder.put(uri).setEntity(getHttpEntity(content, type)).build();
		
		if(headers != null) {
			put.setHeaders(getCustomHeaders(headers));
		}
		
		return performRequest(put);
	}
	
	
	
	public static RestResponse performPutRequest(String url, Object content, ContentType type, Map<String, String> headers) {
		try {
			return performPutRequest(new URI(url), content, type, headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	
	
	private static HttpEntity getHttpEntity(Object content, ContentType type) {
		if(content instanceof String)
			return new StringEntity ((String)content, type);
		else if (content instanceof File)
			return new FileEntity((File)content, type);
		else 
			throw new RuntimeException("Entity Type not found");
	}
	
	
	
	private static Header[] getCustomHeaders(Map<String, String> headers) {
		Header[] customHeaders = new Header[headers.size()];
		int i=0;
		for (String key : headers.keySet()) {
			customHeaders[i++] = new BasicHeader(key, headers.get(key));
		}
		return customHeaders;
	}
	
	
	
	
	private static RestResponse performRequest(HttpUriRequest httpMethod) {
		
		CloseableHttpResponse response = null;
		try (CloseableHttpClient client = HttpClientBuilder.create().build()){
			response = client.execute(httpMethod);
			ResponseHandler<String> body = new BasicResponseHandler();
			return new RestResponse(response.getStatusLine().getStatusCode(), body.handleResponse(response));
		} catch (Exception e) {
			//to handle HttpResponseException when the response code is >=300
			if(e instanceof HttpResponseException)
				return new RestResponse(response.getStatusLine().getStatusCode(), e.getMessage());
			throw new RuntimeException(e.getMessage(),e);
		}
	}

}
