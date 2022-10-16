package com.example.webservice.controllers;

import com.example.webservice.entities.Request;
import com.example.webservice.servises.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request")
@RequiredArgsConstructor
@Log4j2
public class RequestController {

    private final RequestService requestService;

    @PostMapping("/add")
    private Request saveRequest(@RequestBody Request request) {
        return requestService.buildAndSaveRequest(request);
    }

    @PostMapping("/linkTag/{requestId}/{tagId}")
    private Request linkTagWithRequest(@PathVariable Long requestId, @PathVariable Long tagId) throws Exception {
        log.info("Start request controller,add tag in request, input requestId={}, tagId={}", requestId, tagId);
        return requestService.linkTagWithRequest(tagId, requestId);
    }

    @PostMapping("/linkFolder/{requestId}/{folderId}")
    private Request linkRequestWithFolder(@PathVariable Long requestId, @PathVariable Long folderId) {
        log.info("Start request controller,add request in folser, input requestId={}, folderId={}", requestId, folderId);
        return requestService.linkRequestWithFolder(requestId, folderId);
    }

    @GetMapping("/getAll")
    private List<Request> getAllRequests() {
        log.info("Start request controller,get all request");
        return requestService.getAllRequests();
    }

    @GetMapping("/getRequestByTag/{tagId}")
    private Request getRequestByTag(@PathVariable Long tagId) {
        log.info("Start request controller,get request by tagId, input tagId={}", tagId);
        return requestService.getRequestByTagId(tagId);
    }

    @GetMapping("/getRequestByFolder/{folderId}")
    private Request getRequestByFolder(@PathVariable Long folderId) {
        log.info("Start request controller,get request by folderId, input folderId={}", folderId);
        return requestService.getRequestByFolderId(folderId);
    }

    @GetMapping("/getRequestByFieldText/{text}")
    private List<Request> getRequestByFieldText(@PathVariable String text) {
        log.info("Start request controller,get request by field text, input text={}", text);
        return requestService.getRequestByFieldText(text);
    }
}
