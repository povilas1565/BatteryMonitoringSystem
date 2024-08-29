package com.smartBattery.exception;

public class BatteryException extends RuntimeException {

    public BatteryException() {}

   public BatteryException(String message) {
        super(message);
    }
}
