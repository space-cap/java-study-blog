package com.house.modeling.rooms;

import com.house.modeling.core.*;

/**
 * Bedroom - 침실 클래스 (상속, 다형성)
 */
public class Bedroom extends Room implements Illuminatable, Heatable {
    private boolean lightOn;
    private int brightnessLevel;
    private boolean heatingOn;
    private double targetTemperature;
    private boolean hasBed;
    private boolean hasCloset;
    private int numberOfBeds;
    
    public Bedroom(String name, double area, int numberOfWindows, int numberOfBeds) {
        super(name, area, numberOfWindows);
        this.lightOn = false;
        this.brightnessLevel = 0;
        this.heatingOn = false;
        this.targetTemperature = 20.0;
        this.hasBed = true;
        this.hasCloset = true;
        this.numberOfBeds = numberOfBeds;
    }
    
    @Override
    public void performMainFunction() {
        System.out.println(getName() + "에서 잠을 자고 휴식을 취하고 있습니다.");
        if (hasCloset) {
            System.out.println("옷장에서 옷을 정리하고 있습니다.");
        }
    }
    
    @Override
    public String getRoomType() {
        return "침실";
    }
    
    @Override
    public void turnOnLight() {
        this.lightOn = true;
        this.brightnessLevel = 30;
        System.out.println(getName() + "의 조명을 은은하게 켰습니다.");
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
    
    public void sleep() {
        if (hasBed) {
            turnOffLight();
            System.out.println(getName() + "에서 잠을 자고 있습니다.");
        }
    }
    
    public void organizeCloset() {
        if (hasCloset) {
            System.out.println(getName() + "의 옷장을 정리하고 있습니다.");
        }
    }
    
    public boolean hasBed() {
        return hasBed;
    }
    
    public boolean hasCloset() {
        return hasCloset;
    }
    
    public int getNumberOfBeds() {
        return numberOfBeds;
    }
}