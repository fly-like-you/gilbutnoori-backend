package com.ssafy.gilbut.domain.user.model.dto;

import com.ssafy.gilbut.domain.course.model.entity.Role;
import com.ssafy.gilbut.domain.course.model.entity.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Schema(description = "사용자 정보 응답 관련")
public class UserResponse {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "사용자 정보 간단 응답 DTO")
    public static class DetailResultDTO {
        @Schema(description = "사용자 pk", example = "1")
        private Long id;

        @Schema(description = "로그인 ID", example = "user123")
        private String loginId;

        @Schema(description = "이메일", example = "user@example.com")
        private String email;

        @Schema(description = "사용자 닉네임", example = "홍길동")
        private String nickname;

        @Schema(description = "프로필 사진", example = "http://~~")
        private String profileImg;

        @Schema(description = "생성 일자", example = "2024-11-11")
        private LocalDate createdAt;

        @Schema(description = "수정 일자", example = "2024-11-11")
        private LocalDate updatedAt;

        @Schema(description = "사용자 권한", example = "ADMIN")
        private Role role;

        @Schema(description = "사용자 상태 (휴면 계정 유무)", example = "ACTIVATE")
        private Status status;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "사용자 정보 상세 응답 DTO")
    public static class SimpleResultDTO {

        @Schema(description = "사용자 pk", example = "1")
        private Long id;

        @Schema(description = "로그인 ID", example = "user123")
        private String loginId;

        @Schema(description = "사용자 닉네임", example = "홍길동")
        private String nickname;

        @Schema(description = "이메일", example = "user@example.com")
        private String email;
    }

    @Data
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TokenDTO {
        private String refreshToken;
        private String accessToken;
    }

}
