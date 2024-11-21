package com.ssafy.gilbut.domain.user.service;

import com.ssafy.gilbut.advice.status.ErrorStatus;
import com.ssafy.gilbut.domain.user.mapper.UserMapper;
import com.ssafy.gilbut.domain.user.model.dto.request.UserInfoResponseDTO;
import com.ssafy.gilbut.domain.user.model.dto.request.UserLoginRequestDTO;
import com.ssafy.gilbut.domain.user.model.dto.request.UserSignUpRequestDTO;
import com.ssafy.gilbut.domain.user.model.dto.request.UserUpdateRequestDTO;
import com.ssafy.gilbut.domain.user.model.dto.response.UserResponseDTO;
import com.ssafy.gilbut.domain.user.model.dto.response.UserTokenResponseDTO;
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
    public void withdraw(String accessToken) {
        String userTokenId = jwtUtil.getUserLoginId(accessToken);
        User user = getActivatedUserByLoginId(userTokenId);

        userMapper.inactivateUser(user);
    }

    @Override
    public UserResponseDTO register(UserSignUpRequestDTO user) {
        // 1개 이상 있으면 예외 던지기
        if (isDuplicateLoginId(user.getLoginId())) throw new GeneralExceptionHandler(ErrorStatus.USER_DUPLICATED_LOGIN_ID);
        if (isDuplicateNickname(user.getNickname())) throw new GeneralExceptionHandler(ErrorStatus.USER_DUPLICATED_NICKNAME);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.register(user);

        log.debug("register done => user: {}", user);

        return UserResponseDTO.builder()
                .nickname("asd")
                .build();
    }

    @Override
    public UserTokenResponseDTO login(UserLoginRequestDTO memberDto) {
        // 로그인 아이디 확인
        User login = getActivatedUserByLoginId(memberDto.getLoginId());
        String password = login.getPassword();

        // 비밀번호 일치 확인
        if (!passwordEncoder.matches(memberDto.getPassword(), password))
            throw new GeneralExceptionHandler(ErrorStatus.USER_LOGIN_FAILED);

        // 토큰 생성
        String accessToken = jwtUtil.createAccessToken(login);
        String refreshToken = jwtUtil.createRefreshToken(login);

        // Refresh Token만 DB에 저장
        saveRefreshToken(login.getLoginId(), refreshToken);

        return UserTokenResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public UserInfoResponseDTO userInfo(String token) {
        // 토큰 값과 파라미터의 아이디가 일치하는지 검사
        String tokenUserLoginId = jwtUtil.getUserLoginId(token);

        // 유저 데이터 가져오기
        User user = getActivatedUserByLoginId(tokenUserLoginId);

        return UserInfoResponseDTO.builder()
                .loginId(user.getLoginId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }




    @Override
    public UserTokenResponseDTO refreshAccessToken(String refreshToken) {
        // RefreshToken 유효성 검사하기
        jwtUtil.checkTokenValidation(refreshToken);

        // 토큰에서 ID 찾기
        User user = getActivatedUserByLoginId(jwtUtil.getUserLoginId(refreshToken));

        // ID로 RefreshToken 가져오기
        String authRefreshToken = getRefreshToken(user.getLoginId());
        if (!authRefreshToken.equals(refreshToken)) throw new GeneralExceptionHandler(ErrorStatus.USER_BAD_REQUEST);

        // RefreshToken 저장하기
        String newToken = jwtUtil.createAccessToken(user);
        log.debug("정상적으로 access token 재발급!!!");

        return UserTokenResponseDTO.builder()
                .accessToken(newToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void updateUser(String accessToken, UserUpdateRequestDTO requestUser) {
        // 토큰으로부터 사용자의 정보 가져오기
        User user = getActivatedUserByLoginId(jwtUtil.getUserLoginId(accessToken));

        // 비밀번호 일치 확인
        if (!passwordEncoder.matches(user.getPassword(), passwordEncoder.encode(requestUser.getOldPassword())))
            throw new GeneralExceptionHandler(ErrorStatus.USER_LOGIN_FAILED);

        userMapper.updateUser(user.getLoginId(), requestUser);
    }

    @Override
    public void deleteRefreshToken(String accessToken) {
        // 토큰으로부터 사용자의 아이디 찾아오기
        String userTokenId = jwtUtil.getUserLoginId(accessToken);
        log.debug("userTokenId -> {}", userTokenId);

        // DB에서 token값 삭제하기 (로그아웃)
        userMapper.deleteRefreshToken(userTokenId);
    }

    /**
     * 상태가 ACTIVATE인 사용자를 가져오는 코드
     * 유저 데이터를 Mapper를 가져올 때는 아래 메서드만 사용해주세요
     * @param loginId
     * @return 상태가 ACTIVATE인 유저 엔티티
     */
    private User getActivatedUserByLoginId(String loginId) {
        return userMapper.findUserByLoginId(loginId).orElseThrow(
                () -> new GeneralExceptionHandler(ErrorStatus.USER_NOT_FOUND)
        );
    }

    private String getRefreshToken(String loginId) {
        return userMapper.getRefreshToken(loginId);
    }

    private void saveRefreshToken(String loginId, String refreshToken) {
        log.debug("refresh token: {}", refreshToken);
        userMapper.saveRefreshToken(loginId, refreshToken);
    }

    private boolean isDuplicateLoginId(String userId) {
        return userMapper.checkDuplicatedLoginId(userId) > 0;
    }

    private boolean isDuplicateNickname(String nickname) {
        return userMapper.checkDuplicatedNickname(nickname) > 0;
    }


}
