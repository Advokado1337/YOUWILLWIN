package com.youwillwin.controller;

import com.youwillwin.model.Tag;
import com.youwillwin.model.TagType;
import com.youwillwin.repository.TagRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagRepository tagRepository;

    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @GetMapping
    public List<Tag> list(@RequestParam(required = false) TagType type) {
        if (type != null) {
            return tagRepository.findByType(type);
        }
        return tagRepository.findAll();
    }
}
