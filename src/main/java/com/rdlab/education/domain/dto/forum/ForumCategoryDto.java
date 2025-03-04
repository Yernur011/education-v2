package com.rdlab.education.domain.dto.forum;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ForumCategoryDto {
    Long id;
    String categoryName;
    Long forumsCount;
}
