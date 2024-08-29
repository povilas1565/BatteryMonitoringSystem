package com.smartBattery.model;

import com.smartBattery.model.Battery;
import jakarta.persistence.*;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDateTime;

@Data
@Entity
public class BatteryData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer data_id;

    private double current;

    private double voltage;

    private double temperature;

    private LocalDateTime timeStamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "battery_id")
    @JsonIgnore
    private Battery battery;

    public BatteryData() {

        double curr = 10*Math.random();
        this.current = Math.round(curr*100)/100.0;

        double vol = 150 + (400-150)*Math.random();
        this.voltage = Math.round(vol*100)/100.0;

        double temp = 30 + (100-30)*Math.random();
        this.temperature = Math.round(temp*100)/100.0;

        this.timeStamp = LocalDateTime.now();

    }
}
