package com.smartBattery;

import com.smartBattery.model.Battery;
import com.smartBattery.model.BatteryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BatteryDataRepository extends JpaRepository<BatteryData, Integer> {

    List<BatteryData> findByBatteryOrderByTimeStampDesc(Battery currentBattery);

    @Query("SELECT bd FROM BatteryData bd WHERE bd.battery.batteryId = :batteryId ORDER BY bd.timeStamp DESC")
    List<BatteryData> findLatestDataByBatteryId(Integer batteryId);

    @Query("SELECT bd FROM BatteryData bd WHERE bd.battery.batteryId = :batteryId AND bd.timeStamp BETWEEN :startTime AND :endTime ORDER BY bd.timeStamp DESC")
    List<BatteryData> findByBatteryIdAndTimeStampBetween(Integer batteryId, LocalDateTime startTime, LocalDateTime endTime);

    // findLatestCurrentByBatteryId
    // findLatestVoltageByBatteryId
    // findLatestTemperatureByBatteryId

}
