package com.tanaka.desafio_anota_ai.domain.category;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {
	
	@Id
	private String Id;
	private String title;
	private String description;
	private String ownerId;

}
