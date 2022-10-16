package com.example.webservice.entities;

import io.github.kaiso.relmongo.annotation.CascadeType;
import io.github.kaiso.relmongo.annotation.FetchType;
import io.github.kaiso.relmongo.annotation.OneToMany;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "tags")
@Data
@Builder
public class Tags {

    @Transient
    public static final String TAG_SEQUENCE_NAME = "tags_sequence";

    @Id
    Long id;
    String tagName;
}
