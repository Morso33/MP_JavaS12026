public class SwampTile implements Tile {

    public char getCharacter() {
        return 'S';
    }

    public String getType() {
        return "swamp";
    }

    public void action() {
        System.out.println("You sink into the swamp and lose a turn.");
    }
}
