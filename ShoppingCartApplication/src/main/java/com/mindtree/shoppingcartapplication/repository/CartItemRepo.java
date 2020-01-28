package com.mindtree.shoppingcartapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.shoppingcartapplication.entities.Cart;
import com.mindtree.shoppingcartapplication.entities.CartItems;
import com.mindtree.shoppingcartapplication.entities.Product;

@Repository
public interface CartItemRepo extends CrudRepository<CartItems, Integer>{

	List<CartItems> fetchbyProdId(@Param(value = "prodId") Product prodId);
	List<CartItems> fetchbyCartId(@Param(value = "cart") Cart cart);
	
}