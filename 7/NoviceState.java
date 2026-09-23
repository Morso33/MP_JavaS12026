import java.util.Arrays;
import java.util.List;

public class NoviceState extends AbstractState {

    public static final int EXPERIENCE_TO_INTERMEDIATE = 100;

    @Override
    public String getLevelName() {
        return "novice";
    }

    @Override
    public List<Action> getAvailableActions() {
        return Arrays.asList(Action.TRAIN);
    }

    @Override
    public int getExperienceToAdvance() {
        return EXPERIENCE_TO_INTERMEDIATE;
    }

    @Override
    protected CharacterState nextState() {
        return new IntermediateState();
    }
}
