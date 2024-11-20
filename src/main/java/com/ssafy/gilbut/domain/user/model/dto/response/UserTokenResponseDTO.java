package com.ssafy.gilbut.domain.user.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTokenResponseDTO {
    private String refreshToken;
    private String accessToken;
}
