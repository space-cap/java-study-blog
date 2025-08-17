package com.house.modeling.rooms;

import com.house.modeling.core.*;

/**
 * Kitchen - 주방 클래스 (상속, 다형성)
 */
public class Kitchen extends Room implements Illuminatable, Ventilatable {
    private boolean lightOn;
    private int brightnessLevel;
    private boolean ventilating;
    private int ventilationLevel;
    private boolean hasStove;
    private boolean hasRefrigerator;
    private boolean hasSink;
    private int numberOfBurners;
    
    public Kitchen(String name, double area, int numberOfWindows) {
        super(name, area, numberOfWindows);
        this.lightOn = false;
        this.brightnessLevel = 0;
        this.ventilating = false;
        this.ventilationLevel = 0;
        this.hasStove = true;
        this.hasRefrigerator = true;
        this.hasSink = true;
        this.numberOfBurners = 4;
    }
    
    @Override
    public void performMainFunction() {
        System.out.println(getName() + "에서 요리를 하고 있습니다.");
        if (hasStove) {
            System.out.println("가스레인지를 사용하여 음식을 조리하고 있습니다.");
        }
        if (hasSink) {
            System.out.println("싱크대에서 설거지를 하고 있습니다.");
        }
    }
    
    @Override
    public String getRoomType() {
        return "주방";
    }
    
    @Override
    public void turnOnLight() {
        this.lightOn = true;
        this.brightnessLevel = 80;
        System.out.println(getName() + "의 밝은 조명을 켰습니다.");
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
        this.ventilationLevel = 4;
        System.out.println(getName() + "의 후드 환기팬을 작동시켰습니다.");
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
    
    public void cook() {
        if (hasStove && hasElectricity()) {
            startVentilation();
            System.out.println(getName() + "에서 요리를 시작합니다.");
        }
    }
    
    public void washDishes() {
        if (hasSink) {
            System.out.println(getName() + "에서 설거지를 하고 있습니다.");
        }
    }
    
    public void openRefrigerator() {
        if (hasRefrigerator && hasElectricity()) {
            System.out.println(getName() + "의 냉장고를 열었습니다.");
        }
    }
    
    public boolean hasStove() {
        return hasStove;
    }
    
    public boolean hasRefrigerator() {
        return hasRefrigerator;
    }
    
    public boolean hasSink() {
        return hasSink;
    }
    
    public int getNumberOfBurners() {
        return numberOfBurners;
    }
}