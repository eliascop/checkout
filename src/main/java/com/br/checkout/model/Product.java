package com.br.checkout.model;

import java.util.Comparator;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "products")
@EqualsAndHashCode @AllArgsConstructor
public class Product {
	
	@Transient
	public static final String SEQUENCE_NAME = "product_sequence";
	@Id @Getter @Setter
	private String id;
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter
	private Double price;

	@Override
	public String toString() {
		return String.format(
			"Productm[id=%s, name='%s', price='%s']",id, name,price
		);
	}
	public static Comparator<Product> COMPARE_BY_NAME = new Comparator<Product>() {
		public int compare(Product p1, Product p2) {
			return p1.name.compareTo(p2.name);
		}
	};
}
	
	