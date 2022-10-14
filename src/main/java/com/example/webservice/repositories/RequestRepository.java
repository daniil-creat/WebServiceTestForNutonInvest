package com.example.webservice.repositories;

import com.example.webservice.entities.Request;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends MongoRepository<Request, Long> {
}
