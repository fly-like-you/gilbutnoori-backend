package com.ssafy.gilbut.domain.user.controller;

import com.ssafy.gilbut.domain.user.model.dto.request.UserLoginRequestDTO;
import com.ssafy.gilbut.domain.user.model.dto.request.UserSignUpRequestDTO;
import com.ssafy.gilbut.domain.user.model.dto.request.UserUpdateRequestDTO;
import com.ssafy.gilbut.domain.user.model.entity.User;
import com.ssafy.gilbut.domain.user.service.UserService;
import com.ssafy.gilbut.util.JWTUtil;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController implements UserControllerDocs {

    private final UserService userService;
    private final JWTUtil jwtUtil;

    @Override
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserSignUpRequestDTO user) {
        log.debug("register user: {}", user);
        userService.register(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @DeleteMapping
    public ResponseEntity<?> withdraw(@RequestHeader("Authorization") String userToken) {
        log.debug("token: {}", userToken);
        userService.withdraw(userToken);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> updateUser(UserUpdateRequestDTO user) {
        return null;
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequestDTO user) {
        log.debug("login user : {}", user);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        User loginUser = userService.login(user);
        if (loginUser != null) {
            String accessToken = jwtUtil.createAccessToken(loginUser.getLoginId());
            String refreshToken = jwtUtil.createRefreshToken(loginUser.getLoginId());
            log.debug("access token : {}", accessToken);
            log.debug("refresh token : {}", refreshToken);

            // 발급받은 refresh token 을 DB에 저장.
            userService.saveRefreshToken(loginUser.getLoginId(), refreshToken);

            // JSON 으로 token 전달.
            resultMap.put("access-token", accessToken);
            resultMap.put("refresh-token", refreshToken);

            status = HttpStatus.CREATED;
        } else {
            resultMap.put("message", "아이디 또는 패스워드를 확인해 주세요.");
            status = HttpStatus.UNAUTHORIZED;
        }

        return new ResponseEntity<>(resultMap, status);
    }

    @Override
    @GetMapping("/info/{userId}")
    public ResponseEntity<?> getInfo(@PathVariable("userId") @Parameter(description = "인증할 회원의 아이디.", required = true) String userId, @RequestHeader("Authorization") String header) {
        log.debug("userId : {}, header : {} ", userId, header);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;
        if (!jwtUtil.checkToken(header)) {
            log.info("사용 가능한 토큰!!!");
            try {
//				로그인 사용자 정보.
                User user = userService.userInfo(userId);
                resultMap.put("userInfo", user);
                status = HttpStatus.OK;
            } catch (Exception e) {
                log.error("정보조회 실패 : {}", e);
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            log.error("사용 불가능 토큰!!!");
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @Override
    @GetMapping("/logout/{userId}")
    public ResponseEntity<?> logout(@PathVariable("userId") String userId) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            userService.deleteRefreshToken(userId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("로그아웃 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @Override
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody UserLoginRequestDTO user, @RequestHeader("refreshToken") String token) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
//		String token = request.getHeader("refreshToken");
        log.debug("token : {}, memberDto : {}", token, user);
        if (jwtUtil.checkToken(token)) {
            if (token.equals(userService.getRefreshToken(user.getLoginId()))) {
                String accessToken = jwtUtil.createAccessToken(user.getLoginId());
                log.debug("token : {}", accessToken);
                log.debug("정상적으로 access token 재발급!!!");
                resultMap.put("access-token", accessToken);
                status = HttpStatus.CREATED;
            }
        } else {
            log.debug("refresh token 도 사용 불가!!!!!!!");
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<>(resultMap, status);
    }


}
