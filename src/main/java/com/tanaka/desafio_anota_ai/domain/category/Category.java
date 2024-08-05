package com.tanaka.desafio_anota_ai.domain.category;

import org.json.JSONObject;
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
	
	@Override
	public String toString() {
		JSONObject json = new JSONObject();
		json.put("title", this.title);
		json.put("description", this.description);
		json.put("ownerId", this.ownerId);
		json.put("id", this.id);
		json.put("type", "category");
		
		return json.toString();
	}

}
