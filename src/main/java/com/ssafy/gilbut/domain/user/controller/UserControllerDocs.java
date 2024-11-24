package com.ssafy.gilbut.domain.user.controller;

import com.ssafy.gilbut.domain.user.model.dto.UserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "회원 인증 컨트롤러", description = "로그인 로그아웃, 토큰처리등 회원의 인증관련 처리하는 클래스.")
public interface UserControllerDocs {
    @Operation(summary = "회원인증", description = "회원 정보를 담은 Token 을 반환한다.")
    ResponseEntity<?> getInfo(String header);

    @Operation(summary = "로그인", description = "아이디와 비밀번호를 이용하여 로그인 처리.")
    ResponseEntity<?> login(
            @Parameter(description = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) UserRequest.LoginDTO user
    );


    @Operation(summary = "로그아웃", description = "회원 정보를 담은 Token 을 제거한다.")
    ResponseEntity<?> logout(@Parameter(description = "로그아웃 할 회원의 아이디.", required = true) String userId);

    @Operation(summary = "Access Token 재발급", description = "만료된 access token 을 재발급 받는다.")
    ResponseEntity<?> refreshToken(String refreshToken) throws Exception;

    ResponseEntity<?> register(UserRequest.SignUpDTO user);

    ResponseEntity<?> updateUser(String accessToken, UserRequest.UpdateDTO user);

    ResponseEntity<?> closeAccount(
            String accessToken
    );


}
