package com.ssafy.gilbut.domain.travel.model.entity;

import com.ssafy.gilbut.domain.course.model.entity.Course;
import com.ssafy.gilbut.domain.user.model.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Travel {

    @Schema(description = "여행 아이디", requiredMode = RequiredMode.REQUIRED, example = "1")
    @NotNull
    private Long id;

    @Schema(description = "둘렛길 아이디", requiredMode = RequiredMode.REQUIRED, example = "1")
    @NotNull
    private Course course;

    @Schema(description = "사용자 아이디", requiredMode = RequiredMode.REQUIRED, example = "1")
    @NotNull
    private User user;

    @Schema(description = "여행 시작 날자", requiredMode = RequiredMode.REQUIRED, example = "2024-10-01")
    @NotNull
    private LocalDate startDate;

}
