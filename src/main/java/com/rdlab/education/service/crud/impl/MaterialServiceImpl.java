package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.dto.page.PageableDto;
import com.rdlab.education.domain.entity.edu.Material;
import com.rdlab.education.domain.repository.edu.MaterialRepository;
import com.rdlab.education.service.crud.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;


    public List<Material> getAll() {
        return materialRepository.findAll();
    }

    @Override
    public PageableDto<Material> findAll(int page, int size) {
        Page<Material> materials = materialRepository.findAll(PageRequest.of(page, size));
        return new PageableDto<>(materials.getTotalPages(), materials.getContent());
    }

    public Material getById(Long id) {
        return materialRepository.findById(id).orElseThrow();
    }

    public Material save(Material material) {
        return materialRepository.save(material);
    }

    public void delete(Long id) {
        materialRepository.deleteById(id);
    }
}
