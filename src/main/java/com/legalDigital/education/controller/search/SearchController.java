package com.legalDigital.education.controller.search;

import com.legalDigital.education.domain.dto.SearchDto;
import com.legalDigital.education.service.crud.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.legalDigital.education.utils.codes.ProductCode.SEARCH_URI;
import static com.legalDigital.education.utils.codes.ProductCode.V1_URI;

@RestController
@RequestMapping(V1_URI + SEARCH_URI)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<List<SearchDto>> search(@RequestParam String query) {
        return ResponseEntity.ok(searchService.search(query));
    }
}
