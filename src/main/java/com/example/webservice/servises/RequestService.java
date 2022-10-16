package com.example.webservice.servises;

import com.example.webservice.entities.Request;

import java.util.List;

public interface RequestService {
    Request buildAndSaveRequest(Request request);

    Request linkTagWithRequest(Long tagId, Long requestId);

    Request linkRequestWithFolder(Long requestId, Long folderId);

    List<Request> getAllRequests();

    Request getRequestByTagId(Long tagId);

    Request getRequestByFolderId(Long folderId);

    List<Request> getRequestByFieldText(String text);
}
