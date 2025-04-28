package mini_project.card_game;

public class SettingTestEx1 {
    public static void main(String[] args) {
        System.out.println("SettingTestEx1");

        ConfigLoader config = new ConfigLoader("config.properties");

        System.out.println("앱 이름: " + config.get("app.name"));
        System.out.println("앱 버전: " + config.get("app.version"));
        System.out.println("DB 연결 URL: " + config.get("db.url"));

    }
}
