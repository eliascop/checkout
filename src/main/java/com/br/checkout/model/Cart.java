package com.br.checkout.model;

import java.util.ArrayList;
import java.util.Collections;
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
	@Getter @Setter private List<Item> items;
	@Getter @Setter private Date createdAt;
	@Getter @Setter private Double cartTotal;
	@Getter @Setter private Boolean cartClosed;
	
	public static Boolean ST_CART_OPEN = Boolean.FALSE;
	public static Boolean ST_CART_CLOSE = Boolean.TRUE;	
	
	public Cart() { 
		this.createdAt = new Date();
		this.cartTotal = 0.0;
		this.cartClosed = ST_CART_OPEN;
		this.items = new ArrayList<Item>();
	} 
	
	public void addItem(Item item) {	
		boolean flag = false;
		for(Item i : items) {
			if(i.getProductId().equals(item.getProductId())) {
				flag=true;
				i.setQuantity(i.getQuantity()+item.getQuantity());
				i.setItemTotal(i.getItemTotal()+item.getItemTotal());
			}
		}
		
		if(flag==false)
			items.add(item);

		Collections.sort(items,Item.COMPARE_BY_PRODUCT);			
		generateTotalCart();
	}
	
	public void removeItem(Item item) {
		boolean flag = false;
		for(Item i : items) {
			if(i.getProductId().equals(item.getProductId())) {
				flag = true;
				i.setQuantity(i.getQuantity()-1);
				i.setItemTotal(item.getItemTotal());
				if(i.getQuantity()==0)
					items.remove(item);
			}
		}
		if(flag == false)
			items.removeIf(e -> e.getId().equals(item.getId()));
		generateTotalCart();
	}
	
	private void generateTotalCart() {
		cartTotal = 0.0;
		for(Item item: items) 
			cartTotal+=item.getItemTotal();		
	}
}
