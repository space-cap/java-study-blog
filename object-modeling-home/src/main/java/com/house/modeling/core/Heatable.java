package com.house.modeling.core;

/**
 * Heatable 인터페이스 - 추상화
 * 난방 기능을 가진 방들이 구현해야 하는 인터페이스
 */
public interface Heatable {
    void turnOnHeating();
    void turnOffHeating();
    boolean isHeatingOn();
    void setTargetTemperature(double temperature);
    double getTargetTemperature();
}