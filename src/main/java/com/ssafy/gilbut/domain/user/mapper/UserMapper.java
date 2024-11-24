package com.ssafy.gilbut.domain.user.mapper;

import com.ssafy.gilbut.domain.user.model.dto.UserRequest;
import com.ssafy.gilbut.domain.user.model.entity.User;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    Optional<User> findUserByLoginId(@Param("loginId") String userId);

    Optional<User> findUserByUserId(@Param("userId") Long userId);

    Integer checkDuplicatedLoginId(@Param("userId") Long userId);

    Integer checkDuplicatedNickname(@Param("nickname") String nickname);

    void updateUser(@Param("userId") Long userId, @Param("user") UserRequest.UpdateDTO user);

    void inactivateUser(@Param("user") User user);

    void register(@Param("user") UserRequest.SignUpDTO user);

    /* --------------------- TOKEN ---------------------*/
    void saveRefreshToken(@Param("userId") Long userId, @Param("refreshToken") String refreshToken);

    String getRefreshToken(@Param("userId") Long userId);

    void deleteRefreshToken(@Param("userId") Long userId);
}
