package com.br.checkout.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.checkout.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>{
	
	@Query("{'name' : ?0 }")
	public Optional<Product> findProductByName(String name);

}