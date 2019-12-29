package com.br.checkout.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.checkout.model.Cart;
import com.br.checkout.model.Item;
import com.br.checkout.model.User;
import com.br.checkout.repository.CartRepository;
import com.br.checkout.repository.ItemRepository;
import com.br.checkout.repository.UserRepository;

@Service 
public class CartService {
	
	@Autowired 
	private CartRepository cartRepository;
	
	@Autowired 
	private ItemRepository itemRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	private final static Logger log = Logger.getLogger(CartService.class.getName());
	
	public void addItem(String userId, String itemId) {
		Item storedItem = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
		User storedUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		Cart storedCart = cartRepository.findCartByUser(userId).get();		
		if(storedCart == null) {
			storedCart = new Cart();
			storedCart.setUser(storedUser); 
		}
		storedCart.addItem(storedItem);
		log.info("Total cart of user "+storedUser.getName()+" is $ "+storedCart.getCartTotal());
		cartRepository.save(storedCart);		
	}
	
	public void removeItem(String userId, String itemId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		Cart storedCart = cartRepository.findCartByUser(userId).get();		
		if(storedCart != null) {
			storedCart.removeItem(itemId);
			if(storedCart.getItems().size() == 0) 
				delete(storedCart.getId());	
			else
				cartRepository.save(storedCart);		
				
			log.info("Total cart of user "+user.getName()+" is $ "+storedCart.getCartTotal());
		}
	}
	
	public List<Item> closeCart(String userId){
		Cart storedCart = cartRepository.findCartByUser(userId).orElseThrow(() -> new RuntimeException("Problem to close cart: User not found"));
		storedCart.setCartClosed(Cart.CART_CLOSE);
		return storedCart.getItems();
	}
	
	public void delete(String userId) {
		cartRepository.deleteById(userId);
	}
	
	public Cart findByUser(String userId){
		return this.cartRepository.findCartByUser(userId).orElseThrow(() -> new RuntimeException("User not found"));
	} 
}
