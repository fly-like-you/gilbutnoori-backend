package com.ssafy.gilbut.domain.user.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class UserRequest {

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginDTO {

        @NotNull(message = "Login ID cannot be null")
        private String loginId;

        @NotNull(message = "Password cannot be null")
        @Size(min = 6, message = "Password must be at least 6 characters")
        private String password;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignUpDTO {
        private Long id;

        @NotNull(message = "Login ID cannot be null")
        @Size(min = 4, max = 20, message = "Login ID must be between 4 and 20 characters")
        private String loginId;

        @NotNull(message = "Password cannot be null")
        @Size(min = 6, message = "Password must be at least 6 characters")
        private String password;

        @Email(message = "Email should be valid")
        private String email;

        @NotNull(message = "Nickname cannot be null")
        @Size(min = 3, max = 20, message = "Nickname must be between 3 and 20 characters")
        private String nickname;
    }

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateDTO {

        @NotNull(message = "Login ID cannot be null")
        private String loginId;

        @NotNull(message = "Old password cannot be null")
        @Size(min = 6, message = "Old password must be at least 6 characters")
        private String oldPassword;

        @NotNull(message = "New password cannot be null")
        @Size(min = 6, message = "New password must be at least 6 characters")
        private String newPassword;

        private String profileImg;

        @Email(message = "Email should be valid")
        private String email;

        @NotNull(message = "Nickname cannot be null")
        @Size(min = 3, max = 20, message = "Nickname must be between 3 and 20 characters")
        private String nickname;
    }


}
