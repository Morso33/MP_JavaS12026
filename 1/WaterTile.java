public class WaterTile implements Tile {

    public char getCharacter() {
        return 'W';
    }

    public String getType() {
        return "water";
    }

    public void action() {
        System.out.println("You have to swim across the water.");
    }
}
