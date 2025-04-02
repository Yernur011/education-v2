package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.entity.edu.Base64Docs;
import com.rdlab.education.domain.repository.edu.Base64DocsRepository;
import com.rdlab.education.service.crud.LawService;
import jakarta.activation.MimetypesFileTypeMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;


@Service
@RequiredArgsConstructor
public class LawServiceImpl implements LawService {

    private final Base64DocsRepository base64DocsRepository;

    @Override
    public Base64Docs getById(Long id) {
        return base64DocsRepository.findById(id)
                .map(ent -> new Base64Docs(ent.getId(), ent.getFilename(), ent.getBase64Docs()))
                .orElse(null);
    }

    @Override
    public List<Base64Docs> getAll() {
        return base64DocsRepository.findAll()
                .stream()
                .map(ent -> new Base64Docs(ent.getId(), ent.getFilename(), null))
                .toList();
    }

    @Override
    public Base64Docs updateDoc(MultipartFile multipartFile) {
        Base64Docs base64Docs = new Base64Docs();
        try {
            StringBuffer strBuf = new StringBuffer();
            strBuf.append("data:").append(getContentType(multipartFile)).append(";base64,")
                    .append(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
            base64Docs.setBase64Docs(strBuf.toString());
            base64Docs.setFilename(multipartFile.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return base64DocsRepository.save(base64Docs);

    }

    private static String getContentType(MultipartFile multipartFile) {
        String contentType = multipartFile.getContentType();
        if (contentType == null) {
            contentType = new MimetypesFileTypeMap().getContentType(multipartFile.getOriginalFilename());
        }
        return contentType;
    }
}
