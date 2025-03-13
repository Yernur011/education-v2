package com.rdlab.education.domain.dto.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableDto<T>  {
    int totalPages;
    List<T> content;
}
