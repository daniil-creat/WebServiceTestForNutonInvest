package com.example.webservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Document(indexName = "request_index")
public class Request {

    @Transient
    public static final String REQUEST_SEQUENCE_NAME = "request_sequence";

    @Id
    Long id;
    String text;
    @Field(name="modified date")
    Long modifiedDate;
    Long length;
    List<Tags> tags;
    Folders folder;
}
