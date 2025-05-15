package com.legalDigital.education.service.crud;

import com.legalDigital.education.domain.dto.SearchDto;

import java.util.List;

public interface SearchService {
    List<SearchDto> search(String query);
}
