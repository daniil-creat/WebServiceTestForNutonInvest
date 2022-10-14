package com.example.webservice.servises;

import com.example.webservice.entities.Request;

public interface RequestService {
    Request buildAndSaveRequest(Request request);

    Request addTagInRequest(Long tagId, Long requestId);

    Request addRequestInFolder(Long requestId, Long folderId);
}
