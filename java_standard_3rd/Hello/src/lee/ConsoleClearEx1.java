package lee;

public class ConsoleClearEx1 {
    public static void main(String[] args) {

        System.out.println("Hello World");

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Hello World2");

        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {}

        System.out.println("Hello World3");
    }
}
