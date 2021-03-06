package com.br.checkout.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.checkout.model.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, String>{
	
	@Query("{'userId' : ?0 }")
	public Optional<Cart> findCartByUser(String userId);

}