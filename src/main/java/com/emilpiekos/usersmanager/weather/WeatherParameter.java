package com.emilpiekos.usersmanager.weather;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherParameter {

    private final double amount;
    private final String unit;
}
