package com.example.webservice.repositories;

import com.example.webservice.entities.Request;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends MongoRepository<Request, Long> {

    @Query("{'tags.id': ?0}")
    Request getRequestByTagsId(Long tagId);

    @Query("{'folder.id': ?0}")
    Request getRequestByFolderId(Long folderId);
}
