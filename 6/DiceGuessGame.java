import java.util.Random;
import java.util.Scanner;

public class DiceGuessGame extends Game {

    private static final int ROUNDS_PER_PLAYER = 3;

    private final Scanner scanner;
    private final Random random = new Random();

    private Player[] players;
    private int turnsPlayed;

    public DiceGuessGame(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void initializeGame(int numberOfPlayers) {
        players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player("Player " + (i + 1));
        }
        turnsPlayed = 0;
    }

    @Override
    public boolean endOfGame() {
        return turnsPlayed >= players.length * ROUNDS_PER_PLAYER;
    }

    @Override
    public void playSingleTurn(int player) {
        Player currentPlayer = players[player];
        int guess = readGuess(currentPlayer);
        int roll = random.nextInt(6) + 1;

        System.out.println("The dice showed " + roll + ".");
        if (guess == roll) {
            currentPlayer.addPoint();
            System.out.println("Correct! " + currentPlayer.getName() + " scores a point.");
        } else {
            System.out.println("Wrong guess.");
        }
        System.out.println();

        turnsPlayed++;
    }

    private int readGuess(Player player) {
        int guess = 0;
        boolean validGuess = false;
        while (!validGuess) {
            System.out.print(player.getName() + ", guess a number between 1 and 6: ");
            if (scanner.hasNextInt()) {
                guess = scanner.nextInt();
                validGuess = guess >= 1 && guess <= 6;
            } else {
                scanner.next();
            }
            if (!validGuess) {
                System.out.println("Please enter a number between 1 and 6.");
            }
        }
        return guess;
    }

    @Override
    public void displayWinner() {
        Player winner = players[0];
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getScore() + " point(s)");
            if (player.getScore() > winner.getScore()) {
                winner = player;
            }
        }

        boolean tie = false;
        for (Player player : players) {
            if (player != winner && player.getScore() == winner.getScore()) {
                tie = true;
            }
        }

        if (tie) {
            System.out.println("The game ended in a tie!");
        } else {
            System.out.println("The winner is " + winner.getName() + "!");
        }
    }
}
