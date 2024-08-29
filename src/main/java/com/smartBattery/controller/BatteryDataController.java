package com.smartBattery.controller;

import com.smartBattery.model.BatteryData;
import com.smartBattery.service.BatteryDataService;
import com.smartBattery.service.BatteryDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/smart_battery")
public class BatteryDataController {

    @Autowired
    private BatteryDataServiceImpl batteryDataServiceImpl;

    @GetMapping("/info/{batteryId}")
    public ResponseEntity<List<BatteryData>> allInfoHandler(@PathVariable Integer batteryId) throws Exception {

        List<BatteryData> dataList = batteryDataServiceImpl.getAllInfoOfBatteryId(batteryId);

        return new ResponseEntity<>(dataList, HttpStatus.OK);

    }

    @GetMapping("/voltage/{batteryId}")
    public ResponseEntity<Double> getVoltageHandler(@PathVariable Integer batteryId) {

        Double voltage = batteryDataServiceImpl.getLatestVoltageRecord(batteryId);

        return new ResponseEntity<>(voltage, HttpStatus.OK);
    }

    @GetMapping("/current/{batteryId}")
    public ResponseEntity<Double> getCurrentHandler(@PathVariable Integer batteryId){


        Double current = batteryDataServiceImpl.getLatestCurrentRecord(batteryId);

        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    @GetMapping("/temperature/{batteryId}")
    public ResponseEntity<Double> getTemperatureHandler(@PathVariable Integer batteryId) {


        Double temp = batteryDataServiceImpl.getLatestTemperatureRecord(batteryId);

        return new ResponseEntity<>(temp, HttpStatus.OK);
    }

    @GetMapping("/track/{batteryId}/{startTime}/{endTime}")
    public ResponseEntity<List<BatteryData>> trackRecordHandler(@PathVariable Integer batteryId, @PathVariable LocalDateTime startTime, @PathVariable LocalDateTime endTime){

        List<BatteryData> dataList = batteryDataServiceImpl.trackRecordWithIdAndTimeStamp(batteryId, startTime, endTime);

        return new ResponseEntity<>(dataList, HttpStatus.OK);

    }









    }
