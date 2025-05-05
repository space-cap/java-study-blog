package lee;


import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

class LogUtils {

    private LogUtils() {
        // 인스턴스화 방지
    }

    private static Logger getLogger(Class<?> clazz) {
        return Logger.getLogger(clazz.getName());
    }

    public static void info(Class<?> clazz, String message) {
        getLogger(clazz).info(message);
    }

    public static void warning(Class<?> clazz, String message) {
        getLogger(clazz).warning(message);
    }

    public static void error(Class<?> clazz, String message, Throwable throwable) {
        getLogger(clazz).log(Level.SEVERE, message, throwable);
    }

    public static void debug(Class<?> clazz, String message) {
        getLogger(clazz).fine(message); // JUL에서 디버그는 fine
    }
}


public class LogUtilsTest {
    public static void main(String[] args) {
        configureLogging(); // 로그 레벨 설정

        LogUtils.info(LogUtilsTest.class, "정보 로그입니다.");
        LogUtils.debug(LogUtilsTest.class, "디버그 로그입니다.");
        LogUtils.warning(LogUtilsTest.class, "경고 로그입니다.");

        try {
            throw new RuntimeException("테스트용 예외");
        } catch (Exception e) {
            LogUtils.error(LogUtilsTest.class, "예외 발생!", e);
        }
    }

    // 디버그 로그까지 보이게 설정
    private static void configureLogging() {
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.FINE);
        for (Handler handler : rootLogger.getHandlers()) {
            handler.setLevel(Level.FINE);
        }
    }
}
