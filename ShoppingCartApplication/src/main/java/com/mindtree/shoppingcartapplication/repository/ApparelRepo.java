package com.mindtree.shoppingcartapplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.shoppingcartapplication.entities.Apparel;

@Repository
public interface ApparelRepo extends CrudRepository<Apparel, Integer> {

}
