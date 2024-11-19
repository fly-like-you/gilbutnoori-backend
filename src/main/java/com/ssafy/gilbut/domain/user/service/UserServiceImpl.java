package com.ssafy.gilbut.domain.user.service;

import com.ssafy.gilbut.advice.status.ErrorStatus;
import com.ssafy.gilbut.domain.user.mapper.UserMapper;
import com.ssafy.gilbut.domain.user.model.dto.request.UserLoginRequestDTO;
import com.ssafy.gilbut.domain.user.model.dto.request.UserSignUpRequestDTO;
import com.ssafy.gilbut.domain.user.model.dto.response.UserResponseDTO;
import com.ssafy.gilbut.domain.user.model.entity.User;
import com.ssafy.gilbut.exception.handler.TempHandler;
import com.ssafy.gilbut.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final JWTUtil jwtUtil;

    @Override
    public void withdraw(String userToken) {
        String userId = jwtUtil.getUserId(userToken);
        User user = userMapper.findUserByUserId(userId).orElseThrow(
                () -> new TempHandler(ErrorStatus.USER_NOT_FOUND)
        );

        userMapper.inactivateUser(user);
    }
    private boolean isDuplicateLoginId(String userId) {
        return userMapper.checkDuplicatedLoginId(userId) > 0;
    }
    private boolean isDuplicateNickname(String nickname) {
        return userMapper.checkDuplicatedNickname(nickname) > 0;
    }
    @Override
    public UserResponseDTO register(UserSignUpRequestDTO user) {
        // 1개 이상 있으면 예외 던지기
        if (isDuplicateLoginId(user.getLoginId())) throw new TempHandler(ErrorStatus.USER_DUPLICATED_LOGIN_ID);
        if (isDuplicateNickname(user.getNickname())) throw new TempHandler(ErrorStatus.USER_DUPLICATED_NICKNAME);

        userMapper.register(user);
        log.debug("register done => user: {}", user);


        return UserResponseDTO.builder()
                .username("asd")
                .build();
    }

    @Override
    public User login(UserLoginRequestDTO memberDto) {
        return userMapper.login(memberDto);
    }

    @Override
    public User userInfo(String loginId) {
        return userMapper.userInfo(loginId);

    }

    @Override
    public void saveRefreshToken(String loginId, String refreshToken) {
        log.debug("refresh token: {}", refreshToken);
        userMapper.saveRefreshToken(loginId, refreshToken);
    }

    @Override
    public String getRefreshToken(String loginId) {
        return userMapper.getRefreshToken(loginId);
    }

    @Override
    public void deleteRefreshToken(String loginId) {
        userMapper.deleteRefreshToken(loginId);
    }


}
