package com.example.webservice.servises.Impl;

import com.example.webservice.entities.Folders;
import com.example.webservice.repositories.FolderRepository;
import com.example.webservice.servises.FolderService;
import com.example.webservice.servises.NextSequenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class FoldersServiceImpl implements FolderService {

    private final FolderRepository folderRepository;
    private final NextSequenceService nextSequenceService;

    @Override
    public Folders buildAndSave(Folders folder) {
        log.info("Start folder service, build and save, input:{}", folder);
        Folders folderForDb = Folders.builder()
                .id(nextSequenceService.getNextSequence(Folders.FOLDER_SEQUENCE_NAME))
                .folderName(folder.getFolderName())
                .build();
        Folders savedFolder = folderRepository.save(folderForDb);
        log.info("End folder service, build and save, output:{}", savedFolder);
        return savedFolder;
    }

    @Override
    public List<Folders> getAllFolders() {
        log.info("Start folder service, get all folders");
        List<Folders> foldersOfDb = folderRepository.findAll();
        log.info("End folder service, output:{}", foldersOfDb);
        return foldersOfDb;
    }
}
