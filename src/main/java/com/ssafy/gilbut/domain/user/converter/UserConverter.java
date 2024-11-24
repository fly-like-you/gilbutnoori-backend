package com.ssafy.gilbut.domain.user.converter;

import com.ssafy.gilbut.domain.user.model.dto.UserResponse;
import com.ssafy.gilbut.domain.user.model.entity.Token;
import com.ssafy.gilbut.domain.user.model.entity.User;

public class UserConverter {
    public static UserResponse.DetailResultDTO toDetailResultDTO(User user) {
        return UserResponse.DetailResultDTO.builder()
                .id(user.getId())
                .loginId(user.getLoginId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .profileImg(user.getProfileImg())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
    }

    public static UserResponse.SimpleResultDTO toSimpleResultDTO(User user) {
        return UserResponse.SimpleResultDTO.builder()
                .id(user.getId())
                .loginId(user.getLoginId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }

    public static UserResponse.TokenDTO toTokenDTO(Token token) {
        return UserResponse.TokenDTO.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }

    public static UserResponse.TokenDTO toTokenDTO(String refreshToken) {
        return UserResponse.TokenDTO.builder()
                .refreshToken(refreshToken)
                .build();
    }
}
