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
	private String id;
	private String title;
	private String description;
	private String ownerId;
	
	public Category(CategoryDTO categoryDto) {
		this.title = categoryDto.title();
		this.description = categoryDto.description();
		this.ownerId = categoryDto.ownerId();
	}

}
