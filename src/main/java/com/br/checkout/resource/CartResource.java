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

import com.br.checkout.model.Item;
import com.br.checkout.service.CartService;

@RestController
@RequestMapping("cart")
public class CartResource{
	
	@Autowired
	private CartService cartService;
		
	@PostMapping
	public ResponseEntity<?> insertItem(@PathVariable String userId, @Valid @RequestBody Item item) {
		try {
			cartService.addItem(userId, item);
			return ResponseEntity.status(HttpStatus.CREATED).build();			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();			
		}
	}
	
	@PostMapping
	public ResponseEntity<?> removeItem(@PathVariable String userId, @Valid @RequestBody String itemId) {
		try {
			cartService.removeItem(userId, itemId);
			return ResponseEntity.status(HttpStatus.CREATED).build();			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();			
		}
	}
	
	@PostMapping
	public ResponseEntity<?> closeCart(@PathVariable String userId) {
		try {
			cartService.closeCart(userId);
			return ResponseEntity.status(HttpStatus.CREATED).build();			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();			
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable String userId) {
		return ResponseEntity.ok(cartService.findByUser(userId));
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<?> delete(@PathVariable String userId) {
		cartService.delete(userId);
		return ResponseEntity.noContent().build();
	}

}
