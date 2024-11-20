package com.ssafy.gilbut.domain.user.service;

import com.ssafy.gilbut.domain.user.model.dto.request.UserInfoResponseDTO;
import com.ssafy.gilbut.domain.user.model.dto.request.UserLoginRequestDTO;
import com.ssafy.gilbut.domain.user.model.dto.request.UserSignUpRequestDTO;
import com.ssafy.gilbut.domain.user.model.dto.request.UserUpdateRequestDTO;
import com.ssafy.gilbut.domain.user.model.dto.response.UserResponseDTO;
import com.ssafy.gilbut.domain.user.model.dto.response.UserTokenResponseDTO;

public interface UserService {

    UserTokenResponseDTO login(UserLoginRequestDTO memberDto);

    UserInfoResponseDTO userInfo(String loginId, String header);

    void deleteRefreshToken(String loginId);

    void withdraw(String loginId, String userToken);

    UserResponseDTO register(UserSignUpRequestDTO user);

    UserTokenResponseDTO refreshAccessToken(String accessToken, String refreshToken);

    void updateUser(String accessToken, UserUpdateRequestDTO user);
}
