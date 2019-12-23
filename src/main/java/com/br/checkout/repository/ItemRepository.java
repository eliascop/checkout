package com.br.checkout.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.checkout.model.Item;

@Repository
public interface ItemRepository extends MongoRepository<Item, String>{
		
    Item findItemByName(@Param("name") String name);
	
}
