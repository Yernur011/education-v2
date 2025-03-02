package com.rdlab.education.controller.account;

import com.rdlab.education.domain.dto.user.info.UserInfoDto;
import com.rdlab.education.domain.dto.user.info.UserInfoOutputDto;
import com.rdlab.education.domain.entity.image.Base64Images;
import com.rdlab.education.service.crud.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.rdlab.education.utils.codes.ProductCode.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(V1_URI + ACCOUNT_URI)
public class AccountController {
    private final AccountService accountService;

    @GetMapping(COURSE_URI + ACTIVE_URI)
    public ResponseEntity<List<Object>> getCourses() {
        return ResponseEntity.ok(List.of());
    }

    @GetMapping(COURSE_URI + FINISHED_URI)
    public ResponseEntity<List<Object>> getCoursesFinished() {
        return ResponseEntity.ok(List.of());
    }

    @GetMapping(TESTS_URI + ACTIVE_URI)
    public ResponseEntity<List<Object>> getTests() {
        return ResponseEntity.ok(List.of());
    }

    @GetMapping(TESTS_URI + FINISHED_URI)
    public ResponseEntity<List<Object>> getTestFinished() {
        return ResponseEntity.ok(List.of());
    }

    @GetMapping(QUESTIONS_URI)
    public ResponseEntity<List<Object>> getQuestions() {
        return ResponseEntity.ok(List.of());
    }

    @GetMapping(ANSWERS_URI)
    public ResponseEntity<List<Object>> getAnswers() {
        return ResponseEntity.ok(List.of());
    }

    @PutMapping(UPDATE_URI)
    public ResponseEntity<Void> updateUser() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update-image")
    public ResponseEntity<Base64Images> updatePhoto(@RequestParam MultipartFile image) {
        return ResponseEntity.ok(accountService.updateImage(image));
    }

    @PostMapping("/update-user-info")
    public ResponseEntity<UserInfoOutputDto> saveUserInfo(@RequestBody UserInfoDto userInfoDto
                                                          ) {
        return ResponseEntity.ok(accountService.updateUserData(userInfoDto));
    }
}
