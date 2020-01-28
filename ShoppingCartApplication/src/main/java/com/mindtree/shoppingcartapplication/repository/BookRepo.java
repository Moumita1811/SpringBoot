package com.mindtree.shoppingcartapplication.repository;

import org.springframework.data.repository.CrudRepository;

import com.mindtree.shoppingcartapplication.entities.Book;

public interface BookRepo extends CrudRepository<Book, Integer> {

}
