public class ForestTile implements Tile {

    public char getCharacter() {
        return 'F';
    }

    public String getType() {
        return "forest";
    }

    public void action() {
        System.out.println("You make your way through the dense forest.");
    }
}
