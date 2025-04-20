package com.rdlab.education.controller.material;

import com.rdlab.education.domain.dto.material.ZoomDto;
import com.rdlab.education.domain.mapper.ZoomMapper;
import com.rdlab.education.service.crud.ZoomService;
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
@RequestMapping("/api/zooms")
@RequiredArgsConstructor
public class ZoomController {

    private final ZoomService zoomService;
    @Qualifier("zoomMapper")
    private final ZoomMapper zoomMapper;

    @GetMapping
    public ResponseEntity<List<ZoomDto>> findAll() {
        return ResponseEntity.ok(zoomService.getAll().stream().map(zoomMapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZoomDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(zoomMapper.toDto(zoomService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ZoomDto> create(@RequestBody ZoomDto dto) {
        return ResponseEntity.ok(
                zoomMapper.toDto(
                        zoomService.save(zoomMapper.toEntity(dto))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZoomDto> update(@PathVariable Long id, @RequestBody ZoomDto dto) {
        return ResponseEntity.ok(
                zoomMapper.toDto(
                        zoomService.save(zoomMapper.toEntity(dto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        zoomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
