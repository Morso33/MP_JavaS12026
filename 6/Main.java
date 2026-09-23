import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many players? ");
        int numberOfPlayers = scanner.nextInt();

        Game game = new DiceGuessGame(scanner);
        game.play(numberOfPlayers);
    }
}
