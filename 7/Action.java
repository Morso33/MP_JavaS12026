public enum Action {

    TRAIN("train"),
    MEDITATE("meditate"),
    FIGHT("fight");

    private final String label;

    Action(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
