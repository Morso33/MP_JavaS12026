import java.util.List;


public interface CharacterState {

    void train(GameCharacter character);

    void meditate(GameCharacter character);

    void fight(GameCharacter character);

    String getLevelName();

    List<Action> getAvailableActions();

    int getExperienceToAdvance();

    boolean isFinal();
}
