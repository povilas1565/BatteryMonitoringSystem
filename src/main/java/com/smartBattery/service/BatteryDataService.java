package com.smartBattery.service;

import com.smartBattery.exception.BatteryDataException;
import com.smartBattery.exception.BatteryException;
import com.smartBattery.model.BatteryData;

import java.time.LocalDateTime;
import java.util.List;

public interface BatteryDataService {

    List<BatteryData> getAllInfoOfBatteryId(Integer batteryId) throws Exception, BatteryException;

    double getLatestVoltageRecord(Integer batteryId) throws BatteryException, BatteryDataException;

    double getLatestCurrentRecord(Integer batteryId) throws BatteryException, BatteryDataException;

    double getLatestTemperatureRecord(Integer batteryId) throws BatteryException, BatteryDataException;

    List<BatteryData> trackRecordWithIdAndTimeStamp(Integer batteryId, LocalDateTime startTime, LocalDateTime endTime) throws BatteryException, BatteryDataException;
}
