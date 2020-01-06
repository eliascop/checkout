package com.br.checkout.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.checkout.model.Cart;
import com.br.checkout.model.Item;
import com.br.checkout.repository.CartRepository;

@Service 
public class CartService {
	
	@Autowired 
	private CartRepository cartRepository;

	@Autowired
	private ItemService itemService;
	
	private final static Logger log = Logger.getLogger(CartService.class.getName());
	
	public void addItem(String userId, Item item) {
		Optional<Cart> storedCart = cartRepository.findCartByUser(userId);		
		Cart cart = new Cart();
		if(!storedCart.isPresent()) {
			cart.setUserId(userId);
			cart = cartRepository.save(cart);
		}else 
			cart = storedCart.get();
		

		item.setCartId(cart.getId());
		item = this.itemService.insert(item);
	
		cart.addItem(item);			
		
		this.cartRepository.save(cart);
		
		log.info("Total cart of user "+userId+" is $ "+cart.getCartTotal()+","+cart.getItems().size()+" items");
	}
		
	public void removeItem(String userId, String productId) {
		Optional<Cart> storedCart = cartRepository.findCartByUser(userId);
		if(storedCart.isPresent()) {

			Item item = itemService.findByProductId(productId).get();			
			itemService.remove(productId);		
			Cart cart = storedCart.get();						
			cart.removeItem(item); 
				
			if(cart.getItems().size() == 0) 
				delete(cart.getId());	
			else 
				cartRepository.save(cart);
			
			
			log.info("Total cart of userX "+userId+" is $ "+cart.getCartTotal());
		}
	}
	
	public List<Item> closeCart(String userId){
		Cart storedCart = cartRepository.findCartByUser(userId).orElseThrow(() -> new RuntimeException("Problem to close cart: User not found"));
		storedCart.setCartClosed(Cart.ST_CART_CLOSE);
		return storedCart.getItems();
	}
	
	public void delete(String cartId) {
		cartRepository.deleteById(cartId);
	}
	
	public List<Cart> findAll(){
		List<Cart> carts = this.cartRepository.findAll();
		return carts;
	}
	
	public Cart findByUser(String userId){
		return this.cartRepository.findCartByUser(userId).orElseThrow(() -> new RuntimeException("User not found"));
	} 
}
