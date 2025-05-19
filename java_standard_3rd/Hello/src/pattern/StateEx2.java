package pattern;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

class SafeFrame extends Frame implements ActionListener, Context {
    private TextField textClock = new TextField(60);
    private TextArea textScreen = new TextArea(10,60);
    private Button buttonUse = new Button("금고사용");
    private Button buttonAlarm = new Button("비상벨");
    private Button buttonPhoto = new Button("일반 통화");
    private Button buttonExit = new Button("End");

    private State0519 state0519 = DayState0519.getInstance();

    public SafeFrame(String title) {
        super(title);
        setBackground(Color.black);
        setLayout(new BorderLayout());

        add(textClock, BorderLayout.NORTH);
        textClock.setEditable(false);

        add(textScreen, BorderLayout.CENTER);
        textScreen.setEditable(false);

        Panel panel = new Panel();
        panel.add(buttonUse);
        panel.add(buttonAlarm);
        panel.add(buttonPhoto);
        panel.add(buttonExit);
        add(panel, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        buttonUse.addActionListener(this);
        buttonAlarm.addActionListener(this);
        buttonPhoto.addActionListener(this);
        buttonExit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.toString());

        if(e.getSource() == buttonUse) {
            state0519.useSafe(this);
        } else if(e.getSource() == buttonAlarm) {
            state0519.doAlarm(this);
        } else if(e.getSource() == buttonPhoto) {
            state0519.doPhoto(this);
        } else if(e.getSource() == buttonExit) {
            System.exit(0);
        } else {
            System.out.println("?");
        }
    }

    @Override
    public void setClock(int hour) {
        String clock = String.format("%02d:00", hour);
        textClock.setText(clock);
        state0519.doClock(this, hour);
    }

    @Override
    public void changeState(State0519 state) {
        System.out.println(this.state0519 + "에서" + state + "으로 상태가 변화했습니다.");
        this.state0519 = state; // 상태 전환
    }

    @Override
    public void callSecurityCenter(String msg) {
        textScreen.append("call" + msg + "\n");
    }

    @Override
    public void recordLog(String msg) {
        textScreen.append("record" + msg + "\n");
    }
}

interface Context {
    void setClock(int hour);
    void changeState(State0519 state);
    void callSecurityCenter(String msg);
    void recordLog(String msg);
}

public class StateEx2 {
    public static void main(String[] args) {
        System.out.println("Hello state");

        SafeFrame frame = new SafeFrame("state sample");
        while (true) {
            for(int hour = 0; hour < 24; hour++) {
                frame.setClock(hour);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

