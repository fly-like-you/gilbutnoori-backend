package com.ssafy.gilbut.domain.travel.model.dto.response;

import com.ssafy.gilbut.domain.course.model.dto.CourseSimpleResponseDTO;
import com.ssafy.gilbut.domain.user.model.dto.response.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelResponseDTO {
    private Integer id;
    private String title;
    private CourseSimpleResponseDTO course;
    private UserResponseDTO user;
    private LocalDate startDate;
}
