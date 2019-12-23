package com.br.checkout.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "database_sequences")
@EqualsAndHashCode
public class DatabaseSequence {
    @Id
    @Getter @Setter
    private String id;
    @Getter @Setter
    private Long sequence;
 
}
