package com.mindtree.shoppingcartapplication.dto;

import java.util.List;

import com.mindtree.shoppingcartapplication.entities.Product;

public class CartDisplayDTO {
	
	private List<Product> productList;
	
	private double cartTotal;

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public double getCartTotal() {
		return cartTotal;
	}

	public void setCartTotal(double cartTotal) {
		this.cartTotal = cartTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(cartTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((productList == null) ? 0 : productList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartDisplayDTO other = (CartDisplayDTO) obj;
		if (Double.doubleToLongBits(cartTotal) != Double.doubleToLongBits(other.cartTotal))
			return false;
		if (productList == null) {
			if (other.productList != null)
				return false;
		} else if (!productList.equals(other.productList))
			return false;
		return true;
	}
	
}
