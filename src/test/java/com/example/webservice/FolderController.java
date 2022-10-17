package com.example.webservice;

import com.example.webservice.entities.Folders;
import com.example.webservice.servises.FolderService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FolderController {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FolderService folderService;

    @Test
    void saveTag() throws Exception {
        File file = ResourceUtils.getFile("classpath:Folder.json");
        String folderStr = new String(Files.readAllBytes(file.toPath()));
        Folders folder = new Gson().fromJson(folderStr, Folders.class);

        when(folderService.buildAndSave(any())).thenReturn(folder);

        mockMvc.perform(post("/api/folder/add").
                        contentType(MediaType.APPLICATION_JSON).content(folderStr))
                .andExpect(status().isOk())
                .andReturn();

        verify(folderService).buildAndSave(folder);
    }

    @Test
    void getAll() throws Exception {
        when(folderService.getAllFolders()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/folder/getAll"))
                .andExpect(status().isOk())
                .andReturn();

        verify(folderService).getAllFolders();
    }

}
