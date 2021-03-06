package days;

import util.Point;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day10 {


    public static void main(String[] args) {

        AsteroidMap asteroidMap = new AsteroidMap();
        asteroidMap.initMap(map);

        Point station =findStation(asteroidMap);


        int i = 1;
        Map<Double, Point> angles = asteroidMap.calculateLoSAsteroidsAngles(station.x, station.y);
        while (angles.size() > 0) {

            angles = asteroidMap.calculateLoSAsteroidsAngles(station.x, station.y);

//            for (Map.Entry<Double, Point> keyValue : angles.entrySet()) {
//                System.out.println(keyValue.getKey() +" "+ keyValue.getValue());
//
//            }

            List<Point> sortedAsteroids = AsteroidMap.sortFrom90Degrees(angles);
            for (Point asteroid : sortedAsteroids) {
                System.out.println(i++ + " " + asteroid);
                asteroidMap.map[asteroid.x][asteroid.y] = '.';
            }

        }


    }

    private static Point findStation(AsteroidMap asteroidMap) {
        int mostLoS = -1;

        int bestX = -1;
        int bestY = -1;
        for (int y = 0; y < asteroidMap.yMax; y++) {
            for (int x = 0; x < asteroidMap.xMax; x++) {
                int decections = asteroidMap.calculateLoSAsteroids(x, y);
                if (decections > mostLoS) {
                    bestX = x;
                    bestY = y;
                    mostLoS = decections;
                }
            }
        }

        System.out.println("(" + bestX + "," + bestY + ") " + mostLoS);
        return new Point(bestX, bestY);
    }


//    static String map = ".#..##.###...#######\n" +
//            "##.############..##.\n" +
//            ".#.######.########.#\n" +
//            ".###.#######.####.#.\n" +
//            "#####.##.#.##.###.##\n" +
//            "..#####..#.#########\n" +
//            "####################\n" +
//            "#.####....###.#.#.##\n" +
//            "##.#################\n" +
//            "#####.##.###..####..\n" +
//            "..######..##.#######\n" +
//            "####.##.####...##..#\n" +
//            ".#####..#.######.###\n" +
//            "##...#.##########...\n" +
//            "#.##########.#######\n" +
//            ".####.#.###.###.#.##\n" +
//            "....##.##.###..#####\n" +
//            ".#.#.###########.###\n" +
//            "#.#.#.#####.####.###\n" +
//            "###.##.####.##.#..##";
//    static String map = ".#..#\n" +
//            ".....\n" +
//            "#####\n" +
//            "....#\n" +
//            "...##";
    static String map = ".............#..#.#......##........#..#\n" +
        ".#...##....#........##.#......#......#.\n" +
        "..#.#.#...#...#...##.#...#.............\n" +
        ".....##.................#.....##..#.#.#\n" +
        "......##...#.##......#..#.......#......\n" +
        "......#.....#....#.#..#..##....#.......\n" +
        "...................##.#..#.....#.....#.\n" +
        "#.....#.##.....#...##....#####....#.#..\n" +
        "..#.#..........#..##.......#.#...#....#\n" +
        "...#.#..#...#......#..........###.#....\n" +
        "##..##...#.#.......##....#.#..#...##...\n" +
        "..........#.#....#.#.#......#.....#....\n" +
        "....#.........#..#..##..#.##........#..\n" +
        "........#......###..............#.#....\n" +
        "...##.#...#.#.#......#........#........\n" +
        "......##.#.....#.#.....#..#.....#.#....\n" +
        "..#....#.###..#...##.#..##............#\n" +
        "...##..#...#.##.#.#....#.#.....#...#..#\n" +
        "......#............#.##..#..#....##....\n" +
        ".#.#.......#..#...###...........#.#.##.\n" +
        "........##........#.#...#.#......##....\n" +
        ".#.#........#......#..........#....#...\n" +
        "...............#...#........##..#.#....\n" +
        ".#......#....#.......#..#......#.......\n" +
        ".....#...#.#...#...#..###......#.##....\n" +
        ".#...#..##................##.#.........\n" +
        "..###...#.......#.##.#....#....#....#.#\n" +
        "...#..#.......###.............##.#.....\n" +
        "#..##....###.......##........#..#...#.#\n" +
        ".#......#...#...#.##......#..#.........\n" +
        "#...#.....#......#..##.............#...\n" +
        "...###.........###.###.#.....###.#.#...\n" +
        "#......#......#.#..#....#..#.....##.#..\n" +
        ".##....#.....#...#.##..#.#..##.......#.\n" +
        "..#........#.......##.##....#......#...\n" +
        "##............#....#.#.....#...........\n" +
        "........###.............##...#........#\n" +
        "#.........#.....#..##.#.#.#..#....#....\n" +
        "..............##.#.#.#...........#.....";
}
