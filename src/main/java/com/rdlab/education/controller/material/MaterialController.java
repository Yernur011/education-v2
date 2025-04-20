package com.rdlab.education.controller.material;

import com.rdlab.education.domain.dto.material.MaterialDto;
import com.rdlab.education.domain.mapper.MaterialMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
@RequiredArgsConstructor
public class MaterialController {

    private final com.rdlab.education.service.crud.MaterialService materialService;
    @Qualifier("materialMapper")
    private final MaterialMapper mapper;

    @GetMapping
    public ResponseEntity<List<MaterialDto>> findAll() {
        return ResponseEntity.ok(
                materialService.getAll()
                        .stream()
                        .map(mapper::toDto)
                        .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDto(materialService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<MaterialDto> create(@RequestBody MaterialDto dto) {
        return ResponseEntity.ok(
                mapper.toDto(
                        materialService.save(mapper.toEntity(dto))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialDto> update(@PathVariable Long id, @RequestBody MaterialDto dto) {
        var entity = mapper.toEntity(dto.toBuilder().id(id).build());
        return ResponseEntity.ok(mapper.toDto(materialService.save(entity)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        materialService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

