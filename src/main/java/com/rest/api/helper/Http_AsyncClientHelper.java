package com.rest.api.helper;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import com.rest.api.util.RequestStatus;
import com.rest.api.util.RestResponse;

public class Http_AsyncClientHelper {
	
	/*
	 * TrustStrategy - use to by pass ssl certification verification
	 * SSLContextBuilder - gives the object of SSLContext
	 * SSLContext - Instance of this class represents a secure socket protocol implementation which acts as a factory for SSLEngine
	 * SSLEngine - a class that enables secure communication using SSL protocol
	 * 
	 */
	
	
	public static SSLContext getSSLContext() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		TrustStrategy trust = new TrustStrategy() {
			
			@Override
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				// override to true allows to override the certificate verification process
				return true; 
			}
		};
		return SSLContextBuilder.create().loadTrustMaterial(trust).build();
	}

	
	// Assigns SSLContext instance.
		public static CloseableHttpAsyncClient getHttpAsyncClient(SSLContext sslContext) {
			if(sslContext == null)
				return HttpAsyncClientBuilder.create().build();
			return HttpAsyncClientBuilder.create().setSSLContext(sslContext).build();
		}
	
		
		public static RestResponse performRequest(HttpUriRequest method, SSLContext context) throws InterruptedException, ExecutionException {
			Future<HttpResponse> response = null;
			try (CloseableHttpAsyncClient client = getHttpAsyncClient(context)){
			// start the asynchClient before executing the client
				client.start();
			// create the object of RequestStatus class that is implementing FutureCallBack<HttpResponse> interface
				response = client.execute(method, new RequestStatus());
				ResponseHandler<String> handler = new BasicResponseHandler();
				// get the type of response first using the .get() method and then invoke .getStatusLine().getStatusCode()
				return new RestResponse(response.get().getStatusLine().getStatusCode(), handler.handleResponse(response.get()));
						
				
			} catch (Exception e) {
				if (e instanceof HttpResponseException)
					return new RestResponse(response.get().getStatusLine().getStatusCode(), e.getMessage());
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
		
		public static RestResponse performGetRequest_Async(String uri, Map<String, String> headers) {
			try {
				return performGetRequest_Async(new URI(uri), headers);
			} catch (URISyntaxException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
		
		
		public static RestResponse performGetRequest_Async(URI uri, Map<String, String> headers) {
			HttpGet get = new HttpGet(uri);
			if(headers != null)
				get.setHeaders(getCustomHeaders(headers));
			try {
				return performRequest(get, null);
			} catch (InterruptedException |ExecutionException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	
	
		public static RestResponse performPost_Async(String uri, Object content, ContentType type, Map<String, String> headers) {
			try {
				return performPost_Async(new URI(uri), content, type, headers);
			} catch (URISyntaxException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
		
		
		public static RestResponse performPost_Async(URI uri, Object content, ContentType type, Map<String, String> headers) {
			HttpUriRequest post = RequestBuilder.post(uri).setEntity(getHttpEntity(content, type)).build();
			if(headers != null)
				post.setHeaders(getCustomHeaders(headers));
			try {
				return performRequest(post, null);
			} catch (InterruptedException | ExecutionException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
		
		
		
		public static RestResponse performPutRequest_Async(String uri, Object content, ContentType type, Map<String, String> headers) {
			try {
				return performPutRequest_Async(new URI(uri), content, type, headers);
			} catch (URISyntaxException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
		
		public static RestResponse performPutRequest_Async(URI uri, Object content, ContentType type, Map<String, String> headers) {
			HttpUriRequest put = RequestBuilder.put(uri).setEntity(getHttpEntity(content, type)).build();
			if(headers != null)
				put.setHeaders(getCustomHeaders(headers));
			try {
				return performRequest(put, null);
			} catch (InterruptedException | ExecutionException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
		
		
		public static RestResponse performDeleteRequest_Async(URI uri, Map<String, String> headers) {
			HttpUriRequest delete = RequestBuilder.delete(uri).build();
			if( headers != null)
				delete.setHeaders(getCustomHeaders(headers));
			try {
				return performRequest(delete, null);
			} catch (InterruptedException | ExecutionException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
		
		
		public static RestResponse performDeleteRequest_Async(String uri, Map<String, String> headers) {
			try {
				return performDeleteRequest_Async(new URI(uri), headers);
			} catch (URISyntaxException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
		
		
		public static RestResponse performGetSSLRequestAsync(String uri,Map<String, String> headers) {
			try {
				return performGetRequest_Async(new URI(uri),headers);
			} catch (URISyntaxException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
		public static RestResponse performGetSSLRequestAsync(URI uri,Map<String, String> headers) {
			HttpGet get = new HttpGet(uri);
			if(headers != null)
				get.setHeaders(getCustomHeaders(headers));
			try {
				return performRequest(get,getSSLContext());
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
		public static RestResponse performPostSLLRequestAsync(URI uri,Object content,ContentType type,Map<String, String> headers) {
			HttpPost post = new HttpPost(uri);
			if(headers != null)
				post.setHeaders(getCustomHeaders(headers));
			post.setEntity(getHttpEntity(content, type));
			try {
				return performRequest(post,getSSLContext());
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
		public static RestResponse performPostSSLRequestAsync(String uri,Object content,ContentType type,Map<String, String> headers) {
			try {
				return performPost_Async(new URI(uri),content,type,headers);
			} catch (URISyntaxException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
		public static RestResponse performPutSSLRequestAsync(URI uri,Object content,ContentType type,Map<String, String> headers) {
			HttpPut post = new HttpPut(uri);
			if(headers != null)
				post.setHeaders(getCustomHeaders(headers));
			post.setEntity(getHttpEntity(content, type));
			try {
				return performRequest(post,getSSLContext());
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
		public static RestResponse performPutSSLRequestAsync(String uri,Object content,ContentType type,Map<String, String> headers) {
			try {
				return performPost_Async(new URI(uri),content,type,headers);
			} catch (URISyntaxException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
		public static RestResponse performDeleteSSLRequestAsync(URI uri,Map<String, String> headers) {
			HttpDelete post = new HttpDelete(uri);
			if(headers != null)
				post.setHeaders(getCustomHeaders(headers));
			try {
				return performRequest(post,getSSLContext());
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
		public static RestResponse performDeleteSSLRequestAsync(String uri,Map<String, String> headers) {
			try {
				return performDeleteRequest_Async(new URI(uri),headers);
			} catch (URISyntaxException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}

	
	
	
	
	
	
		private static HttpEntity getHttpEntity(Object content, ContentType type) {
			if (content instanceof String)
				return new StringEntity((String) content, type);
			else if (content instanceof File)
				return new FileEntity((File) content, type);
			else
				throw new RuntimeException("Entity type not found ");
	
		}

//	Header Interface represents an HTTP header field
// 	BasicHeader class implements Header Interface
		private static Header[] getCustomHeaders(Map<String, String> headers) {
			Header[] customHeaders = new Header[headers.size()];
			int i = 0;
			for (String key : headers.keySet()) {
				customHeaders[i++] = new BasicHeader(key, headers.get(key));
			}
			return customHeaders;
		}

}
