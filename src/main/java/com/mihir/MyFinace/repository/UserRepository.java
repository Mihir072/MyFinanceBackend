package com.mihir.MyFinace.repository;

import com.mihir.MyFinace.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    @Query("{ 'name' : ?0 }")
    User findByName(String name);
     Optional<User> findById(String id);
}