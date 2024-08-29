package com.smartBattery.service;

import com.smartBattery.exception.BatteryException;
import com.smartBattery.model.Battery;
import com.smartBattery.model.BatteryInfo;
import com.smartBattery.repository.BatteryDataRepository;
import com.smartBattery.repository.BatteryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatteryServiceImpl implements BatteryService {

    @Autowired
    private BatteryRepository batteryRepository;

    @Autowired
    private BatteryDataRepository batteryDataRepository;

    @Override
    @Transactional
    public Battery createNewBattery(Battery battery) {
        return batteryRepository.save(battery);
    }

    @Override
    public Battery getBatteryById(Integer batteryId) throws BatteryException {
        return batteryRepository.findById(batteryId).orElseThrow(() -> new BatteryException("no battery with id " + batteryId));
    }

    @Override
    public BatteryInfo getBatteryLatestDetails(Integer batteryId) throws BatteryException {
        return batteryDataRepository.findLatestDataByBatteryId(batteryId)
                .stream()
                .map(batteryData -> new BatteryInfo(
                        batteryData.getBattery().getBatteryId(),
                        batteryData.getBattery().getBattery_model(),
                        batteryData.getBattery().getManufacturingDate().toLocalDate(),
                        batteryData.getCurrent(),
                        batteryData.getVoltage(),
                        batteryData.getTemperature()
                ))
                .findFirst()
                .orElseThrow(() -> new BatteryException("no battery with id " + batteryId));
    }

    @Override
    @Transactional
    public void sendBatteryDataToDB(Integer batteryId, String batteryModel) throws BatteryException {
        Battery battery = batteryRepository.findById(batteryId).orElseThrow(() -> new BatteryException("no battery with id " + batteryId));
        battery.setBattery_model(batteryModel);
    }
}





