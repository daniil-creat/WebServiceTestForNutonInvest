package com.example.webservice.servises.Impl;

import com.example.webservice.entities.Folders;
import com.example.webservice.entities.Request;
import com.example.webservice.entities.Tags;
import com.example.webservice.exceptions.ServiceException;
import com.example.webservice.repositories.FolderRepository;
import com.example.webservice.repositories.RequestRepository;
import com.example.webservice.repositories.TagsRepository;
import com.example.webservice.repositories.elasticrepo.RequestElasticRepository;
import com.example.webservice.servises.NextSequenceService;
import com.example.webservice.servises.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.webservice.entities.Request.REQUEST_SEQUENCE_NAME;

@Service
@RequiredArgsConstructor
@Log4j2
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final NextSequenceService nextSequenceService;
    private final TagsRepository tagsRepository;
    private final FolderRepository folderRepository;
    private final RequestElasticRepository requestElasticRepository;

    @Override
    public Request buildAndSaveRequest(Request request) {
        log.info("Start request service, build and save, input:{}", request);
        Request requestForDb = Request.builder()
                .id(nextSequenceService.getNextSequence(REQUEST_SEQUENCE_NAME))
                .length(request.getLength())
                .modifiedDate(request.getModifiedDate())
                .text(request.getText())
                .build();
        Request savedRequest = requestRepository.save(requestForDb);
        requestElasticRepository.save(requestForDb);
        log.info("End request service, output:{}", savedRequest);
        return savedRequest;
    }

    @Override
    public Request linkTagWithRequest(Long tagId, Long requestId) {
        log.info("Start request service, add tag in request, input tagId={}, requestId={}", tagId, requestId);
        Request request = checkRequestExistInDb(requestId);
        Tags tagForRequest = checkTagExistInDb(tagId);
        List<Tags> tagsOfRequest = getTagsOfRequest(request);
        if (checkCountTagsInRequest(tagsOfRequest) && !tagsOfRequest.contains(tagForRequest)) {
            tagsOfRequest.add(tagForRequest);
            request.setTags(tagsOfRequest);
            Request savedRequest = requestRepository.save(request);
            requestElasticRepository.save(request);
            log.info("End request service, output:{}", savedRequest);
            return savedRequest;
        } else {
            throw new ServiceException("Request has tag yet");
        }
    }

    @Override
    public Request linkRequestWithFolder(Long requestId, Long folderId) {
        log.info("Start request service, add folder in request, input folderId={}, requestId={}", folderId, requestId);
        Request request = checkRequestExistInDb(requestId);
        Folders folder = checkFolderExistInDb(folderId);
        request.setFolder(folder);
        Request savedRequest = requestRepository.save(request);
        requestElasticRepository.save(request);
        log.info("End request service, output:{}", savedRequest);
        return savedRequest;
    }

    @Override
    public List<Request> getAllRequests() {
        log.info("Start request service, get all requests");
        List<Request> requests = List.of(requestRepository.findAll().iterator().next());
        log.info("End request service, output:{}", requests);
        return requests;
    }

    @Override
    public Request getRequestByTagId(Long tagId) {
        log.info("Start request service, get request by tagId={}", tagId);
        Request request = requestRepository.getRequestByTagsId(tagId);
        log.info("End request service, output={}", request);
        return request;
    }

    @Override
    public Request getRequestByFolderId(Long folderId) {
        log.info("Start request service, get request by folderId={}", folderId);
        Request request = requestRepository.getRequestByFolderId(folderId);
        log.info("End request service, output={}", request);
        return request;
    }

    @Override
    public List<Request> getRequestByFieldText(String text) {
        log.info("Start request service, get request by field text, input ={}", text);
        List<Request> requests = requestElasticRepository.findByText(text);
        log.info("End request service, output ={}", requests);
        return requests;
    }

    private Request checkRequestExistInDb(Long requestId) {
        Optional<Request> optionalRequest = requestRepository.findById(requestId);
        if (optionalRequest.isPresent()) {
            return optionalRequest.get();
        } else {
            throw new ServiceException("This request is not exist in data base");
        }
    }

    private Tags checkTagExistInDb(Long tagId) {
        Optional<Tags> optionalTags = tagsRepository.findById(tagId);
        if (optionalTags.isPresent()) {
            return optionalTags.get();
        } else {
            throw new ServiceException("This tag is not exist in data base");
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
            throw new ServiceException("Count tags in request 10");
        }
    }

    private Folders checkFolderExistInDb(Long folderId) {
        Optional<Folders> optionalTags = folderRepository.findById(folderId);
        if (optionalTags.isPresent()) {
            return optionalTags.get();
        } else {
            throw new ServiceException("This folder is not exist in data base");
        }
    }
}
