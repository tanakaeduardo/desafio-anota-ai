package com.tanaka.desafio_anota_ai.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tanaka.desafio_anota_ai.domain.category.Category;
import com.tanaka.desafio_anota_ai.domain.category.exceptions.CategoryNotFoundException;
import com.tanaka.desafio_anota_ai.domain.product.Product;
import com.tanaka.desafio_anota_ai.domain.product.ProductDTO;
import com.tanaka.desafio_anota_ai.domain.product.Exceptions.ProductNotFoundException;
import com.tanaka.desafio_anota_ai.repositories.ProductRepository;
import com.tanaka.desafio_anota_ai.services.aws.AwsSnsService;
import com.tanaka.desafio_anota_ai.services.aws.MessageDTO;

@Service
public class ProductService {
	
	private CategoryService categoryService;	
	private ProductRepository repository;
	private final AwsSnsService snsService;

	
	public ProductService( CategoryService categoryService, ProductRepository repository, AwsSnsService snsService) {

		this.repository = repository;
		this.categoryService = categoryService;
		this.snsService = snsService;
	}
	
	public Product insert (ProductDTO productData) {
		Category category = this.categoryService.getById(productData.categoryId())
				.orElseThrow(CategoryNotFoundException :: new);
		Product newProduct = new Product(productData);

		this.repository.save(newProduct);
		
		this.snsService.publish(new MessageDTO(newProduct.getOwnerId()));
		
		return newProduct;
	}
	
	public List<Product> getAll(){
		return this.repository.findAll();
	}
	
	public Product update (String id, ProductDTO productData) {
		Product product = this.repository.findById(id)
				.orElseThrow(ProductNotFoundException :: new);
		
		if(productData.categoryId() != null) {
			this.categoryService.getById(productData.categoryId())
				.orElseThrow(CategoryNotFoundException::new);
			product.setCategory(productData.categoryId());
		}

		if(!productData.title().isEmpty()) {
			product.setTitle(productData.title());
		}
		if(!productData.description().isEmpty()) {
			product.setDescription(productData.description());
		}
		if(!productData.ownerId().isEmpty()) {
			product.setOwnerId(productData.ownerId());
		}
		if(productData.price() != null) {
			product.setPrice(productData.price());
		}
		
		this.repository.save(product);
		
		this.snsService.publish(new MessageDTO(product.getOwnerId()));
		
		return product;
	}
	
	public void delete(String id) {
		Product product = this.repository.findById(id)
				.orElseThrow(ProductNotFoundException :: new);
		this.repository.delete(product);
		return;
	}

}
