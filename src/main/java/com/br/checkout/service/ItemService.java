package com.br.checkout.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.checkout.model.Item;
import com.br.checkout.model.Product;
import com.br.checkout.repository.ItemRepository;
import com.br.checkout.repository.ProductRepository;
import com.br.checkout.utils.Utils;

@Service 
public class ItemService {
	
	@Autowired 
	private ItemRepository itemRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public Item insert(Item item) {					
		Optional<Item> storedItem = this.itemRepository.findItemByProductId(item.getProductId());
		Optional<Product> storedProduct = this.productRepository.findById(item.getProductId());
		int itemQuantity = item.getQuantity();
		Double itemTotal = storedProduct.get().getPrice()*itemQuantity;
		
		if(!storedItem.isPresent()) {			
			item.setItemTotal(itemTotal);
			return this.itemRepository.save(item);
		}else {
			itemQuantity += storedItem.get().getQuantity();
			itemTotal = storedProduct.get().getPrice()*itemQuantity;
			
			item.setId(storedItem.get().getId());
			item.setQuantity(itemQuantity);
			item.setItemTotal(itemTotal);
			return this.update(item);
		}
	}
	
	public void remove(String productId) {					
		Optional<Item> local = this.itemRepository.findItemByProductId(productId);
		if(local.isPresent()){
			Product storedProduct = this.productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Problem to remove item: Product not found"));;
			Item item = local.get();
			int itemQuantity = item.getQuantity()-1;
			Double itemTotal = item.getItemTotal()-storedProduct.getPrice();
			if(itemQuantity == 0)
				this.delete(item.getId());
			else {
				item.setQuantity(itemQuantity);
				item.setItemTotal(itemTotal);
				this.update(item);
			}
		}
			
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
		List<Item> items = this.itemRepository.findAll();
		Collections.sort(items, Item.COMPARE_BY_PRODUCT);
		return items;
	}
	
	public List<Item> findItemsByUserId(String userId){
		List<Item> items = this.itemRepository.findItemsByUserId(userId);
		return items;
	}
	
	public Optional<Item> findById(String id){
		return this.itemRepository.findById(id);
	}

	public Optional<Item> findByProductId(String productId) {
		return this.itemRepository.findItemByProductId(productId);
	}
	
}
