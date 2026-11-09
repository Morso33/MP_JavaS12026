package computerbuilder;

/** Component grade used by the preset builders to pick cheaper or better parts. */
public enum Grade {
    BUDGET("Budget"),
    STANDARD("Standard"),
    PREMIUM("Premium");

    private final String displayName;

    Grade(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
