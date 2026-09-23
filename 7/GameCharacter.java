import java.util.List;


public class GameCharacter {

    public static final int MAX_HEALTH = 100;

    private final String name;
    private CharacterState state;
    private int experience;
    private int health;

    public GameCharacter(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("A character needs a name.");
        }
        this.name = name.trim();
        this.state = new NoviceState();
        this.experience = 0;
        this.health = MAX_HEALTH;
    }

    public void train() {
        this.state.train(this);
    }

    public void meditate() {
        this.state.meditate(this);
    }

    public void fight() {
        this.state.fight(this);
    }

    public void setState(CharacterState state) {
        if (state == null) {
            throw new IllegalArgumentException("A character must always have a level.");
        }
        this.state = state;
    }

    public CharacterState getState() {
        return this.state;
    }

    public void gainExperience(int points) {
        this.experience += Math.max(0, points);
    }

    public int heal(int points) {
        int restored = Math.min(Math.max(0, points), MAX_HEALTH - this.health);
        this.health += restored;
        return restored;
    }

    public void takeDamage(int points) {
        this.health = Math.max(0, this.health - Math.max(0, points));
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public String getName() {
        return this.name;
    }

    public int getExperience() {
        return this.experience;
    }

    public int getHealth() {
        return this.health;
    }

    public String getLevelName() {
        return this.state.getLevelName();
    }

    public List<Action> getAvailableActions() {
        return this.state.getAvailableActions();
    }

    public String getStatus() {
        StringBuilder status = new StringBuilder();
        status.append(this.name)
                .append(" | level: ").append(this.state.getLevelName())
                .append(" | experience: ").append(this.experience)
                .append(" | health: ").append(this.health).append("/").append(MAX_HEALTH);
        int required = this.state.getExperienceToAdvance();
        if (required > 0) {
            status.append(" | experience to next level: ")
                    .append(Math.max(0, required - this.experience));
        }
        return status.toString();
    }
}
