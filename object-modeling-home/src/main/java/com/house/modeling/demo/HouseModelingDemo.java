package com.house.modeling.demo;

import com.house.modeling.core.*;
import com.house.modeling.rooms.*;
import java.util.ArrayList;
import java.util.List;

/**
 * HouseModelingDemo - 메인 클래스
 * OOP 4대 원칙을 모두 활용한 데모 프로그램
 */
public class HouseModelingDemo {
    
    public static void main(String[] args) {
        System.out.println("=== 집의 방 객체 모델링 데모 (JDK 21) ===\n");
        
        // 다양한 방 객체 생성 (상속)
        List<Room> rooms = new ArrayList<>();
        rooms.add(new LivingRoom("거실", 25.5, 2));
        rooms.add(new Bedroom("안방", 18.0, 1, 1));
        rooms.add(new Bedroom("작은방", 12.0, 1, 2));
        rooms.add(new Kitchen("주방", 15.0, 1));
        rooms.add(new Bathroom("욕실", 8.5, 1));
        
        System.out.println("1. 캡슐화 - 각 방의 정보 출력:");
        System.out.println("============================================");
        for (Room room : rooms) {
            System.out.println(room);
            System.out.println();
        }
        
        System.out.println("\n2. 상속 - Room 클래스를 상속받은 각 방의 기본 기능:");
        System.out.println("==========================================================");
        for (Room room : rooms) {
            room.performMainFunction(); // 추상 메서드 구현 (다형성)
            room.ventilate(); // 부모 클래스의 메서드 활용
            System.out.println();
        }
        
        System.out.println("\n3. 추상화 - 인터페이스를 통한 기능별 그룹화:");
        System.out.println("===============================================");
        
        // 조명 기능이 있는 방들
        System.out.println("▶ 조명 기능이 있는 방들:");
        for (Room room : rooms) {
            if (room instanceof Illuminatable illuminatable) {
                illuminatable.turnOnLight();
                illuminatable.adjustBrightness(75);
            }
        }
        System.out.println();
        
        // 난방 기능이 있는 방들
        System.out.println("▶ 난방 기능이 있는 방들:");
        for (Room room : rooms) {
            if (room instanceof Heatable heatable) {
                heatable.turnOnHeating();
                heatable.setTargetTemperature(23.0);
            }
        }
        System.out.println();
        
        // 환기 기능이 있는 방들
        System.out.println("▶ 환기 기능이 있는 방들:");
        for (Room room : rooms) {
            if (room instanceof Ventilatable ventilatable) {
                ventilatable.startVentilation();
                ventilatable.setVentilationLevel(3);
            }
        }
        System.out.println();
        
        System.out.println("\n4. 다형성 - 각 방의 고유한 기능 실행:");
        System.out.println("==========================================");
        
        for (Room room : rooms) {
            System.out.println("▶ " + room.getName() + " (" + room.getRoomType() + "):");
            
            // 각 방의 고유 기능 실행 (다형성)
            switch (room) {
                case LivingRoom livingRoom -> {
                    livingRoom.watchTv();
                    System.out.println("TV 보유: " + livingRoom.hasTv());
                    System.out.println("소파 보유: " + livingRoom.hasSofa());
                }
                case Bedroom bedroom -> {
                    bedroom.sleep();
                    bedroom.organizeCloset();
                    System.out.println("침대 수: " + bedroom.getNumberOfBeds());
                }
                case Kitchen kitchen -> {
                    kitchen.cook();
                    kitchen.washDishes();
                    kitchen.openRefrigerator();
                    System.out.println("버너 수: " + kitchen.getNumberOfBurners());
                }
                case Bathroom bathroom -> {
                    bathroom.takeShower();
                    bathroom.stopWater();
                    System.out.println("샤워실 보유: " + bathroom.hasShower());
                    System.out.println("욕조 보유: " + bathroom.hasBathtub());
                }
                default -> System.out.println("알 수 없는 방 타입입니다.");
            }
            System.out.println();
        }
        
        System.out.println("\n5. 공기(Air) 클래스 상속 확인:");
        System.out.println("=================================");
        Room sampleRoom = rooms.get(0);
        System.out.println("방의 공기 상태: " + sampleRoom);
        sampleRoom.setTemperature(25.0);
        sampleRoom.setHumidity(45.0);
        System.out.println("온도/습도 조절 후: " + sampleRoom);
        
        System.out.println("\n=== 데모 완료 ===");
        
        // JDK 21 기능 활용 예시
        demonstrateJdk21Features(rooms);
    }
    
    /**
     * JDK 21의 새로운 기능들을 활용한 데모
     */
    private static void demonstrateJdk21Features(List<Room> rooms) {
        System.out.println("\n6. JDK 21 기능 활용:");
        System.out.println("====================");
        
        // Pattern Matching for switch (JDK 21)
        System.out.println("▶ Pattern Matching을 활용한 방 타입별 처리:");
        for (Room room : rooms) {
            String feature = switch (room) {
                case LivingRoom lr -> "가족 휴식 공간 - TV: " + lr.hasTv();
                case Bedroom br -> "수면 공간 - 침대 수: " + br.getNumberOfBeds();
                case Kitchen k -> "요리 공간 - 버너 수: " + k.getNumberOfBurners();
                case Bathroom br -> "위생 공간 - 샤워실: " + br.hasShower();
                default -> "일반 방";
            };
            System.out.println(room.getName() + ": " + feature);
        }
        
        // Virtual Threads 시뮬레이션 (간단한 예시)
        System.out.println("\n▶ 각 방의 동시 작업 시뮬레이션:");
        rooms.parallelStream().forEach(room -> {
            System.out.println("[" + Thread.currentThread().getName() + "] " 
                             + room.getName() + "에서 작업 중...");
        });
    }
}