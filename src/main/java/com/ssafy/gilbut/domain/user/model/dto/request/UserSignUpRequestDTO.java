package com.ssafy.gilbut.domain.user.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpRequestDTO {
    private String id;
    private String loginId;
    private String password;
    private String email;
    private String nickname;
}
