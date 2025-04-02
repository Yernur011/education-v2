package com.rdlab.education.controller.law;

import com.rdlab.education.domain.entity.edu.Base64Docs;
import com.rdlab.education.service.crud.LawService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.rdlab.education.utils.codes.ProductCode.LAWS_URI;
import static com.rdlab.education.utils.codes.ProductCode.V1_URI;


@RestController
@RequestMapping(V1_URI + LAWS_URI)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LawController {

    private final LawService lawService;

    @GetMapping
    public ResponseEntity<List<Base64Docs>> getAllLaws() {
        return ResponseEntity.ok(lawService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Base64Docs> getLawById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(lawService.getById(id));
    }


    @PostMapping("/update-doc")
    public ResponseEntity<Base64Docs> updatePhoto(@RequestParam MultipartFile doc) {
        return ResponseEntity.ok(lawService.updateDoc(doc));
    }
}
