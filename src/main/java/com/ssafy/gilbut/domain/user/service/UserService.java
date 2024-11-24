package com.ssafy.gilbut.domain.user.service;

import com.ssafy.gilbut.domain.user.model.dto.UserRequest;
import com.ssafy.gilbut.domain.user.model.dto.UserResponse;

public interface UserService {

    UserResponse.DetailResultDTO userInfo(String accessToken);

    UserResponse.TokenDTO login(UserRequest.LoginDTO memberDto);

    UserResponse.TokenDTO refreshAccessToken(String refreshToken);

    void deleteRefreshToken(String loginId);

    UserResponse.SimpleResultDTO register(UserRequest.SignUpDTO user);

    void updateUser(String accessToken, UserRequest.UpdateDTO user);

    void withdraw(String accessToken);
}
