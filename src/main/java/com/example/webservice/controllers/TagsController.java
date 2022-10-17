package com.example.webservice.controllers;

import com.example.webservice.entities.Tags;
import com.example.webservice.servises.TagsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tag")
@Log4j2
public class TagsController {

    private final TagsService tagsService;

    @PostMapping("/add")
    @ApiOperation(value = "Save tag in data base")
    private Tags saveTag(@ApiParam(value = "tag object") @RequestBody Tags tag) {
        log.info("Start tag controller, save tag, input:{}", tag);
        return tagsService.buildAndSave(tag);
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "Get all tags of data base")
    private List<Tags> getAllTags() {
        log.info("Start tag controller, get all tags");
        return tagsService.getAllTags();
    }
}
