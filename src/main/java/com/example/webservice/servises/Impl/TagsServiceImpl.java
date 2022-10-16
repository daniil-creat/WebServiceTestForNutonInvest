package com.example.webservice.servises.Impl;

import com.example.webservice.entities.Tags;
import com.example.webservice.repositories.TagsRepository;
import com.example.webservice.servises.NextSequenceService;
import com.example.webservice.servises.TagsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.webservice.entities.Tags.TAG_SEQUENCE_NAME;

@Service
@RequiredArgsConstructor
@Log4j2
public class TagsServiceImpl implements TagsService {

    private final TagsRepository tagsRepository;
    private final NextSequenceService nextSequenceService;

    @Override
    public Tags buildAndSave(Tags tag) {
        log.info("Start tag service, input tag {}", tag);
        Tags tagForDb = Tags.builder()
                .id(nextSequenceService.getNextSequence(TAG_SEQUENCE_NAME))
                .tagName(tag.getTagName())
                .build();
        Tags savedTag = tagsRepository.save(tagForDb);
        log.info("End tag service, output:{}", savedTag);
        return savedTag;
    }

    @Override
    public List<Tags> getAllTags() {
        log.info("Start tag service, get  all tags");
        List<Tags> tags = tagsRepository.findAll();
        log.info("End tag service, output:{}", tags);
        return tags;
    }
}
