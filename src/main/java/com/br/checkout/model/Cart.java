package com.br.checkout.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "carts")
@EqualsAndHashCode @AllArgsConstructor
public class Cart {
	
	@Id
	@Getter @Setter private String id;
	@Getter @Setter private String userId;
	@Getter @Setter private List<Item> items = new ArrayList<Item>();
	@Getter @Setter private Date createdAt;
	@Getter @Setter private Double cartTotal;
	@Getter @Setter private Boolean cartClosed;
	
	public static Boolean CART_OPEN = Boolean.TRUE;
	public static Boolean CART_CLOSE = Boolean.FALSE;	
	
	public Cart() {
		this.createdAt = new Date();
		this.cartTotal = 0.0;
		this.cartClosed = CART_OPEN;
	}
	
	public void addItem(Item item) {
		items.add(item);	
		items.sort(Comparator.comparing(Item::getName)); 
		generateTotalCart();
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public void removeItem(String itemId) {
		items.remove(items.indexOf(itemId));
		generateTotalCart();
	}
	
	private void generateTotalCart() {
		cartTotal = 0.0;
		for(Item item: items) {
			cartTotal+=item.getValue();
		}
		
	}
}
