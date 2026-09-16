import java.util.Arrays;
import java.util.List;

public class IntermediateState extends AbstractState {

    public static final int EXPERIENCE_TO_EXPERT = 250;

    @Override
    public String getLevelName() {
        return "intermediate";
    }

    @Override
    public List<Action> getAvailableActions() {
        return Arrays.asList(Action.TRAIN, Action.MEDITATE);
    }

    @Override
    public int getExperienceToAdvance() {
        return EXPERIENCE_TO_EXPERT;
    }

    @Override
    protected CharacterState nextState() {
        return new ExpertState();
    }
}
