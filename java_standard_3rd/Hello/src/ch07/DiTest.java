package ch07;

abstract class MessageSender {
    abstract void send(String msg);
}

class EmailSender extends MessageSender {
    void send(String msg) {
        System.out.println("이메일 전송: " + msg);
    }
}

class SmsSender extends MessageSender {
    void send(String msg) {
        System.out.println("sms 전송: " + msg);
    }
}

class NotificationService {
    private MessageSender sender;

    // 생성자 주입
    public NotificationService(MessageSender sender) {
        this.sender = sender;
    }

    void notify(String msg) {
        sender.send(msg);
    }
}


public class DiTest {
    public static void main(String[] args) {
        EmailSender emailSender = new EmailSender();
        NotificationService notificationService = new NotificationService(emailSender);
        notificationService.notify("안녕하세요!");

        SmsSender smsSender = new SmsSender();
        notificationService = new NotificationService(smsSender);
        notificationService.notify("안녕하세요!");

        System.out.println(smsSender);

        smsSender = null;
        System.out.println(smsSender);
    }
}
