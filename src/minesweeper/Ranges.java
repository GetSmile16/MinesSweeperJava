package minesweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges {
    private static Coordinates size;
    private static ArrayList<Coordinates> allCoords;
    private static Random random = new Random();

    public static void setSize(Coordinates size_) {
        size = size_;
        allCoords = new ArrayList<Coordinates>();
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                allCoords.add(new Coordinates(x, y));
            }
        }
    }

    public static Coordinates getSize() {
        return size;
    }

    public static ArrayList<Coordinates> getAllCoords() {
        return allCoords;
    }

    static boolean inRange (Coordinates coord) {
        return coord.x >= 0 && coord.x < size.x && coord.y >= 0 && coord.y < size.y;
    }

    static Coordinates getRandomCoord() {
        return new Coordinates(random.nextInt(size.x), random.nextInt(size.y));
    }

    static ArrayList<Coordinates> getCoordsAround(Coordinates coord) {
        Coordinates aroundCoord;
        ArrayList<Coordinates> list = new ArrayList<Coordinates>();
        for (int x = coord.x - 1; x <= coord.x + 1; x++) {
            for (int y = coord.y - 1; y <= coord.y + 1; y++) {
                if (inRange(aroundCoord = new Coordinates(x, y)))
                    if (!aroundCoord.equals(coord))
                        list.add(aroundCoord);
            }
        }
        return list;
    }
}
