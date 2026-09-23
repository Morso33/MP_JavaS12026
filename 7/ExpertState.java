import java.util.Arrays;
import java.util.List;

public class ExpertState extends AbstractState {

    public static final int EXPERIENCE_TO_MASTER = 500;

    @Override
    public String getLevelName() {
        return "expert";
    }

    @Override
    public List<Action> getAvailableActions() {
        return Arrays.asList(Action.TRAIN, Action.MEDITATE, Action.FIGHT);
    }

    @Override
    public int getExperienceToAdvance() {
        return EXPERIENCE_TO_MASTER;
    }

    @Override
    protected CharacterState nextState() {
        return new MasterState();
    }
}
