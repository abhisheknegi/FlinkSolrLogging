package com.pack.functions;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pack.models.AvailabilityJsonModel;
import com.pack.models.OfferJsonDataModel;
import com.pack.models.PriceJsonDataModel;
import com.pack.models.RankJsonDataModel;
import com.pack.models.SellerJsonDataModel;

public class ObjectToSolrDocMapper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static transient Gson gsonFull;
	
    @SerializedName("id")
    @Expose
    private String id = UUID.randomUUID().toString();
    
    @SerializedName("ingestion_name")
    @Expose
    private String ingestionName;
	
    @SerializedName("ingestion_id")
    @Expose
    private String ingestionId;
    
    @SerializedName("record_id")
    @Expose
    private String recordId;
    
    @SerializedName("ingestion_status")
    @Expose
    private String ingestionStatus;
    
    @SerializedName("ingestion_status_details")
    @Expose
    private String ingestionStatusDetails;
    
    @SerializedName("doc_date")
    @Expose
    private String docDate;
    
    @SerializedName("seller_id")
    @Expose
    private String sellerId;
    
    @SerializedName("sku_id")
    @Expose
    private String skuId;
    
    @SerializedName("offer_id")
    @Expose
    private String offerId;
    
    @SerializedName("store_number")
    @Expose
    private String storeNumber;
    
    @SerializedName("offer_status")
    @Expose
    private String offerStatus;
    
    @SerializedName("availability")
    @Expose
    private String availability;
    
    @SerializedName("data1")
    @Expose
    private String data1;
    
    @SerializedName("data2")
    @Expose
    private String data2;
    
    public void setId(String id) {
    	this.id = id;
    }
    
    public String getId() {
    	return this.id;
    }
    
    public void setIngestionName(String ingestionName) {
    	this.ingestionName = ingestionName;
    }
    
    public String getIngestionName() {
    	return this.ingestionName;
    }
    
    public void setIngestionId(String ingestionId) {
    	this.ingestionId = ingestionId;
    }
    
    public String getIngestionId() {
    	return this.ingestionId;
    }
    
    public void setRecordId(String recordId) {
    	this.recordId = recordId;
    }
    
    public String getRecordId() {
    	return this.recordId;
    }
    
    public void setIngestionStatus(String ingestionStatus) {
    	this.ingestionStatus = ingestionStatus;
    }
    
    public String getIngestionStatus() {
    	return this.ingestionStatus;
    }
    
    public void setIngestionStatusDetails(String ingestionStatusDetails) {
    	this.ingestionStatusDetails = ingestionStatusDetails;
    }
    
    public String getIngestionStatusDetails() {
    	return this.ingestionStatusDetails;
    }
    
    public void setDocDate(String docDate) {
    	this.docDate = docDate;
    }
    
    public String getDocDate() {
    	return this.docDate;
    }
    
    public void setSellerId(String sellerId) {
    	this.sellerId = sellerId;
    }
    
    public String getSellerId() {
    	return this.sellerId;
    }
    
    public void setSkuId(String skuId) {
    	this.skuId = skuId;
    }
    
    public String getSkuId() {
    	return this.skuId;
    }
    
    public void setOfferId(String offerId) {
    	this.offerId = offerId;
    }
    
    public String getOfferId() {
    	return this.offerId;
    }
    
    public void setStoreNumber(String storeNumber) {
    	this.storeNumber = storeNumber;
    }
    
    public String getStoreNumber() {
    	return this.storeNumber;
    }
    
    public void setOfferStatus(String offerStatus) {
    	this.offerStatus = offerStatus;
    }
    
    public String getOfferStatus() {
    	return this.offerStatus;
    }
    
    public void setAvailability(String availability) {
    	this.availability = availability;
    }
    
    public String getAvailability() {
    	return this.availability;
    }
    
    public void setData1(String data1) {
    	this.data1 = data1;
    }
    
    public String getData1() {
    	return this.data1;
    }
    
    public void setData2(String data2) {
    	this.data2 = data2;
    }
    
    public String getData2() {
    	return this.data2;
    }
    
	
	public String getSolrJson(String jobname, String status, String message, String json, OfferJsonDataModel msg) throws ParseException {
		
		this.id = UUID.randomUUID().toString();
		this.ingestionName = jobname;
		this.ingestionId = UUID.randomUUID().toString();
		this.recordId = UUID.randomUUID().toString();
		this.ingestionStatus = status;
		this.ingestionStatusDetails = message;
		
		if(StringUtils.isNotBlank(msg.getCreationTime())) {
			this.docDate = msg.getCreationTime();
		}else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			this.docDate = dateFormat.format(new Date());
		}
		
		this.sellerId = String.valueOf(msg.getSellerId());
		this.skuId = msg.getSkuId();
		this.offerId = msg.getOfferId();
		this.storeNumber = "";
		this.offerStatus = "";
		this.data1 = json;
		this.data2 = "";
		
		System.out.println("id: " + this.id);
		
        if (null == gsonFull) {
            gsonFull = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").create();
        }
        return gsonFull.toJson(this);
	}
	
	public String getSolrJson(String jobname, String status, String message, String json, PriceJsonDataModel msg) {
		
		this.id = UUID.randomUUID().toString();
		this.ingestionName = jobname;
		this.ingestionId = UUID.randomUUID().toString();
		this.recordId = UUID.randomUUID().toString();
		this.ingestionStatus = status;
		this.ingestionStatusDetails = message;
		
		if(StringUtils.isNotBlank(msg.getCreationTime())) {
			this.docDate = msg.getCreationTime();
		}else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			this.docDate = dateFormat.format(new Date());
		}
		
		this.sellerId = "-";
		this.skuId = msg.getSkuId();
		this.offerId = msg.getOfferId();
		this.storeNumber = String.valueOf(Integer.valueOf(msg.getStoreNumber()));
		this.offerStatus = "";
		this.data1 = json;
		this.data2 = "";
		
        if (null == gsonFull) {
            gsonFull = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").create();
        }
        return gsonFull.toJson(this);
	}
	
	public String getSolrJson(String jobname, String status, String message, String json, RankJsonDataModel msg) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String strDate = dateFormat.format(new Date());
		
		this.id = UUID.randomUUID().toString();
		this.ingestionName = jobname;
		this.ingestionId = UUID.randomUUID().toString();
		this.recordId = UUID.randomUUID().toString();
		this.ingestionStatus = status;
		this.ingestionStatusDetails = message;
		this.docDate = strDate;
		this.sellerId = "-";
		this.skuId = msg.getSkuId();
		this.offerId = msg.getOfferId();
		this.storeNumber = "";
		this.offerStatus = "";
		this.data1 = json;
		this.data2 = "";
		
        if (null == gsonFull) {
            gsonFull = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").create();
        }
        return gsonFull.toJson(this);
	}
	
	public String getSolrJson(String jobname, String status, String message, String json, AvailabilityJsonModel msg) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String strDate = dateFormat.format(new Date());
		
		this.id = UUID.randomUUID().toString();
		this.ingestionName = jobname;
		this.ingestionId = UUID.randomUUID().toString();
		this.recordId = UUID.randomUUID().toString();
		this.ingestionStatus = status;
		this.ingestionStatusDetails = message;
		this.docDate = strDate;
		this.sellerId = "-";
		this.skuId = msg.getSkuId();
		this.offerId = msg.getOfferId();
		this.storeNumber = "";
		this.offerStatus = "";
		this.data1 = json;
		this.data2 = "";
		
        if (null == gsonFull) {
            gsonFull = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").create();
        }
        return gsonFull.toJson(this);
	}
	
	public String getSolrJson(String jobname, String status, String message, String json, SellerJsonDataModel msg) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String strDate = dateFormat.format(new Date());
		
		this.id = UUID.randomUUID().toString();
		this.ingestionName = jobname;
		this.ingestionId = UUID.randomUUID().toString();
		this.recordId = UUID.randomUUID().toString();
		this.ingestionStatus = status;
		this.ingestionStatusDetails = message;
		this.docDate = strDate;
		this.sellerId = String.valueOf(msg.getSellerId());
		this.skuId = "";
		this.offerId = "";
		this.storeNumber = "";
		this.offerStatus = "";
		this.data1 = json;
		this.data2 = "";
		
        if (null == gsonFull) {
            gsonFull = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").create();
        }
        return gsonFull.toJson(this);
	}
	

}
