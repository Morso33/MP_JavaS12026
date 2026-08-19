public class WildernessMap extends Map {

    public WildernessMap(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    public Tile createTile() {
        switch (random.nextInt(3)) {
            case 0:
                return new SwampTile();
            case 1:
                return new WaterTile();
            default:
                return new ForestTile();
        }
    }
}
