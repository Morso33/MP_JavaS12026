import java.util.List;


public abstract class AbstractState implements CharacterState {

    public static final int TRAINING_EXPERIENCE = 20;
    public static final int MEDITATION_HEALTH = 15;
    public static final int FIGHT_EXPERIENCE = 40;
    public static final int FIGHT_HEALTH_COST = 25;

    @Override
    public void train(GameCharacter character) {
        if (!isAvailable(Action.TRAIN)) {
            refuse(character, Action.TRAIN);
            return;
        }
        character.gainExperience(TRAINING_EXPERIENCE);
        System.out.println(character.getName() + " trains hard and gains "
                + TRAINING_EXPERIENCE + " experience points.");
        advanceIfReady(character);
    }

    @Override
    public void meditate(GameCharacter character) {
        if (!isAvailable(Action.MEDITATE)) {
            refuse(character, Action.MEDITATE);
            return;
        }
        int restored = character.heal(MEDITATION_HEALTH);
        System.out.println(character.getName() + " meditates and restores "
                + restored + " health points.");
        advanceIfReady(character);
    }

    @Override
    public void fight(GameCharacter character) {
        if (!isAvailable(Action.FIGHT)) {
            refuse(character, Action.FIGHT);
            return;
        }
        character.takeDamage(FIGHT_HEALTH_COST);
        character.gainExperience(FIGHT_EXPERIENCE);
        System.out.println(character.getName() + " wins a fight: "
                + FIGHT_EXPERIENCE + " experience points gained, "
                + FIGHT_HEALTH_COST + " health points lost.");
        if (!character.isAlive()) {
            System.out.println(character.getName() + " collapses after the fight.");
            return;
        }
        advanceIfReady(character);
    }

    @Override
    public int getExperienceToAdvance() {
        return -1;
    }

    @Override
    public boolean isFinal() {
        return nextState() == null;
    }

    protected abstract CharacterState nextState();

    protected boolean isAvailable(Action action) {
        return getAvailableActions().contains(action);
    }


    protected void advanceIfReady(GameCharacter character) {
        CharacterState next = nextState();
        if (next == null || character.getExperience() < getExperienceToAdvance()) {
            return;
        }
        character.setState(next);
        System.out.println();
        System.out.println("*** " + character.getName() + " reaches the "
                + next.getLevelName() + " level! ***");
    }

    private void refuse(GameCharacter character, Action action) {
        System.out.println(character.getName() + " cannot " + action.getLabel()
                + " at the " + getLevelName() + " level.");
        System.out.println("Available actions: " + describeActions() + ".");
    }

    private String describeActions() {
        List<Action> actions = getAvailableActions();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < actions.size(); i++) {
            if (i > 0) {
                result.append(i == actions.size() - 1 ? " and " : ", ");
            }
            result.append(actions.get(i).getLabel());
        }
        return result.length() == 0 ? "none" : result.toString();
    }
}
