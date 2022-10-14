package com.example.webservice.entities;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.core.annotation.AliasFor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "request_collection")
public class Request {

    @Transient
    public static final String REQUEST_SEQUENCE_NAME = "request_sequence";

    @Id
    Long id;
    String text;
    Long modifiedDate;
    Long length;
    List<Tags> tags;
    Folders folder;
}
