package com.example.webservice.servises;

import com.example.webservice.entities.Folders;

import java.util.List;

public interface FolderService {
    Folders buildAndSave(Folders folder);

    List<Folders> getAllFolders();
}
