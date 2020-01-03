package com.br.checkout.model;

import java.util.Comparator;

import javax.validation.constraints.Email;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "users")
@EqualsAndHashCode @AllArgsConstructor
public class User {
	
	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	
	@Id @Getter @Setter
	private String id;
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter @Email 
	private String email;
	
	@Override
	public String toString() {
		return String.format(
	        "User[id=%s, name='%s', email='%s']",id, name, email
        );
	}
	
	public static Comparator<User> COMPARE_BY_NAME = new Comparator<User>() {
        public int compare(User u1, User u2) {
            return u1.name.compareTo(u2.name);
        }
    };
}
