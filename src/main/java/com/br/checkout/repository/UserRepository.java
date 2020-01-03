package com.br.checkout.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.checkout.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
	
	@Query("{'email' : ?0 }")
	public Optional<User> findUserByEmail(String email);
	
}