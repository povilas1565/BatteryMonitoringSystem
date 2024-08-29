package com.smartBattery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatteryInfo {

    private Integer battery_id;

    private String model;

    private LocalDate manufacturingDate;

    private double current;

    private double voltage;

    private double temperature;
}
