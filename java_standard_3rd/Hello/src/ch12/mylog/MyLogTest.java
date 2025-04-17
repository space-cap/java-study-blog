package ch12.mylog;

public class MyLogTest {
    public static void main(String[] args) {
        IWorker origin = new Worker();

        origin.doWork();
        origin.skipLog();

        System.out.println("====================");

        IWorker proxy = MyAopProxy.createProxy(origin);

        proxy.doWork();    // 로그 출력됨!
        proxy.skipLog();   // 로그 없음
    }
}
