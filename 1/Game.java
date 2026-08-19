public class Game {

    public static void main(String[] args) {
        Game game = new Game();

        System.out.println("City map:");
        Map city = game.createMap(MapType.CITY, 10, 20);
        city.display();

        System.out.println();

        System.out.println("Wilderness map:");
        Map wilderness = game.createMap(MapType.WILDERNESS, 10, 20);
        wilderness.display();
    }

    public Map createMap(MapType type, int rows, int columns) {
        if (type == MapType.CITY) {
            return new CityMap(rows, columns);
        }
        return new WildernessMap(rows, columns);
    }
}
