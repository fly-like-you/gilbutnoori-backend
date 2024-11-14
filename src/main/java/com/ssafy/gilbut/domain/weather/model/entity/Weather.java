package com.ssafy.gilbut.domain.weather.model.entity;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Weather {
    private Long id;
    private Integer sidoCode;
    private LocalDate date;
    private String dust;
    private Double highestTemp;
    private Double lowestTemp;
    private Double tempAverage;
    private LocalDate sunsetTime;
}
