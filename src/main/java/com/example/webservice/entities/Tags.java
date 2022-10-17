package com.example.webservice.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "tags")
@Data
@Builder
public class Tags {

    @Transient
    public static final String TAG_SEQUENCE_NAME = "tags_sequence";

    @Id
    Long id;
    @Field(name = "tag name")
    String tagName;
}
