package com.rdlab.education.service.crud;

import com.rdlab.education.domain.dto.page.PageableDto;
import com.rdlab.education.domain.entity.edu.Material;

import java.util.List;

public interface MaterialService {
    List<Material> getAll();
    PageableDto<Material> findAll(int page, int size);
    Material getById(Long id);
    Material save(Material material);
    void delete(Long id);
}
