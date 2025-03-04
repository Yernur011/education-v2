package com.rdlab.education.service.crud;

import com.rdlab.education.domain.dto.SearchDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SearchService {
    List<SearchDto> search(String query);
}
