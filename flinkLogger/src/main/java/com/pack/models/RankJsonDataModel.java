package com.pack.models;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RankJsonDataModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@SerializedName("skuId")
	@Expose
	protected String skuId;

	@SerializedName("offerId")
	@Expose
	protected String offerId;
	
	private static transient Gson gsonFull;
	
	public RankJsonDataModel() {
	}
	
	public String getSkuId() {
		return this.skuId;
	}
	
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	
	public String getOfferId() {
		return this.offerId;
	}
	
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
	
	public static RankJsonDataModel fromJson(String json) {
		if (null == gsonFull) {
			gsonFull = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").create();
		}

		return gsonFull.fromJson(json, RankJsonDataModel.class);
	}
	
	
}
