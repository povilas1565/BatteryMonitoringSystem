package com.smartBattery.repository;

import com.smartBattery.model.Battery;
import com.smartBattery.model.BatteryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BatteryDataRepository extends JpaRepository<BatteryData, Integer> {

    @Query("SELECT bd FROM BatteryData bd WHERE bd.battery.batteryId = :batteryId ORDER BY bd.timeStamp DESC")
    List<BatteryData> findLatestDataByBatteryId(Integer batteryId);


    @Query("SELECT bd.voltage FROM BatteryData bd WHERE bd.battery.batteryId = :batteryId ORDER BY bd.timeStamp DESC")
    Optional<Double> findLatestVoltageByBatteryId(Integer batteryId);


    @Query("SELECT bd.current FROM BatteryData bd WHERE bd.battery.batteryId = :batteryId ORDER BY bd.timeStamp DESC")
    Optional<Double> findLatestCurrentByBatteryId(Integer batteryId);

    @Query("SELECT bd.temperature FROM BatteryData bd WHERE bd.battery.batteryId = :batteryId ORDER BY bd.timeStamp DESC")
    Optional<Double> findLatestTemperatureByBatteryId(Integer batteryId);

    @Query("SELECT bd FROM BatteryData bd WHERE bd.battery.batteryId = :batteryId AND bd.timeStamp BETWEEN :startTime AND :endTime ORDER BY bd.timeStamp DESC")
    List<BatteryData> findRecordByBatteryIdAndTimeStampBetween(Integer batteryId, LocalDateTime startTime, LocalDateTime endTime);


    List<BatteryData> findByBatteryOrderByTimeStampDesc(Battery currentBattery);


}
