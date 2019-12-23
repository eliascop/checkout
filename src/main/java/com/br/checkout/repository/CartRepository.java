package com.br.checkout.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.br.checkout.model.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, Integer>{
	
	
}