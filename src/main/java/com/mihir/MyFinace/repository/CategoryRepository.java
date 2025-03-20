package com.mihir.MyFinace.repository;

import com.mihir.MyFinace.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface CategoryRepository extends MongoRepository<Category, String> {}
