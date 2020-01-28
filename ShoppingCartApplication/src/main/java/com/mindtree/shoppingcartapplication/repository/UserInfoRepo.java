package com.mindtree.shoppingcartapplication.repository;

import org.springframework.data.repository.CrudRepository;

import com.mindtree.shoppingcartapplication.entities.UserInfo;

public interface UserInfoRepo extends CrudRepository<UserInfo, Integer> {

}
