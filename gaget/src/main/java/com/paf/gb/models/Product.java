package com.paf.gb.models;

public class Product {

	private int productId;
	private String productName;
	private String productType;
	private String productPrice;
	private String image;
	private String relatedField;
	private String description;
	private int sellerId;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRelatedField() {
		return relatedField;
	}
	public void setRelatedField(String relatedField) {
		
		this.relatedField = relatedField;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productType=" + productType
				+ ", productPrice=" + productPrice + ", image=" + image + ", relatedField=" + relatedField
				+ ", description=" + description + ", sellerId=" + sellerId + "]";
	}
	
	
}
