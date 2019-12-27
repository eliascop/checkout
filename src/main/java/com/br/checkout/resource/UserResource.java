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

import com.br.checkout.model.User;
import com.br.checkout.service.UserService;

@RestController
@RequestMapping("user")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(this.userService.findAll());
	}
	
	@PostMapping
	public ResponseEntity<?> insert(@Valid @RequestBody User user) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.insert(user));			
		}catch(Exception e) {
			return ResponseEntity.noContent().build();			
		}
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<?> update(@PathVariable String codigo, @Valid @RequestBody User user) {
		user.setId(codigo); 
		return ResponseEntity.ok(userService.update(user));  
	}

	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> delete(@PathVariable String codigo) {
		userService.delete(codigo);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> find(@PathVariable String codigo) {
		return ResponseEntity.ok(userService.findById(codigo));
	}

}
