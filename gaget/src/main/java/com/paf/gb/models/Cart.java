package com.paf.gb.models;

public class Cart {

	private int cartId;
	private int numOfItems;
	private String totalPrice;
	private int customerId;
	private int productId;
	
	private String productName;
	private String productPrice;
	private String productImage;
	private boolean increaseNumOfItems; //if increase value=1
	
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	
	public boolean isIncreaseNumOfItems() {
		return increaseNumOfItems;
	}
	public void setIncreaseNumOfItems(boolean increaseNumOfItems) {
		this.increaseNumOfItems = increaseNumOfItems;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getNumOfItems() {
		return numOfItems;
	}
	public void setNumOfItems(int numOfItems) {
		this.numOfItems = numOfItems;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", numOfItems=" + numOfItems + ", totalPrice=" + totalPrice + ", customerId="
				+ customerId + ", productId=" + productId + ", productName=" + productName + ", productPrice="
				+ productPrice + ", productImage=" + productImage + ", increaseNumOfItems=" + increaseNumOfItems + "]";
	}
	
}
