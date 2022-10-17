package com.example.webservice;

import com.example.webservice.entities.Request;
import com.example.webservice.servises.RequestService;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RequestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RequestService requestService;

    @Test
    void addRequestTest() throws Exception {
        File fileResponse = ResourceUtils.getFile("classpath:Request.json");
        String requestStr = new String(Files.readAllBytes(fileResponse.toPath()));
        File fileRequestPojo = ResourceUtils.getFile("classpath:RequestPojo.json");
        String requestPojoStr = new String(Files.readAllBytes(fileRequestPojo.toPath()));
        Request requestPojo =  new Gson().fromJson(requestPojoStr, Request.class);

        when(requestService.buildAndSaveRequest(any())).thenReturn(requestPojo);

       mockMvc.perform(post("/api/request/add")
                        .contentType(MediaType.APPLICATION_JSON).content(requestStr))
                .andExpect(status().isOk())
                .andReturn();

        verify(requestService).buildAndSaveRequest(requestPojo);
    }

    @Test
    void linkTagWithRequestTest() throws Exception {
        when(requestService.linkTagWithRequest(anyLong(), anyLong())).thenReturn(new Request(1L, "hello", 1L, 1L, null, null));

        mockMvc.perform(post("/api/request/linkTag/1/1"))
                .andExpect(status().isOk())
                .andReturn();

        verify(requestService).linkTagWithRequest(1L, 1L);
    }

    @Test
    void linkFolderWithRequestTest() throws Exception {
        when(requestService.linkRequestWithFolder(anyLong(), anyLong())).thenReturn(new Request(1L, "hello", 1L, 1L, null, null));

        mockMvc.perform(post("/api/request/linkFolder/1/1"))
                .andExpect(status().isOk())
                .andReturn();

        verify(requestService).linkRequestWithFolder(1L, 1L);
    }

    @Test
    void getAllTest() throws Exception {
        when(requestService.getAllRequests()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/request/getAll"))
                .andExpect(status().isOk())
                .andReturn();

        verify(requestService).getAllRequests();
    }

    @Test
    void getRequestByTagTest() throws Exception {
        when(requestService.getRequestByTagId(anyLong())).thenReturn(new Request(1L, "hello", 1L, 1L, null, null));

        mockMvc.perform(get("/api/request/getRequestByTag/1"))
                .andExpect(status().isOk())
                .andReturn();

        verify(requestService).getRequestByTagId(1L);
    }

    @Test
    void getRequestByFolderTest() throws Exception {
        when(requestService.getRequestByTagId(anyLong())).thenReturn(new Request(1L, "hello", 1L, 1L, null, null));

        mockMvc.perform(get("/api/request/getRequestByFolder/1"))
                .andExpect(status().isOk())
                .andReturn();

        verify(requestService).getRequestByFolderId(1L);
    }

    @Test
    void getRequestByFieldTextTest() throws Exception {
        when(requestService.getRequestByFieldText(anyString())).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/request/getRequestByFieldText/hello"))
                .andExpect(status().isOk())
                .andReturn();

        verify(requestService).getRequestByFieldText("hello");
    }






}
