package com.example.webservice.servises;

import com.example.webservice.entities.Folders;

public interface FolderService {
    Folders buildAndSave(Folders folder);
}
