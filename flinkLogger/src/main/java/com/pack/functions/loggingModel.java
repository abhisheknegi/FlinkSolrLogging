package com.pack.functions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

public class loggingModel {

	private final String jobname = "$.jobname";
	private final String OFFER = "$.offerId";
	private final String SKU = "$.UPC";
	private final String SELLER = "$.sellerId";
	private final String JSON = "$.json";
	private DocumentContext document;

	protected loggingModel(String input) {

		Configuration conf = Configuration.builder().options(Option.DEFAULT_PATH_LEAF_TO_NULL).build();
		this.document = JsonPath.using(conf).parse(input);

	}
	
	public String getJson() {
		
		String job = this.document.read(jobname, String.class);
		if (!("SELLER".equals(job))) {
			System.out.println("--1 " + job);
			String sku = document.read(SKU, String.class);System.out.println("sku: "+sku);
			String offer = document.read(OFFER, String.class);System.out.println("offer: "+offer);
			String seller = "-";
			String json = document.read(JSON, String.class);
			logJson log = new logJson(sku, offer, seller, json);
			return log.getLogJson();
		} else {
			System.out.println("--2 " + job);
			String sku = "-";
			String offer = "-";
			String seller = document.read(SELLER, String.class);
			String json = document.read(JSON, String.class);
			logJson log = new logJson(sku, offer, seller, json);
			return log.getLogJson();
		}
	}

	public static class logJson {

		private String skuId;
		private String offerId;
		private String sellerId;
		private String json;

		private static transient Gson gsonFull;
		
		protected logJson(String skuId, String Offer, String Seller, String json) {
			System.out.println("--1A");
			this.skuId = skuId;
			this.offerId = Offer;
			this.sellerId = Seller;
			this.json = json;
		}

		public String getSku() {
			return this.skuId;
		}

		public void setSku(String skuId) {
			this.skuId = skuId;
		}

		public String getOffer() {
			return this.offerId;
		}

		public void setOffer(String Offer) {
			this.offerId = Offer;
		}

		public String getSeller() {
			return this.sellerId;
		}

		public void setSeller(String Seller) {
			this.sellerId = Seller;
		}

		public String getJson() {
			return this.json;
		}

		public void setJson(String json) {
			this.json = json;
		}

		public String getLogJson() {
			if (null == gsonFull) {
				gsonFull = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").create();
			}
			return gsonFull.toJson(this);
		}

	}

}
