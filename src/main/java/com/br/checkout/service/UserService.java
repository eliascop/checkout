package com.br.checkout.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.checkout.model.User;
import com.br.checkout.repository.UserRepository;
import com.br.checkout.utils.Utils;

@Service 
public class UserService {
	
	@Autowired 
	private UserRepository userRepository;
	
	public User insert(User user) {					
		User local = userRepository.findUserByEmail(user.getEmail()).get();
		if(local == null)
			return this.userRepository.save(user);
		else
			throw new RuntimeException("There's already an user with this email!");
	}
	 
	public User update(User user) {
		User savedUser = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found"));		
		String[] nullPropertyNames = Utils.getNullPropertyNames(user);
		BeanUtils.copyProperties(user, savedUser, nullPropertyNames);
		return userRepository.save(savedUser);		
		
	}
	
	public void delete(String id) {
		userRepository.deleteById(id);
	}

	public List<User> findAll(){
		return this.userRepository.findAll();
	}
	
	public Optional<User> findById(String id){
		return this.userRepository.findById(id);
	}
	
	public Optional<User> findByEmail(String email) {
		return this.userRepository.findUserByEmail(email);
	}
	
}
