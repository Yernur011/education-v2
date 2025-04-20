package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.entity.edu.Zoom;
import com.rdlab.education.domain.repository.edu.ZoomRepository;
import com.rdlab.education.service.crud.ZoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ZoomServiceImpl implements ZoomService {
    private final ZoomRepository zoomRepository;

    public List<Zoom> getAll() {
        return zoomRepository.findAll();
    }

    public Zoom getById(Long id) {
        return zoomRepository.findById(id).orElseThrow();
    }

    public Zoom save(Zoom zoom) {
        return zoomRepository.save(zoom);
    }

    public void delete(Long id) {
        zoomRepository.deleteById(id);
    }
}
