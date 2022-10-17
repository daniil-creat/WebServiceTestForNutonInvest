package com.example.webservice.controllers;

import com.example.webservice.entities.Folders;
import com.example.webservice.servises.FolderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/folder")
@Log4j2
public class FolderController {

    private final FolderService folderService;

    @PostMapping("/add")
    @ApiOperation(value = "Save new folder in data base")
    private Folders saveFolder(@ApiParam(value = "Folder object") @RequestBody Folders folder) {
        log.info("Start folder controller,save folder, input : {}", folder);
        return folderService.buildAndSave(folder);
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "Get all folders of data base")
    private List<Folders> getAllFolders() {
        log.info("Start folder controller, get all folders");
        return folderService.getAllFolders();
    }
}
