package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.dto.user.info.UserInfoDto;
import com.rdlab.education.domain.dto.user.info.UserInfoOutputDto;
import com.rdlab.education.domain.entity.auth.Users;
import com.rdlab.education.domain.entity.edu.UserDetails;
import com.rdlab.education.domain.entity.image.Base64Images;
import com.rdlab.education.domain.exceptions.ApiException;
import com.rdlab.education.domain.repository.UserDetailsRepository;
import com.rdlab.education.domain.repository.UserRepository;
import com.rdlab.education.service.crud.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserAccountServiceImpl implements AccountService {
    private final UserDetailsRepository userDetailsRepository;
    private final UserRepository userRepository;

    @Override
    public UserInfoOutputDto updateUserData(UserInfoDto userInfoDto) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByUsername(name)
                .map(user -> {
                    UserDetails userDetails = user.getUserDetails();
                    user.setName(userInfoDto.firstname());
                    userDetails.setLastname(userInfoDto.lastname());
                    user.setUserDetails(userDetails);
                    return user;
                })
                .map(userRepository::save)
                .map(users -> new UserInfoOutputDto(users.getName(), users.getUserDetails().getLastname(), users.getUserDetails().getImages().getBase64Image()))
                .orElseThrow(() -> new ApiException("Ошибка при обновлении данных пользователя: " + name));
    }

    @Override
    public Base64Images updateImage(MultipartFile multipartFile) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(name)
                .map(user -> {
                    UserDetails userDetails = user.getUserDetails();
                    try {
                        StringBuffer strBuf = new StringBuffer();
                        strBuf.append("data:image/png;base64,");
                        strBuf.append(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
                        userDetails.setImages(new Base64Images(strBuf.toString()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    return user;
                })
                .map(userRepository::save)
                .map(users -> users.getUserDetails().getImages())
                .orElseThrow(() -> new ApiException("Не удалось сохранить Фото пользователя"));
    }

}
