package com.mindtree.shoppingcartapplication.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.shoppingcartapplication.entities.Product;

@Repository
public interface ProductRepo extends CrudRepository<Product, Integer> {
	
	List<Product> fetchByName(@Param(value = "name") String name); 

}
