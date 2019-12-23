package com.br.checkout.service;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.checkout.model.User;
import com.br.checkout.repository.UserRepository;

@Service 
public class UserService {
	
	@Autowired 
	private UserRepository userRepository;
	
	public User insert(User user) {					
		User local = userRepository.findUserByEmail(user.getEmail());
		if(local == null)
			return this.userRepository.save(user);
		else
			throw new RuntimeException("There's already an user with this email!");
	}
	 
	public User update(User user) {
		User savedUser = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found"));		
		String[] nullPropertyNames = getNullPropertyNames(user);
		BeanUtils.copyProperties(user, savedUser, nullPropertyNames);
		return userRepository.save(savedUser);		
		
	}
	
	public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
            .map(FeatureDescriptor::getName)
            .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
            .toArray(String[]::new);
    }

	public void delete(String id) {
		userRepository.deleteById(id);
	}

	public List<User> findAll(){
		return (List<User>) this.userRepository.findAll();
	}
	
	public Optional<User> findById(String id){
		return this.userRepository.findById(id);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findByEmail(String email) {
		return (List<User>) this.userRepository.findUserByEmail(email);

	}
	
}
