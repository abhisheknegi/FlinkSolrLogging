package com.pack.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferJsonDataModel implements Serializable {

	// have to configure this acc. to current json
	
    private static final long serialVersionUID = 1L;

    /**
     * List of fields to be excluded for the offer details
     */
    public static final List<String> OFFER_DETAILS_FIELDS_EXCLUDE = Arrays
            .asList();
    public static final int MAX_ALLOWED_QTY = 999;  //check its value

    /**
     * One GSON class for an object (will all fields)
     */
    private static transient Gson gsonFull;

    @SerializedName("offerType")
    @Expose
    private String offerType;
    
    @SerializedName("offerId")
    @Expose
    private String offerId;
   
    @SerializedName("UPC")
    @Expose
    private String skuId;
    
    //check default value
    @SerializedName("isActive")
    @Expose
    private boolean isActive = false;
    
    @SerializedName("length")
    @Expose
    protected BigDecimal length = BigDecimal.ZERO;
    
    @SerializedName("width")
    @Expose
    protected BigDecimal width = BigDecimal.ZERO;
    
    @SerializedName("height")
    @Expose
    protected BigDecimal height = BigDecimal.ZERO;
    
    @SerializedName("weight")
    @Expose
    protected BigDecimal weight = BigDecimal.ZERO;
    
    //check its type and default value
    @SerializedName("condition")
    @Expose
    private int condition;
    
    @SerializedName("sellerId")
    @Expose
    private int sellerId;
    
    @SerializedName("sellerName")
    @Expose
    private String sellerName;
    
    //check default value
    @SerializedName("leadTimeToShip")
    @Expose
    private int leadTimeToShip;
    
    @SerializedName("warranty")
    @Expose
    private String warranty;
   
    @SerializedName("warrantyCondition")
    @Expose
    private String warrantyCondition;

    //check default value
    @SerializedName("warrantyInMonths")
    @Expose
    private int warrantyInMonths;
    
    @SerializedName("msi")
    @Expose
    private int msi;
    
    //check default value
    @SerializedName("isFreeShip")
    @Expose
    private boolean isFreeShip = false;
    
    //	Added for new field CreationTime from Sentinel feed
    @SerializedName("creationTime")
    @Expose
    private String creationTime;

    public OfferJsonDataModel() {
    }

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public String getSkuId() {
		return this.skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public int getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public boolean getisActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getLeadTimeToShip() {
		return leadTimeToShip;
	}

	public void setLeadTimeToShip(int leadTimeToShip) {
		this.leadTimeToShip = leadTimeToShip;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public String getWarrantyCondition() {
		return warrantyCondition;
	}

	public void setWarrantyCondition(String warrantyCondition) {
		this.warrantyCondition = warrantyCondition;
	}

	public int getWarrantyInMonths() {
		return warrantyInMonths;
	}

	public void setWarrantyInMonths(int warrantyInMonths) {
		this.warrantyInMonths = warrantyInMonths;
	}

	public int getMsi() {
		return msi;
	}

	public void setMsi(int msi) {
		this.msi = msi;
	}

	public boolean getisFreeShip() {
		return isFreeShip;
	}

	public void setFreeShip(boolean isFreeShip) {
		this.isFreeShip = isFreeShip;
	}


	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}
	
	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.width = weight;
	}

    public String getOfferId() {
        return this.offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }
    
	//	Section added for new field CreationTime from Sentinel feed
	
	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
    public static OfferJsonDataModel fromJson(String json) {
        if (null == gsonFull) {
            gsonFull = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").create();
        }
        return gsonFull.fromJson(json, OfferJsonDataModel.class);
    }

}