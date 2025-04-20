package com.rdlab.education.service.crud;

import com.rdlab.education.domain.entity.edu.Zoom;

import java.util.List;

public interface ZoomService {
    List<Zoom> getAll();
    Zoom getById(Long id);
    Zoom save(Zoom zoom);
    void delete(Long id);
}
