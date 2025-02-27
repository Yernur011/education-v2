package com.rdlab.education.domain.dto.page;

import lombok.Data;

import java.util.List;

@Data
public class PageableDto<T>  {
    int totalPages;
    List<T> content;
}
