package com.br.checkout.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.checkout.model.Item;
import com.br.checkout.service.ItemService;

@RestController
@RequestMapping("item")
public class ItemResource {

	@Autowired
	private ItemService itemService;
	
	@GetMapping(value = { "", "/" })
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(this.itemService.findAll());
	}
	
	@PostMapping
	public ResponseEntity<?> insert(@Valid @RequestBody Item item) {
		try {
			itemService.insert(item);			
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());			
		}
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<?> update(@PathVariable String codigo, @Valid @RequestBody Item item) {
		item.setId(codigo); 
		return ResponseEntity.ok(itemService.update(item));  
	}

	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> delete(@PathVariable String codigo) {
		itemService.delete(codigo);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> find(@PathVariable String codigo) {
		return ResponseEntity.ok(itemService.findById(codigo));
	}

}
