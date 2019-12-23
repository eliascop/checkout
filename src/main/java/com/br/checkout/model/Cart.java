package com.br.checkout.model;

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
	private Integer id;
	@Getter @Setter private User user;
	@Getter @Setter private List<Item> items;
	@Getter @Setter private Date createdAt;
	@Getter @Setter private Double cartTotal;
	@Getter @Setter private Boolean cartClosed;
	
	public static Boolean CART_OPEN = Boolean.TRUE;
	public static Boolean CART_CLOSE = Boolean.FALSE;	
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}
	
}
