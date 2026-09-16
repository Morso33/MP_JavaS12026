import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        new Game(scanner).run();
        scanner.close();
    }
}
