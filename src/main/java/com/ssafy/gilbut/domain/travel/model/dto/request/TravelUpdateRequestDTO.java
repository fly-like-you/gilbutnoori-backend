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
public class TravelUpdateRequestDTO {
    private String courseId;
    private String title;
    private String content;
    private LocalDate startDate;
}
