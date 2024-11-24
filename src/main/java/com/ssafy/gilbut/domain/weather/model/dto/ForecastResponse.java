package com.ssafy.gilbut.domain.weather.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class ForecastResponse {
    private List<ForecastItem> list;

    @Data
    public static class ForecastItem {
        private String dt_txt;
        private Main main;
        private List<Weather> weather;
        private Clouds clouds;
        private Wind wind;
        private int visibility;
        private double pop;
        private Rain rain;
    }

    @Data
    public static class Main {
        private double temp;
        private double temp_min;
        private double temp_max;
        private int humidity;
    }

    @Data
    public static class Weather {
        private String main;
        private String description;
        private String icon;
    }

    @Data
    public static class Clouds {
        private int all;
    }

    @Data
    public static class Wind {
        private double speed;
        private double gust;
    }

    @Data
    public static class Rain {
        @JsonProperty("3h")
        private double threeHour;
    }
}