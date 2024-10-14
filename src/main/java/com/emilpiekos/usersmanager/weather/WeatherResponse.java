package com.emilpiekos.usersmanager.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherResponse {
    public double latitude;
    public double longitude;
    public double generationtime_ms;
    public int utc_offset_seconds;
    public String timezone;
    public String timezone_abbreviation;
    public double elevation;
    public DailyUnits daily_units;
    public Daily daily;

    @Data
    public static class DailyUnits {
        public String time;

        @JsonProperty("temperature_2m_max")
        public String temperature2mMax;

        @JsonProperty("temperature_2m_min")
        public String temperature2mMin;

        public String precipitation_sum;
    }

    @Data
    public static class Daily {
        public List<String> time;

        @JsonProperty("temperature_2m_max")
        public List<Double> temperature2mMax;

        @JsonProperty("temperature_2m_min")
        public List<Double> temperature2mMin;

        public List<Double> precipitation_sum;
    }
}
