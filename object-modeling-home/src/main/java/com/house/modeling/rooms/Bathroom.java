package com.house.modeling.rooms;

import com.house.modeling.core.*;

/**
 * Bathroom - 욕실 클래스 (상속, 다형성)
 */
public class Bathroom extends Room implements Illuminatable, Ventilatable, Heatable {
    private boolean lightOn;
    private int brightnessLevel;
    private boolean ventilating;
    private int ventilationLevel;
    private boolean heatingOn;
    private double targetTemperature;
    private boolean hasShower;
    private boolean hasBathtub;
    private boolean hasToilet;
    private boolean waterRunning;
    
    public Bathroom(String name, double area, int numberOfWindows) {
        super(name, area, numberOfWindows);
        this.lightOn = false;
        this.brightnessLevel = 0;
        this.ventilating = false;
        this.ventilationLevel = 0;
        this.heatingOn = false;
        this.targetTemperature = 24.0;
        this.hasShower = true;
        this.hasBathtub = true;
        this.hasToilet = true;
        this.waterRunning = false;
    }
    
    @Override
    public void performMainFunction() {
        System.out.println(getName() + "에서 개인 위생을 관리하고 있습니다.");
        if (hasShower) {
            System.out.println("샤워를 하고 있습니다.");
        }
        if (hasToilet) {
            System.out.println("화장실을 이용하고 있습니다.");
        }
    }
    
    @Override
    public String getRoomType() {
        return "욕실";
    }
    
    @Override
    public void turnOnLight() {
        this.lightOn = true;
        this.brightnessLevel = 70;
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
    public void startVentilation() {
        this.ventilating = true;
        this.ventilationLevel = 3;
        System.out.println(getName() + "의 환기팬을 작동시켰습니다.");
    }
    
    @Override
    public void stopVentilation() {
        this.ventilating = false;
        this.ventilationLevel = 0;
        System.out.println(getName() + "의 환기팬을 정지시켰습니다.");
    }
    
    @Override
    public boolean isVentilating() {
        return ventilating;
    }
    
    @Override
    public void setVentilationLevel(int level) {
        if (ventilating && level >= 1 && level <= 5) {
            this.ventilationLevel = level;
            System.out.println(getName() + "의 환기팬을 " + level + "단계로 설정했습니다.");
        }
    }
    
    @Override
    public int getVentilationLevel() {
        return ventilationLevel;
    }
    
    @Override
    public void turnOnHeating() {
        this.heatingOn = true;
        System.out.println(getName() + "의 온수 난방을 켰습니다.");
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
    
    public void takeShower() {
        if (hasShower) {
            this.waterRunning = true;
            turnOnHeating();
            startVentilation();
            System.out.println(getName() + "에서 샤워를 하고 있습니다.");
        }
    }
    
    public void takeBath() {
        if (hasBathtub) {
            this.waterRunning = true;
            turnOnHeating();
            System.out.println(getName() + "에서 목욕을 하고 있습니다.");
        }
    }
    
    public void stopWater() {
        this.waterRunning = false;
        System.out.println(getName() + "의 물을 잠갔습니다.");
    }
    
    public boolean hasShower() {
        return hasShower;
    }
    
    public boolean hasBathtub() {
        return hasBathtub;
    }
    
    public boolean hasToilet() {
        return hasToilet;
    }
    
    public boolean isWaterRunning() {
        return waterRunning;
    }
}