package com.ssafy.gilbut.domain.user.service;

import com.ssafy.gilbut.domain.user.model.dto.request.UserLoginRequestDTO;
import com.ssafy.gilbut.domain.user.model.dto.request.UserSignUpRequestDTO;
import com.ssafy.gilbut.domain.user.model.dto.response.UserResponseDTO;
import com.ssafy.gilbut.domain.user.model.entity.User;

public interface UserService {

    User login(UserLoginRequestDTO memberDto);
    User userInfo(String loginId);
    void saveRefreshToken(String loginId, String refreshToken);
    String getRefreshToken(String loginId);
    void deleteRefreshToken(String loginId);
    void withdraw(String userToken);
    UserResponseDTO register(UserSignUpRequestDTO user);
}
