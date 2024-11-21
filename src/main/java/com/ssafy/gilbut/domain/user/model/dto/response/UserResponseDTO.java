package com.ssafy.gilbut.domain.user.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "사용자 정보 응답 DTO")
public class UserResponseDTO {
    @Schema(description = "로그인 ID", example = "user123")
    private String loginId;

    @Schema(description = "사용자 이름", example = "홍길동")
    private String nickname;

    @Schema(description = "이메일", example = "user@example.com")
    private String email;
}