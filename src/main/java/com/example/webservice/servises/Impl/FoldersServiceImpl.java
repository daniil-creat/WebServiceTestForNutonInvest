package com.example.webservice.servises.Impl;

import com.example.webservice.entities.Folders;
import com.example.webservice.entities.Request;
import com.example.webservice.entities.Tags;
import com.example.webservice.repositories.FolderRepository;
import com.example.webservice.repositories.RequestRepository;
import com.example.webservice.servises.FolderService;
import com.example.webservice.servises.NextSequenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoldersServiceImpl implements FolderService {

    private final FolderRepository folderRepository;
    private final NextSequenceService nextSequenceService;

    @Override
    public Folders buildAndSave(Folders folder) {
        Folders folderForDb = Folders.builder()
                .id(nextSequenceService.getNextSequence(Folders.FOLDER_SEQUENCE_NAME))
                .folderName(folder.getFolderName())
                .build();

        return folderRepository.save(folderForDb);
    }
}
