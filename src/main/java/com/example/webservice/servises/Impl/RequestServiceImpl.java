package com.example.webservice.servises.Impl;

import com.example.webservice.entities.Folders;
import com.example.webservice.entities.Request;
import com.example.webservice.entities.Tags;
import com.example.webservice.repositories.FolderRepository;
import com.example.webservice.repositories.RequestRepository;
import com.example.webservice.repositories.TagsRepository;
import com.example.webservice.servises.NextSequenceService;
import com.example.webservice.servises.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.webservice.entities.Request.REQUEST_SEQUENCE_NAME;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final NextSequenceService nextSequenceService;
    private final TagsRepository tagsRepository;
    private final FolderRepository folderRepository;

    @Override
    public Request buildAndSaveRequest(Request request) {
        Request requestForDb = Request.builder()
                .id(nextSequenceService.getNextSequence(REQUEST_SEQUENCE_NAME))
                .length(request.getLength())
                .modifiedDate(request.getModifiedDate())
                .text(request.getText())
                .tags(request.getTags())
                .build();
        return requestRepository.save(requestForDb);
    }

    @Override
    public Request addTagInRequest(Long tagId, Long requestId) {
        Request request = checkRequestExistInDb(requestId);
        Tags tagForRequest = checkTagExistInDb(tagId);
        List<Tags> tagsOfRequest = getTagsOfRequest(request);
        List<Request> requestsList = new ArrayList<>();
        if (checkCountTagsInRequest(tagsOfRequest) && !tagsOfRequest.contains(tagForRequest)) {
            tagsOfRequest.add(tagForRequest);
            requestsList.add(request);
            request.setTags(tagsOfRequest);
            tagForRequest.setRequests(requestsList);
            tagsRepository.save(tagForRequest);
            return requestRepository.save(request);
        } else {
            throw new RuntimeException("Request has tag yet");
        }
    }

    @Override
    public Request addRequestInFolder(Long requestId, Long folderId) {
        Request request = checkRequestExistInDb(requestId);
        Folders folderOfDb = checkFolderExistInDb(folderId);
        Folders folderOfRequest = request.getFolder();
        request.setFolder(folderOfRequest);
        folderOfDb.setRequests(List.of(request));
        folderRepository.save(folderOfDb);
        return requestRepository.save(request);
    }

    private Request checkRequestExistInDb(Long requestId) {
        Optional<Request> optionalRequest = requestRepository.findById(requestId);
        if (optionalRequest.isPresent()) {
            return optionalRequest.get();
        } else {
            throw new RuntimeException("This request is not exist in data base");
        }
    }

    private Tags checkTagExistInDb(Long tagId) {
        Optional<Tags> optionalTags = tagsRepository.findById(tagId);
        if (optionalTags.isPresent()) {
            return optionalTags.get();
        } else {
            throw new RuntimeException("This tag is not exist in data base");
        }
    }

    private List<Tags> getTagsOfRequest(Request request) {
        List<Tags> tagsList = request.getTags();
        if (tagsList != null) {
            return tagsList;
        } else {
            return new ArrayList<>();
        }
    }

    private boolean checkCountTagsInRequest(List<Tags> tags) {
        if (tags.size() < 10) {
            return true;
        } else {
            throw new RuntimeException("Count tags in request 10");
        }
    }

    private Folders checkFolderExistInDb(Long folderId) {
        Optional<Folders> optionalTags = folderRepository.findById(folderId);
        if (optionalTags.isPresent()) {
            return optionalTags.get();
        } else {
            throw new RuntimeException("This folder is not exist in data base");
        }
    }
}
