package com.example.webservice.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "folders_collection")
@Data
@Builder
public class Folders {

    @Transient
    public static final String FOLDER_SEQUENCE_NAME = "folders_sequence";

    @Id
    Long id;
    String folderName;
    List<Request> requests;
}
