package com.youwillwin.repository;

import com.youwillwin.model.Tag;
import com.youwillwin.model.TagType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByName(String name);

    Set<Tag> findByNameIn(Set<String> names);

    List<Tag> findByType(TagType type);
}
