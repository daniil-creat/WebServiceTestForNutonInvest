package com.example.webservice.controlers;

import com.example.webservice.entities.Folders;
import com.example.webservice.entities.Request;
import com.example.webservice.entities.Tags;
import com.example.webservice.repositories.FolderRepository;
import com.example.webservice.repositories.RequestRepository;
import com.example.webservice.servises.NextSequenceService;
import com.example.webservice.servises.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.webservice.entities.Request.*;

@RestController
@RequestMapping("/api/request")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @PostMapping("/add")
    private Request saveRequest(@RequestBody Request request) {
        return requestService.buildAndSaveRequest(request);
    }

    @PostMapping("/addtag/{requestId}/{tagId}")
    private Request addTagInRequest(@PathVariable Long requestId, @PathVariable Long tagId) throws Exception {
        return requestService.addTagInRequest(tagId, requestId);
    }

    @PostMapping("/addrequest/{requestId}/{folderId}")
    private Request addRequestInFolder(@PathVariable Long requestId, @PathVariable Long folderId){
        return requestService.addRequestInFolder(requestId, folderId);
    }
}
