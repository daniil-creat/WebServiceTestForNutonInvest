package com.example.webservice.repositories;

import com.example.webservice.entities.Folders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends MongoRepository<Folders, Long> {
}
