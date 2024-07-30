package com.tanaka.desafio_anota_ai.domain.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tanaka.desafio_anota_ai.domain.category.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collation = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
	
	@Id
	private String id;
	private String title;
	private String descripction;
	private String ownerId;
	private Integer price;
	private Category category;

}
