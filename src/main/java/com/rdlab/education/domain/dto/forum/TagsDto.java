package com.rdlab.education.domain.dto.forum;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TagsDto {
    Long id;
    String tagName;
    Long tagCount;
}
