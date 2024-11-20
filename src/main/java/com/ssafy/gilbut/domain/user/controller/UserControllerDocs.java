package com.ssafy.gilbut.domain.user.controller;

import com.ssafy.gilbut.domain.user.model.dto.request.UserLoginRequestDTO;
import com.ssafy.gilbut.domain.user.model.dto.request.UserSignUpRequestDTO;
import com.ssafy.gilbut.domain.user.model.dto.request.UserUpdateRequestDTO;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "회원 인증 컨트롤러", description = "로그인 로그아웃, 토큰처리등 회원의 인증관련 처리하는 클래스.")
interface UserControllerDocs {
    // TODO: 로그인 (POST) return 토큰
    @Operation(summary = "로그인", description = "아이디와 비밀번호를 이용하여 로그인 처리.")
    ResponseEntity<?> login(
            @Parameter(description = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) UserLoginRequestDTO user
    );

    @Hidden
    @Operation(summary = "회원인증", description = "회원 정보를 담은 Token 을 반환한다.")
    ResponseEntity<?> getInfo(String header);

    // TODO: 로그아웃 (POST)
    @Operation(summary = "로그아웃", description = "회원 정보를 담은 Token 을 제거한다.")
    ResponseEntity<?> logout(@Parameter(description = "로그아웃 할 회원의 아이디.", required = true) String userId);

    @Operation(summary = "Access Token 재발급", description = "만료된 access token 을 재발급 받는다.")
    ResponseEntity<?> refreshToken(String refreshToken) throws Exception;

    // TODO: 회원가입 (POST)
    ResponseEntity<?> register(UserSignUpRequestDTO user);

    // TODO: 회원 탈퇴 (DELETE)
    ResponseEntity<?> withdraw(
            @Parameter(description = "인증 토큰 값", required = true) String userToken
    );

    // TODO: 정보 수정 (PUT)
    ResponseEntity<?> updateUser(String accessToken, UserUpdateRequestDTO user);

}
