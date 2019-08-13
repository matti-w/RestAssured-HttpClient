package com.restAssured.api.testPost;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Features {
	
	private List<String> Feature;
	
	
	public Features() {
		Feature = new ArrayList<String>();
	}

	@XmlElement(name = "Feature")
	public List<String> getFeature() {
		return Feature;
	}


	public void setFeature(List<String> feature) {
		Feature = feature;
	}
	
	

}
