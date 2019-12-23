package com.br.checkout.model;

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
	@Getter @Setter private String name;
	@Getter @Setter private Double value;
	
	@Override
	public String toString() {
		return String.format(
	        "Item[id=%s, name='%s', value='%s']",id, name, value
        );
	}
	
}
