package com.house.modeling.core;

/**
 * Ventilatable 인터페이스 - 추상화
 * 환기 기능을 가진 방들이 구현해야 하는 인터페이스
 */
public interface Ventilatable {
    void startVentilation();
    void stopVentilation();
    boolean isVentilating();
    void setVentilationLevel(int level);
    int getVentilationLevel();
}