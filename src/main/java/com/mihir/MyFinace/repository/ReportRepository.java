package com.mihir.MyFinace.repository;

import com.mihir.MyFinace.models.Report;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReportRepository extends MongoRepository<Report, String> {
    @Query("{ 'userId' : ?0 }")
    List<Report> findByUserId(String userId);
}