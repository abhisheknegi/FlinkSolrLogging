package com.pack.models;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AvailabilityJsonModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static transient Gson gsonFull;
	
	@SerializedName("UPC")
	@Expose
	protected String skuId;

	@SerializedName("offerId")
	@Expose
	protected String offerId;
	
	@SerializedName("isAvailable")
	@Expose
	protected boolean isAvailable;

	@SerializedName("isBundle")
	@Expose
	protected boolean isBundle = false;
	
	public String getSkuId() {
		return skuId;		
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
	
	public boolean getIsAvailable() {
		return isAvailable;		
	}

	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public boolean getIsBundle() {
		return isBundle;
	}

	public void setIsBundle(boolean isBundle) {
		this.isBundle = isBundle;
	}
	
	public static AvailabilityJsonModel fromJson(String json) {
		if (null == gsonFull) {
			gsonFull = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").create();
		}

		return gsonFull.fromJson(json, AvailabilityJsonModel.class);
	}

}
