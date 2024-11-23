package com.ssafy.gilbut.domain.user.controller;

import com.ssafy.gilbut.advice.ApiResponse;
import com.ssafy.gilbut.domain.user.model.dto.UserRequest;
import com.ssafy.gilbut.domain.user.model.dto.UserResponse;
import com.ssafy.gilbut.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController implements UserControllerDocs {

    private final UserService userService;

    @Override
    @GetMapping("/info")
    public ResponseEntity<?> getInfo(
            @RequestHeader("Authorization") String header
    ) {

        log.debug("header : {} ", header);
        UserResponse.DetailResultDTO info = userService.userInfo(header);

        return ResponseEntity.ok(ApiResponse.onSuccess(info));
    }


    @Override
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserRequest.LoginDTO user) {
        // 로그인 확인하기
        UserResponse.TokenDTO userToken = userService.login(user);
        log.debug("login user : {}", user);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.onSuccess(userToken));
    }


    @Override
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String accessToken) {
        userService.deleteRefreshToken(accessToken);
        return ResponseEntity.noContent().build();
    }

    /**
     * 토큰 정보로 Access Token 재발급
     */
    @Override
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(
            @RequestHeader("RefreshToken") String refreshToken
    ) {
        UserResponse.TokenDTO userToken = userService.refreshAccessToken(refreshToken);
        log.debug("token -> {}", refreshToken);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.onSuccess(userToken));
    }

    @Override
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody @Valid UserRequest.SignUpDTO user) {
        log.debug("register user -> {}", user);
        userService.register(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @Override
    @PutMapping
    public ResponseEntity<?> updateUser(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody @Valid UserRequest.UpdateDTO user) {
        log.debug("update user -> {}", user);
        userService.updateUser(accessToken, user);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<?> closeAccount(
            @RequestHeader("Authorization") String accessToken
    ) {
        log.debug("accessToken -> {}", accessToken);
        userService.withdraw(accessToken);

        return ResponseEntity.noContent().build();
    }

}
