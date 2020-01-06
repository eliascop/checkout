package com.br.checkout.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.checkout.model.Product;
import com.br.checkout.repository.ProductRepository;
import com.br.checkout.utils.Utils;

@Service 
public class ProductService {

	@Autowired 
	private ProductRepository productRepository;
	
	public Product insert(Product product) {		
		Optional<Product> local = productRepository.findProductByName(product.getName());
		if(!local.isPresent())
			if(product.getPrice()<=0)
				throw new RuntimeException("Field price must be bigger than 0");
			else
				return this.productRepository.save(product);
		else
			throw new RuntimeException("There's already a product with this name!");
	}
	 
	public Product update(Product product) {
		Product savedProduct = productRepository.findById(product.getId()).orElseThrow(() -> new RuntimeException("Product not found"));		
		String[] nullPropertyNames = Utils.getNullPropertyNames(product);
		BeanUtils.copyProperties(product, savedProduct, nullPropertyNames);
		return productRepository.save(savedProduct);		
	}
	
	public void delete(String id) {
		productRepository.deleteById(id);
	}

	public List<Product> findAll(){
		List<Product> products = this.productRepository.findAll();
		Collections.sort(products, Product.COMPARE_BY_NAME);
		return products;		
	}
	
	public Optional<Product> findById(String id){
		return this.productRepository.findById(id);
	}
	
	public Optional<Product> findByEmail(String name) {
		return this.productRepository.findProductByName(name);
	}
	
}
