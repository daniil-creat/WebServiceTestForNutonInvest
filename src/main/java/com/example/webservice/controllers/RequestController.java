package com.example.webservice.controllers;

import com.example.webservice.entities.Request;
import com.example.webservice.servises.RequestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "Save new request in data base")
    private Request saveRequest(@ApiParam(value = "Request object") @RequestBody Request request) {
        return requestService.buildAndSaveRequest(request);
    }

    @PostMapping("/linkTag/{requestId}/{tagId}")
    @ApiOperation(value = "Add tag in request and save in data base")
    private Request linkTagWithRequest(@PathVariable Long requestId, @PathVariable Long tagId) throws Exception {
        log.info("Start request controller,add tag in request, input requestId={}, tagId={}", requestId, tagId);
        return requestService.linkTagWithRequest(tagId, requestId);
    }

    @PostMapping("/linkFolder/{requestId}/{folderId}")
    @ApiOperation(value = "Add request in folder and save in data base")
    private Request linkRequestWithFolder(@PathVariable Long requestId, @PathVariable Long folderId) {
        log.info("Start request controller,add request in folser, input requestId={}, folderId={}", requestId, folderId);
        return requestService.linkRequestWithFolder(requestId, folderId);
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "Get all requests of data base")
    private List<Request> getAllRequests() {
        log.info("Start request controller,get all request");
        return requestService.getAllRequests();
    }

    @GetMapping("/getRequestByTag/{tagId}")
    @ApiOperation(value = "Get request by tagId of data base")
    private Request getRequestByTag(@PathVariable Long tagId) {
        log.info("Start request controller,get request by tagId, input tagId={}", tagId);
        return requestService.getRequestByTagId(tagId);
    }

    @GetMapping("/getRequestByFolder/{folderId}")
    @ApiOperation(value = "Get request by folderId of data base")
    private Request getRequestByFolder(@PathVariable Long folderId) {
        log.info("Start request controller,get request by folderId, input folderId={}", folderId);
        return requestService.getRequestByFolderId(folderId);
    }

    @GetMapping("/getRequestByFieldText/{text}")
    @ApiOperation(value = "Get request by field text with elasticsearch")
    private List<Request> getRequestByFieldText(@PathVariable String text) {
        log.info("Start request controller,get request by field text, input text={}", text);
        return requestService.getRequestByFieldText(text);
    }
}
