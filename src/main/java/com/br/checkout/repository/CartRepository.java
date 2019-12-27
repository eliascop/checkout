package com.br.checkout.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.checkout.model.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, String>{
	
	public Optional<Cart> findCartByUser(@Param("id") String id);

}