package com.ssafy.gilbut.domain.plan.model.entity;

import com.ssafy.gilbut.domain.attraction.model.entity.Attraction;
import com.ssafy.gilbut.domain.course.model.entity.Course;
import com.ssafy.gilbut.domain.travel.model.entity.Travel;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Plan {
    private Long id;
    private Attraction attraction;
    private Travel travel;
    private Course course;
    private Double sequence;
}
