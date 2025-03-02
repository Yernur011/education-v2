package com.rdlab.education.service.crud;

import com.rdlab.education.domain.dto.user.info.UserInfoDto;
import com.rdlab.education.domain.dto.user.info.UserInfoOutputDto;
import com.rdlab.education.domain.entity.image.Base64Images;
import org.springframework.web.multipart.MultipartFile;

public interface AccountService {
    UserInfoOutputDto updateUserData(UserInfoDto userInfoDto);

    Base64Images updateImage(MultipartFile multipartFile);

}
