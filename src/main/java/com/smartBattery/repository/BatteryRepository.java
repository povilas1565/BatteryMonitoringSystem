package com.smartBattery.repository;

import com.smartBattery.model.Battery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatteryRepository extends JpaRepository<Battery, Integer> {
}
