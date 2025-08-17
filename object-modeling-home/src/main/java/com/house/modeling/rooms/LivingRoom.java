package com.house.modeling.rooms;

import com.house.modeling.core.*;

/**
 * LivingRoom - 거실 클래스 (상속, 다형성, 인터페이스 구현)
 */
public class LivingRoom extends Room implements Illuminatable, Heatable, Ventilatable {
    private boolean lightOn;
    private int brightnessLevel;
    private boolean heatingOn;
    private double targetTemperature;
    private boolean ventilating;
    private int ventilationLevel;
    private boolean hasTv;
    private boolean hasSofa;
    
    public LivingRoom(String name, double area, int numberOfWindows) {
        super(name, area, numberOfWindows);
        this.lightOn = false;
        this.brightnessLevel = 0;
        this.heatingOn = false;
        this.targetTemperature = 22.0;
        this.ventilating = false;
        this.ventilationLevel = 0;
        this.hasTv = true;
        this.hasSofa = true;
    }
    
    @Override
    public void performMainFunction() {
        System.out.println(getName() + "에서 가족들이 휴식을 취하고 있습니다.");
        if (hasTv) {
            System.out.println("TV를 시청하며 여가시간을 보내고 있습니다.");
        }
    }
    
    @Override
    public String getRoomType() {
        return "거실";
    }
    
    @Override
    public void turnOnLight() {
        this.lightOn = true;
        this.brightnessLevel = 50;
        System.out.println(getName() + "의 조명을 켰습니다.");
    }
    
    @Override
    public void turnOffLight() {
        this.lightOn = false;
        this.brightnessLevel = 0;
        System.out.println(getName() + "의 조명을 껐습니다.");
    }
    
    @Override
    public boolean isLightOn() {
        return lightOn;
    }
    
    @Override
    public void adjustBrightness(int level) {
        if (lightOn && level >= 0 && level <= 100) {
            this.brightnessLevel = level;
            System.out.println(getName() + "의 조명 밝기를 " + level + "%로 조절했습니다.");
        }
    }
    
    @Override
    public int getBrightnessLevel() {
        return brightnessLevel;
    }
    
    @Override
    public void turnOnHeating() {
        this.heatingOn = true;
        System.out.println(getName() + "의 난방을 켰습니다.");
    }
    
    @Override
    public void turnOffHeating() {
        this.heatingOn = false;
        System.out.println(getName() + "의 난방을 껐습니다.");
    }
    
    @Override
    public boolean isHeatingOn() {
        return heatingOn;
    }
    
    @Override
    public void setTargetTemperature(double temperature) {
        this.targetTemperature = temperature;
        System.out.println(getName() + "의 목표 온도를 " + temperature + "°C로 설정했습니다.");
    }
    
    @Override
    public double getTargetTemperature() {
        return targetTemperature;
    }
    
    @Override
    public void startVentilation() {
        this.ventilating = true;
        this.ventilationLevel = 3;
        System.out.println(getName() + "의 환기를 시작했습니다.");
    }
    
    @Override
    public void stopVentilation() {
        this.ventilating = false;
        this.ventilationLevel = 0;
        System.out.println(getName() + "의 환기를 중단했습니다.");
    }
    
    @Override
    public boolean isVentilating() {
        return ventilating;
    }
    
    @Override
    public void setVentilationLevel(int level) {
        if (ventilating && level >= 1 && level <= 5) {
            this.ventilationLevel = level;
            System.out.println(getName() + "의 환기 세기를 " + level + "단계로 설정했습니다.");
        }
    }
    
    @Override
    public int getVentilationLevel() {
        return ventilationLevel;
    }
    
    public void watchTv() {
        if (hasTv && hasElectricity()) {
            System.out.println(getName() + "에서 TV를 시청하고 있습니다.");
        }
    }
    
    public boolean hasTv() {
        return hasTv;
    }
    
    public boolean hasSofa() {
        return hasSofa;
    }
}