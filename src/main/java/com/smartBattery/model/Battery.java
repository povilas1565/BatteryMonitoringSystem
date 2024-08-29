package com.smartBattery.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.*;
import lombok.Data;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Battery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer batteryId;

    private LocalDateTime manufacturingDate;

    private String battery_model;

    private boolean isSendingData = false;

    @OneToMany(mappedBy = "battery")
    private List<BatteryData> dataEntries;

    public Battery() {
        this.manufacturingDate = LocalDateTime.now();
        this.dataEntries = new ArrayList<>();
    }

    // constructor

    public Battery(String battery_model) {
        this.battery_model = battery_model;
        this.manufacturingDate = LocalDateTime.now();
        this.dataEntries = new ArrayList<>();
    }

    // method ro send data from battery to server

    public void startSendingData() {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        if (!isSendingData) {
            isSendingData = true;


            Thread dataSenderThread = new Thread(() -> {

                while (isSendingData) {
                    try {
                        BatteryData batteryData = new BatteryData();
                        String payload = objectMapper.writeValueAsString(batteryData);
                        HttpPost request = new HttpPost("http://localhost:8080/smart_battery/senddata/" + this.batteryId);
                        StringEntity params = new StringEntity(payload, "UTF-8");
                        request.addHeader("content-type", "application/json");
                        request.setEntity(params);
                        httpClient.execute(request);

                        Thread.sleep(60000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            dataSenderThread.start();
        }
        }

    }

