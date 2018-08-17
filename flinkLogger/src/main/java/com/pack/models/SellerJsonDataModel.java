package com.pack.models;

import java.io.Serializable;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class SellerJsonDataModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected int sellerId;
	
	public int getSellerId() {
		return this.sellerId;
	}
	
	public void setSellerId(String json, Configuration conf) {
		DocumentContext doc = JsonPath.using(conf).parse(json);
		this.sellerId = doc.read("$.sellerId", Integer.class);;
	}
	
	

}
