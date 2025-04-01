package ch05;

public class HeapSizeCheck {
    public static void main(String[] args) {
        // Heap size check
        System.out.println("Heap size: " + Runtime.getRuntime().totalMemory() / (1024 * 1024) + " MB");
        System.out.println("Max heap size: " + Runtime.getRuntime().maxMemory() / (1024 * 1024) + " MB");
        System.out.println("Free heap size: " + Runtime.getRuntime().freeMemory() / (1024 * 1024) + " MB");

        long heapSize = Runtime.getRuntime().totalMemory(); // 현재 힙 크기
        long maxHeapSize = Runtime.getRuntime().maxMemory(); // 최대 힙 크기
        long freeHeapSize = Runtime.getRuntime().freeMemory(); // 사용 가능한 힙

        System.out.println("현재 힙 크기 (totalMemory): " + heapSize / (1024 * 1024) + " MB");
        System.out.println("최대 힙 크기 (maxMemory): " + maxHeapSize / (1024 * 1024) + " MB");
        System.out.println("사용 가능한 힙 (freeMemory): " + freeHeapSize / (1024 * 1024) + " MB");
    }
}
