package com.mihir.MyFinace.repository;

import com.mihir.MyFinace.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface TransactionRepository extends MongoRepository<Transaction, String> {}
