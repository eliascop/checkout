package com.br.checkout.model;

import java.util.Comparator;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "items")
@EqualsAndHashCode @AllArgsConstructor
public class Item {
	
	@Transient
    public static final String SEQUENCE_NAME = "item_sequence";
	
	@Id
	@Getter @Setter private String id;
	@Getter @Setter private String productId;
	@Getter @Setter private String cartId;
	@Getter @Setter private int quantity;
	@Getter @Setter private Double itemTotal;	
	
	@Override
	public String toString() {
		return String.format(
	        "Item[id=%s, productId='%s', quantity='%s', itemTotal='%s']",id, productId,quantity,itemTotal
        );
	}
	
	public static Comparator<Item> COMPARE_BY_PRODUCT = new Comparator<Item>() {
        public int compare(Item item1, Item item2) {
            return item1.productId.compareTo(item2.productId);
        }
    };
	
}
