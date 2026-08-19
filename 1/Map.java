import java.util.Random;

public abstract class Map {

    protected final Random random = new Random();

    private final int rows;
    private final int columns;
    private final Tile[][] tiles;

    protected Map(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.tiles = new Tile[rows][columns];
        fill();
    }

    public abstract Tile createTile();

    public void display() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                System.out.print(tiles[row][column].getCharacter());
            }
            System.out.println();
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Tile getTile(int row, int column) {
        return tiles[row][column];
    }

    private void fill() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                tiles[row][column] = createTile();
            }
        }
    }
}
