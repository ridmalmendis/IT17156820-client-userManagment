package com.paf.gb.models;

public class Request {
	
	private int fundraiserId;
	private int productId;
	private String requestDescription;
	
	private String productName;
	private String productPrice;
	private String description;
	
	
	
	public Request() {
		super();
	}
	
	
	
	public Request(int fundraiserId, int productId, String requestDescription) {
		super();
		this.fundraiserId = fundraiserId;
		this.productId = productId;
		this.requestDescription = requestDescription;
	}



	public Request(int fundraiserId, int productId, String productName, String productPrice, String description, String requestDescription) {
		super();
		this.fundraiserId = fundraiserId;
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.description = description;
		this.requestDescription = requestDescription;
				
	}
	public int getFundraiserId() {
		return fundraiserId;
	}
	public void setFundraiserId(int fundraiserId) {
		this.fundraiserId = fundraiserId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getRequestDescription() {
		return requestDescription;
	}
	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Request [fundraiserId=" + fundraiserId + ", productId=" + productId + ", requestDescription="
				+ requestDescription + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", description=" + description + "]";
	}
	
	
	
}
