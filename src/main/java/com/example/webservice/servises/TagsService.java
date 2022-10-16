package com.example.webservice.servises;

import com.example.webservice.entities.Tags;

import java.util.List;

public interface TagsService {
    Tags buildAndSave(Tags tag);

    List<Tags> getAllTags();
}
