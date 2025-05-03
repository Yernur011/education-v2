package com.rdlab.education.service.crud;

import com.rdlab.education.domain.dto.forum.TagsDto;

import java.util.List;

public interface TagService {
    List<TagsDto> findAll();
}
