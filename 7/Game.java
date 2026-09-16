import java.util.List;
import java.util.Scanner;

//Console loop runs here
public class Game {

    private final Scanner scanner;
    private GameCharacter character;

    public Game(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("=== Character development ===");
        this.character = new GameCharacter(askName());
        System.out.println();

        while (true) {
            printStatus();
            if (this.character.getState().isFinal()) {
                System.out.println(this.character.getName()
                        + " has mastered the art. The game is over.");
                return;
            }
            if (!this.character.isAlive()) {
                System.out.println(this.character.getName()
                        + " has run out of health points. The game is over.");
                return;
            }

            List<Action> actions = this.character.getAvailableActions();
            printActions(actions);
            Action chosen = askAction(actions);
            if (chosen == null) {
                System.out.println("The journey ends here. Goodbye!");
                return;
            }

            System.out.println();
            perform(chosen);
            System.out.println();
        }
    }

    private void perform(Action action) {
        switch (action) {
            case TRAIN:
                this.character.train();
                break;
            case MEDITATE:
                this.character.meditate();
                break;
            case FIGHT:
                this.character.fight();
                break;
            default:
                throw new IllegalArgumentException("Unknown action: " + action);
        }
    }

    private void printStatus() {
        System.out.println("-----------------------------------------------");
        System.out.println(this.character.getStatus());
    }

    private void printActions(List<Action> actions) {
        System.out.println("Available actions:");
        for (int i = 0; i < actions.size(); i++) {
            System.out.println("  " + (i + 1) + ") " + actions.get(i).getLabel());
        }
        System.out.println("  0) quit");
    }

    private String askName() {
        while (true) {
            System.out.print("Name your character: ");
            if (!this.scanner.hasNextLine()) {
                return "Hero"; // no input available, use a default name
            }
            String name = this.scanner.nextLine().trim();
            if (!name.isEmpty()) {
                return name;
            }
            System.out.println("The name cannot be empty.");
        }
    }

    private Action askAction(List<Action> actions) {
        while (true) {
            System.out.print("Choose an action: ");
            if (!this.scanner.hasNextLine()) {
                return null; // end of input, stop the game
            }
            String input = this.scanner.nextLine().trim();
            if (input.equalsIgnoreCase("quit") || input.equals("0")) {
                return null;
            }
            Action byName = findByLabel(actions, input);
            if (byName != null) {
                return byName;
            }
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= actions.size()) {
                    return actions.get(choice - 1);
                }
            } catch (NumberFormatException ignored) {
                // fall through to the error message below
            }
            System.out.println("Pick a number between 0 and " + actions.size() + ".");
        }
    }

    private Action findByLabel(List<Action> actions, String input) {
        for (Action action : actions) {
            if (action.getLabel().equalsIgnoreCase(input)) {
                return action;
            }
        }
        return null;
    }
}
