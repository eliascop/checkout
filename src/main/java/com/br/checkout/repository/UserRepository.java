package com.br.checkout.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.checkout.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
	
	public Optional<User> findUserByEmail(@Param("email") String email);
	
}