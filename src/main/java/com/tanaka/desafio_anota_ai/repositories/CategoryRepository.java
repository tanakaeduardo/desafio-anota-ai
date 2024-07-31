package com.tanaka.desafio_anota_ai.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.tanaka.desafio_anota_ai.domain.category.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

}
