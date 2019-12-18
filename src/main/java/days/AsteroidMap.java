package days;

import util.Point;

import java.util.*;
import java.util.stream.Collectors;

public class AsteroidMap {
    public char[][] map;
    public int xMax;
    public int yMax;
    private int indexX;
    private int indexY;

    public static List<Point> sortFrom90Degrees(Map<Double, Point> angles) {

        //wszystkie wieksze mniejsze od 90
        List<Map.Entry<Double, Point>> collect = angles.entrySet().stream().filter(e -> e.getKey() <= 90).collect(Collectors.toList());

        List<Point> sortedPointList = new ArrayList<>();

        collect.sort(Map.Entry.comparingByKey(Comparator.reverseOrder()));
        for (Map.Entry<Double, Point> doublePointEntry : collect) {
            sortedPointList.add(doublePointEntry.getValue());
        }
        //od 180 do 90
        collect = angles.entrySet().stream().filter(e -> e.getKey() > 90).collect(Collectors.toList());
        collect.sort(Map.Entry.comparingByKey(Comparator.reverseOrder()));
        for (Map.Entry<Double, Point> doublePointEntry : collect) {
            sortedPointList.add(doublePointEntry.getValue());
        }

        return sortedPointList;





    }

    public void initMap(String s) {
        xMax = s.indexOf('\n');
        yMax = count(s, '\n') + 1;
        map = new char[xMax][yMax];
        for (int i = 0; i < s.length(); i++) {
            addData(s.charAt(i));
        }
    }

    private int count(String s, char c) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }


    public void addData(char value) {

        if (value == '\n') {
            indexY++;
            indexX = 0;
        } else {
            map[indexX++][indexY] = value;
        }
    }

    public char at(int x, int y) {
        return map[x][y];
    }


    public int calculateLoSAsteroids(int xpos, int ypos) {
        if (at(xpos, ypos) != '#') {
            return -1;
        }
        Set<Double> angles = new HashSet<>();

        for (int y = 0; y < yMax; y++) {
            for (int x = 0; x < xMax; x++) {
                if (at(x, y) != '#') {
                    continue;
                }
                if (xpos == x && ypos == y) {
                    continue;
                }

                int dx = x - xpos;
                int dy = ypos - y;
                double angle = Math.toDegrees(Math.atan2(dy, dx));
                angles.add(angle);
            }
        }
        return angles.size();
    }
    public Map<Double, Point> calculateLoSAsteroidsAngles(int xpos, int ypos) {
        Map<Double,Point> angles = new HashMap<>();

        for (int y = 0; y < yMax; y++) {
            for (int x = 0; x < xMax; x++) {
                if (at(x, y) != '#') {
                    continue;
                }
                if (xpos == x && ypos == y) {
                    continue;
                }

                int dx = x - xpos;
                int dy = ypos - y;
                double angle = Math.toDegrees(Math.atan2(dy, dx));
                angles.put(angle, new Point(x, y));
            }
        }
        return angles;
    }


}
