package com.example.webservice.repositories.elasticrepo;

import com.example.webservice.entities.Request;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestElasticRepository extends ElasticsearchRepository<Request, Long> {
    List<Request> findByText(String text);
}
