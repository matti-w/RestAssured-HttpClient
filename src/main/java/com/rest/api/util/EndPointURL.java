/**
 * 
 */
package com.rest.api.util;


public enum EndPointURL {

	PING("/ping/"), GET_ALL("/all"), GET_BY_ID("/find/"), POST("/add"), PUT("/update"), DELETE("/delete/");
	
	String resourcePath;
	
	private EndPointURL(String resourcepath) {
		this.resourcePath = resourcepath;
	}
	
	public String getResourcePath() {
		return this.resourcePath;
	}
	
	public String getResourcepath(String id) {
		return this.resourcePath + id;
	}
}
