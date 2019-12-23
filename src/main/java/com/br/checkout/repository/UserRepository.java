package com.br.checkout.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.checkout.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
	
	public User findUserByEmail(@Param("email") String email);
	
}