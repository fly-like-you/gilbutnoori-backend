package com.ssafy.gilbut.domain.user.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "토큰")
public class Token {

    @Schema(description = "자원 접근에 필요한 토큰")
    private String accessToken;

    @Schema(description = "accessToken 재발급에 필요한 토큰")
    private String refreshToken;

}
