package ch13;

// File: ProducerConsumerWithCondition.java
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithCondition {
    static class BoundedBuffer<T> {
        private final Deque<T> queue = new ArrayDeque<>();
        private final int capacity;
        private final Lock lock = new ReentrantLock();
        private final Condition notFull = lock.newCondition();
        private final Condition notEmpty = lock.newCondition();

        BoundedBuffer(int capacity) {
            this.capacity = capacity;
        }

        public void put(T item) throws InterruptedException {
            lock.lock();
            try {
                while (queue.size() == capacity) {
                    // 버퍼가 가득 찼으면 notFull 신호를 기다린다
                    notFull.await();
                }
                queue.addLast(item);
                // 아이템을 하나 넣었으니 소비자에게 알림
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }

        public T take() throws InterruptedException {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    // 버퍼가 비었으면 notEmpty 신호를 기다린다
                    notEmpty.await();
                }
                T item = queue.removeFirst();
                // 하나 비웠으니 생산자에게 알림
                notFull.signal();
                return item;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(5);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 20; i++) {
                    buffer.put(i);
                    System.out.println("Produced: " + i);
                    Thread.sleep(20); // 생산 속도 조절 (데모용)
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 20; i++) {
                    int v = buffer.take();
                    System.out.println("Consumed: " + v);
                    Thread.sleep(50); // 소비 속도 조절 (데모용)
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer");

        producer.start();
        consumer.start();
    }
}
