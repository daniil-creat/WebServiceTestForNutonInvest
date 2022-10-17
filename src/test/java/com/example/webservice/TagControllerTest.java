package com.example.webservice;

import com.example.webservice.entities.Tags;
import com.example.webservice.servises.TagsService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TagControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TagsService tagsService;

    @Test
    void saveTag() throws Exception {
        File file = ResourceUtils.getFile("classpath:Tag.json");
        String tagStr = new String(Files.readAllBytes(file.toPath()));
        Tags tag = new Gson().fromJson(tagStr, Tags.class);

        when(tagsService.buildAndSave(any())).thenReturn(tag);

        mockMvc.perform(post("/api/tag/add").
                        contentType(MediaType.APPLICATION_JSON).content(tagStr))
                .andExpect(status().isOk())
                .andReturn();

        verify(tagsService).buildAndSave(tag);
    }

    @Test
    void getAll() throws Exception {
        when(tagsService.getAllTags()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/tag/getAll"))
                .andExpect(status().isOk())
                .andReturn();

        verify(tagsService).getAllTags();
    }
}
