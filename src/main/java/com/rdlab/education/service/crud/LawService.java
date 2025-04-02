package com.rdlab.education.service.crud;

import com.rdlab.education.domain.entity.edu.Base64Docs;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LawService {
    Base64Docs updateDoc(MultipartFile multipartFile);

    List<Base64Docs> getAll();

    Base64Docs getById(Long id);
}
