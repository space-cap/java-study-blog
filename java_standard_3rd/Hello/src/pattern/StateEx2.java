package pattern;
import java.awt.BorderLayout;

interface State0519 {
    void doClock(Context context, int hour); // 시간 설정
    void useSafe(Context context); // 금고 사용
    void doAlarm(Context context); // 비상벨
    void doPhoto(Context context); // 일반 통화
}

class DayState0519 implements State0519 {
    private static final DayState0519 instance = new DayState0519();

    private DayState0519() {}

    public static DayState0519 getInstance() {
        return instance;
    }

    @Override
    public void doClock(Context context, int hour) {
        if(hour < 9 || hour >= 17) {
            context.changeState(NightState.getInstance());
        }
    }

    @Override
    public void useSafe(Context context) {
        context.recordLog("금고 사용 주간");
    }

    @Override
    public void doAlarm(Context context) {
        context.callSecurityCenter("비상벨 주간");
    }

    @Override
    public void doPhoto(Context context) {
        context.callSecurityCenter("일반 통화 주간");
    }
}

class NightState implements State0519 {
    private static final NightState instance = new NightState();
    private NightState() {}
    public static NightState getInstance() {
        return instance;
    }

    @Override
    public void doClock(Context context, int hour) {
        if(hour >= 9 && hour < 17) {
            context.changeState(DayState0519.getInstance());
        }
    }

    @Override
    public void useSafe(Context context) {
        context.callSecurityCenter("비상 야간 금고 사용!");
    }

    @Override
    public void doAlarm(Context context) {
        context.callSecurityCenter("비상벨 야간");
    }

    @Override
    public void doPhoto(Context context) {
        context.recordLog("야간 통화 녹음");
    }
}

class SafeFrame extends Frame implements ActionListener, Context {}

interface Context {
    void setClock(int hour);
    void changeState(State0519 state);
    void callSecurityCenter(String msg);
    void recordLog(String msg);
}

public class StateEx2 {
    public static void main(String[] args) {
        System.out.println("Hello state");
    }
}

