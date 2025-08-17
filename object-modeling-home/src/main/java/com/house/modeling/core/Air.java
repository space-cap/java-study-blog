package com.house.modeling.core;

/**
 * Air - 최상위 클래스
 * 집 안의 모든 공간에 존재하는 공기를 표현
 * 캡슐화: private 필드와 protected 메서드 사용
 */
public class Air {
    private double temperature;
    private double humidity;
    private double oxygenLevel;
    private boolean isClean;
    
    public Air() {
        this.temperature = 20.0;
        this.humidity = 50.0;
        this.oxygenLevel = 21.0;
        this.isClean = true;
    }
    
    public Air(double temperature, double humidity, double oxygenLevel, boolean isClean) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.oxygenLevel = oxygenLevel;
        this.isClean = isClean;
    }
    
    public double getTemperature() {
        return temperature;
    }
    
    public void setTemperature(double temperature) {
        if (temperature >= -50 && temperature <= 60) {
            this.temperature = temperature;
        }
    }
    
    public double getHumidity() {
        return humidity;
    }
    
    public void setHumidity(double humidity) {
        if (humidity >= 0 && humidity <= 100) {
            this.humidity = humidity;
        }
    }
    
    public double getOxygenLevel() {
        return oxygenLevel;
    }
    
    public void setOxygenLevel(double oxygenLevel) {
        if (oxygenLevel >= 0 && oxygenLevel <= 100) {
            this.oxygenLevel = oxygenLevel;
        }
    }
    
    public boolean isClean() {
        return isClean;
    }
    
    public void setClean(boolean clean) {
        this.isClean = clean;
    }
    
    protected void circulate() {
        System.out.println("공기가 순환되고 있습니다.");
    }
    
    protected void purify() {
        this.isClean = true;
        System.out.println("공기가 정화되었습니다.");
    }
    
    @Override
    public String toString() {
        return String.format("Air{온도=%.1f°C, 습도=%.1f%%, 산소농도=%.1f%%, 청정=%s}", 
                           temperature, humidity, oxygenLevel, isClean ? "예" : "아니오");
    }
}