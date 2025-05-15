package com.legalDigital.education.service.crud;

import com.legalDigital.education.domain.dto.profile.GetProfile;
import com.legalDigital.education.domain.dto.user.info.UserInfoDto;
import com.legalDigital.education.domain.dto.user.info.UserInfoOutputDto;
import com.legalDigital.education.domain.entity.image.Base64Images;
import org.springframework.web.multipart.MultipartFile;

public interface AccountService {
    UserInfoOutputDto updateUserData(UserInfoDto userInfoDto);

    Base64Images updateImage(MultipartFile multipartFile);

    GetProfile getProfile();

}
