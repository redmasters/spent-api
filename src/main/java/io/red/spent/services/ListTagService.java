package io.red.spent.services;

import io.red.spent.models.dao.TagDAO;
import io.red.spent.repositories.query.TagCustommRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListTagService {
    private final TagCustommRepository repository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ListTagService.class);

    public ListTagService(TagCustommRepository repository) {
        this.repository = repository;
    }

    public List<String> allTags(){
        final var tags = repository.allTags();
        LOGGER.info("Found {} tags", tags.size());
        return tags.stream()
                .map(TagDAO::getTagDescription)
                .toList();
    }
}
