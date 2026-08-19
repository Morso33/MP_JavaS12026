public class BuildingTile implements Tile {

    public char getCharacter() {
        return 'B';
    }

    public String getType() {
        return "building";
    }

    public void action() {
        System.out.println("You enter the building and look for supplies.");
    }
}
