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

import com.br.checkout.model.Item;
import com.br.checkout.repository.ItemRepository;

@Service 
public class ItemService {
	
	@Autowired 
	private ItemRepository itemRepository;
	
	public Item insert(Item item) {					
		return this.itemRepository.save(item);
	}
	 
	public Item update(Item item) {
		Item savedItem = itemRepository.findById(item.getId()).orElseThrow(() -> new RuntimeException("User not found"));		
		String[] nullPropertyNames = getNullPropertyNames(item);
		BeanUtils.copyProperties(item, savedItem, nullPropertyNames);
		return itemRepository.save(savedItem);		
		
	}
	
	public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
            .map(FeatureDescriptor::getName)
            .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
            .toArray(String[]::new);
    }

	public void delete(String id) {
		itemRepository.deleteById(id);
	}

	public List<Item> findAll(){
		return (List<Item>) this.itemRepository.findAll();
	}
	
	public Optional<Item> findById(String id){
		return this.itemRepository.findById(id);
	}
	
}
