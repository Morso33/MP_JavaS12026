public class CityMap extends Map {

    public CityMap(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    public Tile createTile() {
        switch (random.nextInt(3)) {
            case 0:
                return new RoadTile();
            case 1:
                return new ForestTile();
            default:
                return new BuildingTile();
        }
    }
}
