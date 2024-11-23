package com.ssafy.gilbut.domain.weather.model.dto;// ForecastResponse.java
import lombok.Data;
import java.util.List;

@Data
public class ForecastResponse {
    private List<ForecastItem> list;

    @Data
    public static class ForecastItem {
        private String dt_txt;
        private Main main;
    }

    @Data
    public static class Main {
        private double temp;
        private double temp_min;
        private double temp_max;
    }
}