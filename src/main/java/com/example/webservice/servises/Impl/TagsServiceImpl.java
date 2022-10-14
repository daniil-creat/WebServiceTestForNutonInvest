package com.example.webservice.servises.Impl;

import com.example.webservice.entities.Tags;
import com.example.webservice.repositories.TagsRepository;
import com.example.webservice.servises.NextSequenceService;
import com.example.webservice.servises.TagsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.webservice.entities.Tags.*;

@Service
@RequiredArgsConstructor
public class TagsServiceImpl implements TagsService {

    private final TagsRepository tagsRepository;
    private final NextSequenceService nextSequenceService;

    @Override
    public Tags buildAndSave(Tags tag) {
        Tags tagForDb = builder()
                .id(nextSequenceService.getNextSequence(TAG_SEQUENCE_NAME))
                .tagName(tag.getTagName())
                .build();
        return tagsRepository.save(tagForDb);
    }
}
