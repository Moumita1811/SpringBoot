package com.mindtree.shoppingcartapplication.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CART_ITEMS")
@NamedQuery(name="CartItems.fetchbyProdId",
query="Select ci from CartItems ci where ci.product = :prodId")
@NamedQuery(name="CartItems.fetchbyCartId",
query="Select ci from CartItems ci where ci.cart = :cart")
public class CartItems {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartItemId;
	
	@Column
	private Integer productQty;
	
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "PROD_ID")
	private Product product;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CART_ID")
	private Cart cart;
	
	public CartItems() {
		super();
	}
	
	public Integer getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Integer cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public void setProductQty(Integer productQty) {
		this.productQty = productQty;
	}

	public Integer getProductQty() {
		return productQty;
	}

}
