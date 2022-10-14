package com.example.webservice.controlers;

import com.example.webservice.entities.Tags;
import com.example.webservice.servises.TagsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tag")
public class TagsController {

    private final TagsService tagsService;

    @PostMapping("/add")
    private Tags saveTag(@RequestBody Tags tag){
        return tagsService.buildAndSave(tag);
    }
}
