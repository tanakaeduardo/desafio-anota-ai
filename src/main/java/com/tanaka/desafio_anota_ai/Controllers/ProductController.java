package com.tanaka.desafio_anota_ai.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanaka.desafio_anota_ai.domain.product.Product;
import com.tanaka.desafio_anota_ai.domain.product.ProductDTO;
import com.tanaka.desafio_anota_ai.services.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	private ProductService service;
	
	public ProductController(ProductService service) {
		this.service = service;
		
	}
	
	@PostMapping
	public ResponseEntity<Product> insert(@RequestBody ProductDTO productData){
		Product newProduct = this.service.insert(productData);
		return ResponseEntity.ok().body(newProduct);
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getAll() {
		List<Product> products = this.service.getAll();
		return ResponseEntity.ok().body(products);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable("id") String id, @RequestBody ProductDTO productData){
		Product updateProduct = this.service.update(id, productData);
		return ResponseEntity.ok().body(updateProduct);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Product> delete(@PathVariable("id") String id){
		this.service.delete(id);
		return ResponseEntity.noContent().build();
	}


}
