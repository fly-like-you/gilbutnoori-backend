package com.ssafy.gilbut.domain.user.controller;

import com.ssafy.gilbut.advice.ApiResponse;
import com.ssafy.gilbut.domain.user.model.dto.request.UserInfoResponseDTO;
import com.ssafy.gilbut.domain.user.model.dto.request.UserLoginRequestDTO;
import com.ssafy.gilbut.domain.user.model.dto.request.UserSignUpRequestDTO;
import com.ssafy.gilbut.domain.user.model.dto.request.UserUpdateRequestDTO;
import com.ssafy.gilbut.domain.user.model.dto.response.UserTokenResponseDTO;
import com.ssafy.gilbut.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController implements UserControllerDocs {

    private final UserService userService;

    @Override
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserSignUpRequestDTO user) {
        log.debug("register user -> {}", user);
        userService.register(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> withdraw(
            @PathVariable("userId") String loginId,
            @RequestHeader("Authorization") String userToken
    ) {
        log.debug("accessToken -> {}", userToken);
        userService.withdraw(loginId, userToken);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping
    public ResponseEntity<?> updateUser(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody UserUpdateRequestDTO user) {
        log.debug("update user -> {}", user);
        userService.updateUser(accessToken, user);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequestDTO user) {
        // 로그인 확인하기
        UserTokenResponseDTO userToken = userService.login(user);
        log.debug("login user : {}", user);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.onSuccess(userToken));
    }

    @Override
    @GetMapping("/info/{userId}")
    public ResponseEntity<?> getInfo(
            @PathVariable("userId") String loginId,
            @RequestHeader("Authorization") String header
    ) {

        log.debug("userId : {}, header : {} ", loginId, header);
        UserInfoResponseDTO info = userService.userInfo(loginId, header);

        return ResponseEntity.ok(ApiResponse.onSuccess(info));
    }

    @Override
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        userService.deleteRefreshToken(token);
        return ResponseEntity.noContent().build();
    }

    /**
     * 토큰 정보로 Access Token 재발급
     */
    @Override
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(
            @RequestHeader("Authorization") String accessToken,
            @RequestHeader("RefreshToken") String refreshToken) {
        UserTokenResponseDTO userToken = userService.refreshAccessToken(accessToken, refreshToken);
        log.debug("token -> {}", refreshToken);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.onSuccess(userToken));
    }


}
