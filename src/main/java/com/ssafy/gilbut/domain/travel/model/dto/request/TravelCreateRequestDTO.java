package com.ssafy.gilbut.domain.travel.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelCreateRequestDTO {
    private Integer id;
    private String title;
    private String content;
    private String courseId;
    private LocalDate startDate;
}
