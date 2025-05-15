package com.legalDigital.education.controller.account;

import com.legalDigital.education.domain.dto.profile.GetProfile;
import com.legalDigital.education.domain.dto.user.info.UserInfoDto;
import com.legalDigital.education.domain.dto.user.info.UserInfoOutputDto;
import com.legalDigital.education.domain.entity.auth.ChangePasswordRequest;
import com.legalDigital.education.domain.entity.image.Base64Images;
import com.legalDigital.education.service.auth.ChangePassword;
import com.legalDigital.education.service.crud.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.legalDigital.education.utils.codes.ProductCode.ACCOUNT_URI;
import static com.legalDigital.education.utils.codes.ProductCode.ACTIVE_URI;
import static com.legalDigital.education.utils.codes.ProductCode.ANSWERS_URI;
import static com.legalDigital.education.utils.codes.ProductCode.COURSE_URI;
import static com.legalDigital.education.utils.codes.ProductCode.FINISHED_URI;
import static com.legalDigital.education.utils.codes.ProductCode.QUESTIONS_URI;
import static com.legalDigital.education.utils.codes.ProductCode.TESTS_URI;
import static com.legalDigital.education.utils.codes.ProductCode.UPDATE_URI;
import static com.legalDigital.education.utils.codes.ProductCode.V1_URI;


@RestController
@RequiredArgsConstructor
@RequestMapping(V1_URI + ACCOUNT_URI)
public class AccountController {
    private final AccountService accountService;
    private final ChangePassword changePassword;
    @GetMapping
    public ResponseEntity<GetProfile> getProfile(){
        return ResponseEntity.ok(accountService.getProfile());
    }

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
    public ResponseEntity<UserInfoOutputDto> saveUserInfo(@RequestBody UserInfoDto userInfoDto) {
        return ResponseEntity.ok(accountService.updateUserData(userInfoDto));
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> update(@RequestBody ChangePasswordRequest request) {
        changePassword.changePassword(request);

        return ResponseEntity.ok().build();
    }
}
