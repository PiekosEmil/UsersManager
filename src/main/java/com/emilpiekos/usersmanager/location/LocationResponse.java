package com.emilpiekos.usersmanager.location;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationResponse {

    private Element element;
    private boolean success;
    private String message;
    private String error;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Element {
        private String providedBy;
        private double latitude;
        private double longitude;
        private Bounds bounds;
        private String streetNumber;
        private String streetName;
        private String postalCode;
        private String locality;
        private String subLocality;
        private Map<String, AdminLevel> adminLevels;
        private String country;
        private String countryCode;
        private String timezone;
        private String id;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Bounds {

            private double north;
            private double south;
            private double west;
            private double east;
        }

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class AdminLevel {

            private String name;
            private String code;
            private int level;
        }
    }
}
