import java.util.Collections;
import java.util.List;

public class MasterState extends AbstractState {

    @Override
    public String getLevelName() {
        return "master";
    }

    @Override
    public List<Action> getAvailableActions() {
        return Collections.emptyList();
    }

    @Override
    protected CharacterState nextState() {
        return null;
    }
}
