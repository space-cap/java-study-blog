package ch12.mylog;

public class Worker implements IWorker {
    @MyLog
    public void doWork() {
        System.out.println("🛠️ 실제 작업 수행 중...");
    }

    public void skipLog() {
        System.out.println("🕳️ 이 메서드는 로그 없음.");
    }
}

