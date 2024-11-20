package com.ssafy.gilbut.domain.user.model.entity;

import com.ssafy.gilbut.domain.course.model.entity.Role;
import com.ssafy.gilbut.domain.course.model.entity.Status;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String loginId;
    private String password;
    private String email;
    private String profileImg;
    private String nickname;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Role role;
    private Status status;
}
