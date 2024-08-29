package com.smartBattery.controller;

import com.smartBattery.model.Battery;
import com.smartBattery.model.BatteryInfo;
import com.smartBattery.service.BatteryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/smart_battery")
public class BatteryController {

    @Autowired
    private BatteryServiceImpl batteryService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Battery> createBatteryHandler(@RequestBody String batteryModel) {
        return ResponseEntity.ok(batteryService.createNewBattery(new Battery(batteryModel)));
    }

    @GetMapping(value = "/{batteryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BatteryInfo> getBatteryInfoHandler(@PathVariable int batteryId) {
        return ResponseEntity.ok(batteryService.getBatteryLatestDetails(batteryId));
    }

    @PostMapping(value = "/startsendingdata/{batteryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> startSendingDataHandler(@PathVariable int batteryId,
                                                  @RequestBody String batteryModel) {
        batteryService.sendBatteryDataToDB(batteryId, batteryModel);
        return ResponseEntity.ok(batteryId);
    }

    @PostMapping(value = "/senddata/{batteryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> sendBatteryDataToDBHandler(@PathVariable int batteryId,
                                                     @RequestBody String batteryModel) {
        batteryService.sendBatteryDataToDB(batteryId, batteryModel);
        return ResponseEntity.ok(batteryId);
    }
}
