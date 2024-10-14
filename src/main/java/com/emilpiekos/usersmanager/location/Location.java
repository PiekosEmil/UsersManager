package com.emilpiekos.usersmanager.location;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Location {

    private final BigDecimal latitude;
    private final BigDecimal longitude;
}
