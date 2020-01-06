package com.br.checkout.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.checkout.model.Cart;
import com.br.checkout.model.Item;
import com.br.checkout.service.CartService;

@RestController
@RequestMapping("cart")
public class CartResource{
	
	@Autowired
	private CartService cartService;
	
	@PostMapping(value="/item/add/{userId}",consumes = "application/json")
	public ResponseEntity<?> insertItem(@PathVariable String userId, @Valid @RequestBody Item item) {
		try {
			cartService.addItem(userId,item); 
			return ResponseEntity.status(HttpStatus.CREATED).build();			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();			
		}
	}
	
	@PostMapping(value="/item/remove/{userId}",consumes = "application/json")
	public ResponseEntity<?> removeItem(@PathVariable String userId, @Valid @RequestBody Item item) {
		try { 
			cartService.removeItem(userId,item.getProductId());
			return ResponseEntity.status(HttpStatus.CREATED).build();			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();			
		}
	}
	
	@GetMapping("/close/{id}")
	public ResponseEntity<?> closeCart(@PathVariable String userId) {
		try {
			cartService.closeCart(userId);
			return ResponseEntity.status(HttpStatus.CREATED).build();			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();			
		}
	}

	@GetMapping("/item/{userId}")
	public ResponseEntity<?> find(@PathVariable String userId) {
		try {
			Cart cart = cartService.findByUser(userId);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(cart);
		}catch(Exception e) {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/item")
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(this.cartService.findAll());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		cartService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
