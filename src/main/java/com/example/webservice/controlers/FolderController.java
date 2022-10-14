package com.example.webservice.controlers;

import com.example.webservice.entities.Folders;
import com.example.webservice.entities.Tags;
import com.example.webservice.servises.FolderService;
import com.example.webservice.servises.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/folder")
public class FolderController {

    private final FolderService folderService;

    @PostMapping("/add")
    private Folders saveFolder(@RequestBody Folders folder){
        return folderService.buildAndSave(folder);
    }
}
