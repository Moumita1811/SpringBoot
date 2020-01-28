package com.mindtree.shoppingcartapplication.repository;

import org.springframework.data.repository.CrudRepository;

import com.mindtree.shoppingcartapplication.entities.Cart;

public interface CartRepo extends CrudRepository<Cart, Integer> {

}
