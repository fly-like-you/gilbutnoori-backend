package com.ssafy.gilbut.domain.course.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseSearchCriteria {
    String routeId;
    Integer dist;
    Integer turnaround;
    Integer level;
    Integer cycle;
    Integer sidoCode;
    Integer gugunCode;
}
