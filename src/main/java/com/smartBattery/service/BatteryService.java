package com.smartBattery.service;

import com.smartBattery.exception.BatteryException;
import com.smartBattery.model.Battery;
import com.smartBattery.model.BatteryInfo;

public interface BatteryService {

    Battery createNewBattery(Battery battery);

    Battery getBatteryById(Integer batteryId) throws BatteryException;

    BatteryInfo getBatteryLatestDetails(Integer batteryId) throws BatteryException;

    void sendBatteryDataToDB(Integer batteryId) throws BatteryException;


}
