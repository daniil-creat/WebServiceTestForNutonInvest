package com.example.webservice.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "folders")
@Data
@Builder
public class Folders {

    @Transient
    public static final String FOLDER_SEQUENCE_NAME = "folders_sequence";

    @Id
    Long id;
    @Field(name = "folder name")
    String folderName;
}
