package com.house.modeling.core;

/**
 * Illuminatable 인터페이스 - 추상화
 * 조명 기능을 가진 방들이 구현해야 하는 인터페이스
 */
public interface Illuminatable {
    void turnOnLight();
    void turnOffLight();
    boolean isLightOn();
    void adjustBrightness(int level);
    int getBrightnessLevel();
}