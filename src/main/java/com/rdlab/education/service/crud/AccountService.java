package com.rdlab.education.service.crud;

import com.rdlab.education.domain.dto.profile.GetProfile;
import com.rdlab.education.domain.dto.user.info.UserInfoDto;
import com.rdlab.education.domain.dto.user.info.UserInfoOutputDto;
import com.rdlab.education.domain.entity.auth.Users;
import com.rdlab.education.domain.entity.edu.Notification;
import com.rdlab.education.domain.entity.image.Base64Images;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AccountService {
    UserInfoOutputDto updateUserData(UserInfoDto userInfoDto);

    Base64Images updateImage(MultipartFile multipartFile);

    GetProfile getProfile();

    Page<Users> getAllUsers(int page, int size);

    List<Notification> notifyAllUsersWithTopics(Long topicId, Notification notification);

    List<Notification> notifyAllUsers(Notification notification);

}
