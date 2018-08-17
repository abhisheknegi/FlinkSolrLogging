package com.pack.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PriceJsonDataModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static transient Gson gsonFull;
	
	@SerializedName("UPC")
	@Expose
	protected String skuId;

	@SerializedName("offerId")
	@Expose
	protected String offerId;

	@SerializedName("storeNumber")
	@Expose
	protected String storeNumber = "5468";

	@SerializedName("basePrice")
	@Expose
	protected BigDecimal basePrice = BigDecimal.ZERO;

	@SerializedName("specialPrice")
	@Expose
	protected BigDecimal specialPrice = BigDecimal.ZERO;

	@SerializedName("originalPrice")
	@Expose
	protected BigDecimal originalPrice = BigDecimal.ZERO;

	@SerializedName("productUnitaryCost")
	@Expose
	protected BigDecimal productUnitaryCost = BigDecimal.ZERO;

	@SerializedName("promotionEvent")
	@Expose
	protected BigDecimal promotionEvent = BigDecimal.ZERO;

	@SerializedName("eventCode")
	@Expose
	protected String eventCode;

	@SerializedName("ivaAmount")
	@Expose
	protected BigDecimal ivaAmount = BigDecimal.ZERO;

	@SerializedName("ivaTaxPct")
	@Expose
	protected BigDecimal ivaTaxPct = BigDecimal.ZERO;

	@SerializedName("iepsTaxPct")
	@Expose
	protected BigDecimal iepsTaxPct = BigDecimal.ZERO;

	@SerializedName("estatTaxPct")
	@Expose
	protected BigDecimal estatTaxPct = BigDecimal.ZERO;

	@SerializedName("last_updated_date")
	@Expose
	private Date lastUpdatedDate;

	@SerializedName("last_updated_by")
	@Expose
	private String lastUpdatedBy;

	private String storeType = "P";
	
    //	Added for new field CreationTime from Sentinel feed
    @SerializedName("creationTime")
    @Expose
    private String creationTime;

	public PriceJsonDataModel() {
		// Empty constructor to instantiate the object directly
	}
	
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

	public String getStoreType() {
		return storeType;
	}

	public String getStoreNumber() {
		return storeNumber;
	}

	public void setStoreNumber(String storeNumber) {
		this.storeNumber = storeNumber;
	}

	public void setbasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public BigDecimal getbasePrice() {
		return basePrice;
	}

	public void setspecialPrice(BigDecimal specialPrice) {
		this.specialPrice = specialPrice;
	}

	public BigDecimal getspecialPrice() {
		return specialPrice;
	}

	public void setoriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public BigDecimal getoriginalPrice() {
		return originalPrice;
	}

	public void setproductUnitaryCost(BigDecimal productUnitaryCost) {
		this.specialPrice = productUnitaryCost;
	}

	public BigDecimal getproductUnitaryCost() {
		return productUnitaryCost;
	}

	public void setpromotionEvent(BigDecimal promotionEvent) {
		this.promotionEvent = promotionEvent;
	}

	public BigDecimal getpromotionEvent() {
		return promotionEvent;
	}

	public void seteventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public String geteventCode() {
		return eventCode;
	}

	public void setivaAmount(BigDecimal ivaAmount) {
		this.ivaAmount = ivaAmount;
	}

	public BigDecimal getivaAmount() {
		return ivaAmount;
	}

	public void setivaTaxPct(BigDecimal ivaTaxPct) {
		this.ivaTaxPct = ivaTaxPct;
	}

	public BigDecimal getivaTaxPct() {
		return ivaTaxPct;
	}

	public void setiepsTaxPct(BigDecimal iepsTaxPct) {
		this.iepsTaxPct = iepsTaxPct;
	}

	public BigDecimal getiepsTaxPct() {
		return iepsTaxPct;
	}

	public void setestatTaxPct(BigDecimal estatTaxPct) {
		this.estatTaxPct = estatTaxPct;
	}

	public BigDecimal getestatTaxPct() {
		return estatTaxPct;
	}
	
	//	Section added for new field CreationTime from Sentinel feed
	
	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	
	public static PriceJsonDataModel fromJson(String json) {
		if (null == gsonFull) {
			gsonFull = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").create();
		}

		return gsonFull.fromJson(json, PriceJsonDataModel.class);
	}

}
