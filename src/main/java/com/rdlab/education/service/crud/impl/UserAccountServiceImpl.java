package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.dto.profile.GetProfile;
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
import org.springframework.security.core.Authentication;
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
                    user.setName(userInfoDto.firstname());
                    user.setLastname(userInfoDto.lastname());
                    return user;
                })
                .map(userRepository::save)
                .map(users ->
                        new UserInfoOutputDto(users.getName(), users.getLastname(), users.getImage().getBase64Image()))
                .orElseThrow(() -> new ApiException("Ошибка при обновлении данных пользователя: " + name));
    }

    @Override
    public Base64Images updateImage(MultipartFile multipartFile) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(name)
                .map(user -> {
                    try {
                        StringBuffer strBuf = new StringBuffer();
                        strBuf.append("data:image/png;base64,");
                        strBuf.append(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
                        user.setImage(new Base64Images(strBuf.toString()));

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    return user;
                })
                .map(userRepository::save)
                .map(users -> users.getImage())
                .orElseThrow(() -> new ApiException("Не удалось сохранить Фото пользователя"));
    }

    @Override
    public GetProfile getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        String role = authentication.getAuthorities().stream().findFirst().orElseThrow().getAuthority();
        return userRepository.findByUsername(name)
                .map(users ->
                        new GetProfile(
                                users.getName(),
                                users.getLastname(),
                                users.getImage() == null ? "" : users.getImage().getBase64Image(),
                                role
                        ))
                .orElseThrow(() -> new ApiException("Похоже нет такого пользователя!"));

    }
}
