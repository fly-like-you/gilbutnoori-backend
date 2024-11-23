package com.ssafy.gilbut.domain.user.service;

import com.ssafy.gilbut.advice.status.ErrorStatus;
import com.ssafy.gilbut.domain.user.converter.UserConverter;
import com.ssafy.gilbut.domain.user.mapper.UserMapper;
import com.ssafy.gilbut.domain.user.model.dto.UserRequest;
import com.ssafy.gilbut.domain.user.model.dto.UserResponse;
import com.ssafy.gilbut.domain.user.model.entity.Token;
import com.ssafy.gilbut.domain.user.model.entity.User;
import com.ssafy.gilbut.exception.handler.GeneralExceptionHandler;
import com.ssafy.gilbut.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JWTUtil jwtUtil;

    @Override
    public UserResponse.DetailResultDTO userInfo(String accessToken) {
        // 토큰 값과 파라미터의 아이디가 일치하는지 검사
        Long userId = jwtUtil.getUserId(accessToken);

        // 유저 데이터 가져오기
        User user = getActivatedUserByUserId(userId);

        return UserConverter.toDetailResultDTO(user);
    }

    @Override
    public UserResponse.TokenDTO login(UserRequest.LoginDTO memberDto) {
        // 로그인 아이디 확인
        User loginUser = getActivatedUserByLoginId(memberDto.getLoginId());
        String password = loginUser.getPassword();

        // 비밀번호 일치 확인
        if (!passwordEncoder.matches(memberDto.getPassword(), password)) {
            throw new GeneralExceptionHandler(ErrorStatus.USER_LOGIN_FAILED);
        }

        // 토큰 생성
        Token jwtToken = jwtUtil.createJwtToken(loginUser);

        // Refresh Token만 DB에 저장
        saveRefreshToken(loginUser.getId(), jwtToken.getRefreshToken());

        return UserConverter.toTokenDTO(jwtToken);

    }


    @Override
    public UserResponse.TokenDTO refreshAccessToken(String refreshToken) {
        // RefreshToken 유효성 검사하기
        jwtUtil.checkTokenValidation(refreshToken);

        // 토큰에서 ID 찾기
        User user = getActivatedUserByUserId(jwtUtil.getUserId(refreshToken));

        // ID로 RefreshToken 가져오기
        String authRefreshToken = getRefreshToken(user.getId());
        if (!authRefreshToken.equals(refreshToken)) {
            throw new GeneralExceptionHandler(ErrorStatus.USER_BAD_REQUEST);
        }

        // RefreshToken 저장하기
        Token token = jwtUtil.createAccessToken(user);
        userMapper.saveRefreshToken(user.getId(), token.getRefreshToken());
        log.debug("정상적으로 access token 재발급!!!");

        return UserConverter.toTokenDTO(token);
    }

    // 로그아웃 logout
    @Override
    public void deleteRefreshToken(String accessToken) {
        // 토큰으로부터 사용자의 아이디 찾아오기
        Long userId = jwtUtil.getUserId(accessToken);
        User user = getActivatedUserByUserId(userId);
        log.debug("User -> {}", user);

        // DB에서 token값 삭제하기 (로그아웃)
        userMapper.deleteRefreshToken(userId);
    }


    @Override
    public UserResponse.SimpleResultDTO register(UserRequest.SignUpDTO signUpDTO) {
        // 1개 이상 있으면 예외 던지기
        if (isDuplicateLoginId(signUpDTO.getId())) {
            throw new GeneralExceptionHandler(ErrorStatus.USER_DUPLICATED_LOGIN_ID);
        }
        if (isDuplicateNickname(signUpDTO.getNickname())) {
            throw new GeneralExceptionHandler(ErrorStatus.USER_DUPLICATED_NICKNAME);
        }

        signUpDTO.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        userMapper.register(signUpDTO);

        User user = getActivatedUserByUserId(signUpDTO.getId());
        log.debug("register done, user -> {}", user);

        return UserConverter.toSimpleResultDTO(user);
    }

    @Override
    public void updateUser(String accessToken, UserRequest.UpdateDTO requestUser) {
        // 토큰으로부터 사용자의 정보 가져오기
        User user = getActivatedUserByUserId(jwtUtil.getUserId(accessToken));

        // 비밀번호 일치 확인
        if (!passwordEncoder.matches(requestUser.getOldPassword(), user.getPassword())) {
            throw new GeneralExceptionHandler(ErrorStatus.USER_LOGIN_FAILED);
        }
        // 로그인 중복 확인
        if (isDuplicateNickname(requestUser.getNickname())) {
            throw new GeneralExceptionHandler(ErrorStatus.USER_DUPLICATED_NICKNAME);
        }

        requestUser.setNewPassword(passwordEncoder.encode(requestUser.getNewPassword()));
        userMapper.updateUser(user.getId(), requestUser);
    }


    @Override
    public void withdraw(String accessToken) {
        Long userId = jwtUtil.getUserId(accessToken);
        User user = getActivatedUserByUserId(userId);

        userMapper.inactivateUser(user);
    }

    /**
     * 상태가 ACTIVATE인 사용자를 가져오는 코드 유저 데이터를 Mapper를 가져올 때는 아래 메서드만 사용해주세요
     *
     * @param userId
     * @return 상태가 ACTIVATE인 유저 엔티티
     */
    private User getActivatedUserByUserId(Long userId) {
        return userMapper.findUserByUserId(userId).orElseThrow(
                () -> new GeneralExceptionHandler(ErrorStatus.USER_NOT_FOUND)
        );
    }

    private User getActivatedUserByLoginId(String loginId) {
        return userMapper.findUserByLoginId(loginId).orElseThrow(
                () -> new GeneralExceptionHandler(ErrorStatus.USER_NOT_FOUND)
        );
    }

    private String getRefreshToken(Long userId) {
        return userMapper.getRefreshToken(userId);
    }

    private void saveRefreshToken(Long userId, String refreshToken) {
        log.debug("refresh token: {}", refreshToken);
        userMapper.saveRefreshToken(userId, refreshToken);
    }

    private boolean isDuplicateLoginId(Long userId) {
        return userMapper.checkDuplicatedLoginId(userId) > 0;
    }

    private boolean isDuplicateNickname(String nickname) {
        return userMapper.checkDuplicatedNickname(nickname) > 0;
    }


}
