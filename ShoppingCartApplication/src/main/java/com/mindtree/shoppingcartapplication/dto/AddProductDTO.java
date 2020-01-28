package com.mindtree.shoppingcartapplication.dto;

public class AddProductDTO {
	
	private Integer userId;
	
	private Integer prodId;

	
	public AddProductDTO(Integer userId, Integer prodId) {
		super();
		this.userId = userId;
		this.prodId = prodId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	
	
	

}
