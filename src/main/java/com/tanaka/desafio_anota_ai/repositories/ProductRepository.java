package com.tanaka.desafio_anota_ai.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tanaka.desafio_anota_ai.domain.product.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
