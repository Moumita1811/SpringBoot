package com.mindtree.shoppingcartapplication.dto;

import java.util.List;

import com.mindtree.shoppingcartapplication.entities.Product;

public class ResponseDTO {
	
	private List<Product> productList;
	
	private CartDisplayDTO cartDisplayDTO;
	
	private String messageBody;

	public ResponseDTO(List<Product> productList, CartDisplayDTO cartDisplayDTO, String messageBody) {
		super();
		this.productList = productList;
		this.cartDisplayDTO = cartDisplayDTO;
		this.messageBody = messageBody;
	}

	public ResponseDTO(List<Product> allProducts) {
		this.productList = allProducts;
	}

	public ResponseDTO(String message) {
		this.messageBody = message;
	}

	public ResponseDTO() {
		super();
	}

	public ResponseDTO(CartDisplayDTO displayCart) {
		this.cartDisplayDTO = displayCart;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public CartDisplayDTO getCartDisplayDTO() {
		return cartDisplayDTO;
	}

	public void setCartDisplayDTO(CartDisplayDTO cartDisplayDTO) {
		this.cartDisplayDTO = cartDisplayDTO;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	
}
