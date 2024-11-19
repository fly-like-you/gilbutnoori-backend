package com.ssafy.gilbut.domain.user.mapper;

import com.ssafy.gilbut.domain.user.model.dto.request.UserLoginRequestDTO;
import com.ssafy.gilbut.domain.user.model.dto.request.UserSignUpRequestDTO;
import com.ssafy.gilbut.domain.user.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper {
    User login(@Param("user") UserLoginRequestDTO user);

    Integer checkDuplicatedLoginId(@Param("loginId") String loginId);

    Integer checkDuplicatedNickname(@Param("nickname") String nickname);

    User userInfo(@Param("loginId") String loginId);

    Optional<User> findUserByUserId(@Param("loginId") String userId);

    void inactivateUser(User user);

    void register(@Param("user") UserSignUpRequestDTO user);

    /* --------------------- TOKEN ---------------------*/
    void saveRefreshToken(@Param("loginId") String loginId, @Param("refreshToken") String refreshToken);

    String getRefreshToken(@Param("loginId") String loginId);

    void deleteRefreshToken(@Param("loginId") String loginId);
}
