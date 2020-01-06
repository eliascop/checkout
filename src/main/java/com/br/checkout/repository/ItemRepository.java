package com.br.checkout.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.checkout.model.Item;

@Repository
public interface ItemRepository extends MongoRepository<Item, String>{
		
	@Query("{'productId' : ?0 }")
	public Optional<Item> findItemByProductId(String productId);

	@Query("{'userId' : ?0 }")
	public List<Item> findItemsByUserId(String userId);
	
}
