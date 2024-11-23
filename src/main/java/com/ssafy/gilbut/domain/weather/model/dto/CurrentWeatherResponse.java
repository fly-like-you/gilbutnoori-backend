package com.ssafy.gilbut.domain.weather.model.dto;

import lombok.Data;

@Data
public class CurrentWeatherResponse {
    private Main main;
    private Sys sys;

    @Data
    public static class Main {
        private double temp;
        private double temp_min;
        private double temp_max;
    }

    @Data
    public static class Sys {
        private long sunrise;
        private long sunset;
    }
}
