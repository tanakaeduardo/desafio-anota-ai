package com.tanaka.desafio_anota_ai.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tanaka.desafio_anota_ai.domain.category.Category;
import com.tanaka.desafio_anota_ai.domain.category.exceptions.CategoryNotFoundException;
import com.tanaka.desafio_anota_ai.domain.product.Product;
import com.tanaka.desafio_anota_ai.domain.product.ProductDTO;
import com.tanaka.desafio_anota_ai.domain.product.Exceptions.ProductNotFoundException;
import com.tanaka.desafio_anota_ai.repositories.ProductRepository;

@Service
public class ProductService {
	
	private ProductRepository repository;
	private CategoryService categoryService;
	
	public ProductService(ProductRepository repository,  CategoryService categoryService) {
		this.repository = repository;
		this.categoryService = categoryService;
	}
	
	public Product insert (ProductDTO productData) {
		Category category = this.categoryService.getById(productData.categoryId())
				.orElseThrow(CategoryNotFoundException :: new);
		Product newProduct = new Product(productData);
		newProduct.setCategory(category);
		this.repository.save(newProduct);
		return newProduct;
	}
	
	public List<Product> getAll(){
		return this.repository.findAll();
	}
	
	public Product update (String id, ProductDTO productData) {
		Product product = this.repository.findById(id)
				.orElseThrow(ProductNotFoundException :: new);
		this.categoryService.getById(productData.categoryId())
				.ifPresent(product::setCategory);
		if(!productData.title().isEmpty()) {
			product.setTitle(productData.title());
		}
		if(!productData.description().isEmpty()) {
			product.setDescripction(productData.description());
		}
		if(!productData.ownerId().isEmpty()) {
			product.setOwnerId(productData.ownerId());
		}
		if(productData.price() != null) {
			product.setPrice(productData.price());
		}
		
		this.repository.save(product);
		return product;
	}
	
	public void delete(String id) {
		Product product = this.repository.findById(id)
				.orElseThrow(ProductNotFoundException :: new);
		this.repository.delete(product);
		return;
	}

}
