package com.house.modeling.core;

/**
 * Room - 추상 클래스 (상속, 추상화)
 * Air 클래스를 상속받아 방의 기본 구조를 정의
 */
public abstract class Room extends Air {
    private String name;
    private double area;
    private int numberOfWindows;
    private boolean hasElectricity;
    
    protected Room(String name, double area, int numberOfWindows) {
        super();
        this.name = name;
        this.area = area;
        this.numberOfWindows = numberOfWindows;
        this.hasElectricity = true;
    }
    
    public String getName() {
        return name;
    }
    
    public double getArea() {
        return area;
    }
    
    public int getNumberOfWindows() {
        return numberOfWindows;
    }
    
    public boolean hasElectricity() {
        return hasElectricity;
    }
    
    public void setElectricity(boolean hasElectricity) {
        this.hasElectricity = hasElectricity;
    }
    
    public abstract void performMainFunction();
    
    public abstract String getRoomType();
    
    public void ventilate() {
        if (numberOfWindows > 0) {
            circulate();
            setHumidity(getHumidity() - 5);
            System.out.println(name + "의 창문을 통해 환기 중입니다.");
        } else {
            System.out.println(name + "에는 창문이 없어 환기할 수 없습니다.");
        }
    }
    
    @Override
    public String toString() {
        return String.format("%s{이름='%s', 면적=%.1f㎡, 창문수=%d개, 전기=%s, %s}", 
                           getRoomType(), name, area, numberOfWindows, 
                           hasElectricity ? "있음" : "없음", super.toString());
    }
}