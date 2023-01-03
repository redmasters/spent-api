package io.red.spent.controllers;

import io.red.spent.services.ListTagService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/expense")
public class ListTagsController {
    private final ListTagService service;

    public ListTagsController(ListTagService service) {
        this.service = service;
    }

    @GetMapping("/tags")
    public List<String> listAllTags() {
        return service.allTags();
    }
}
