package com.tanaka.desafio_anota_ai.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tanaka.desafio_anota_ai.domain.category.Category;
import com.tanaka.desafio_anota_ai.domain.category.CategoryDTO;
import com.tanaka.desafio_anota_ai.domain.category.exceptions.CategoryNotFoundException;
import com.tanaka.desafio_anota_ai.repositories.CategoryRepository;
import com.tanaka.desafio_anota_ai.services.aws.AwsSnsService;
import com.tanaka.desafio_anota_ai.services.aws.MessageDTO;

@Service
public class CategoryService {
	
	private  CategoryRepository repository;
	private AwsSnsService snsService;
	
	public CategoryService(CategoryRepository repository, AwsSnsService snsService) {
		this.repository = repository;
		this.snsService = snsService;
	}
	
	public Category insert (CategoryDTO categoryData) {
		Category newCategory = new Category(categoryData);
		this.repository.save(newCategory);
		this.snsService.publish(new MessageDTO(newCategory.getOwnerId()));
		return newCategory;
	}
	
	public List<Category> getAll(){
		return this.repository.findAll();
	}
	
	public Optional<Category> getById(String id){
		return this.repository.findById(id);
	}
	
	public Category update (String id, CategoryDTO categoryData) {
		Category category = this.repository.findById(id).orElseThrow(CategoryNotFoundException :: new);
		if(!categoryData.title().isEmpty()) {
			category.setTitle(categoryData.title());
		}
		if(!categoryData.description().isEmpty()) {
			category.setDescription(categoryData.description());
		}
		if(!categoryData.ownerId().isEmpty()) {
			category.setOwnerId(categoryData.ownerId());
		}
		this.repository.save(category);
		
		this.snsService.publish(new MessageDTO(category.getOwnerId()));
		
		return category;
	}
	
	public void delete(String id) {
		Category category = this.repository.findById(id).orElseThrow(CategoryNotFoundException :: new);
		this.repository.delete(category);
		return;
	}
}
