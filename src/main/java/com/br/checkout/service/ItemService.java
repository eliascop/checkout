package com.br.checkout.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.checkout.model.Item;
import com.br.checkout.repository.ItemRepository;
import com.br.checkout.utils.Utils;

@Service 
public class ItemService {
	
	@Autowired 
	private ItemRepository itemRepository;
	
	public Item insert(Item item) {					
		return this.itemRepository.save(item);
	}
	 
	public Item update(Item item) {
		Item savedItem = itemRepository.findById(item.getId()).orElseThrow(() -> new RuntimeException("Item not found"));		
		String[] nullPropertyNames = Utils.getNullPropertyNames(item);
		BeanUtils.copyProperties(item, savedItem, nullPropertyNames);
		return itemRepository.save(savedItem);		
		
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
