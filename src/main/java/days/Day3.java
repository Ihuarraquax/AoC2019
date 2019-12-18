package days;

import java.util.*;
import java.util.List;

public class Day3 {


    private List<WirePoint> wire2Trace;
    private List<WirePoint> wire1Trace;
    private StringTokenizer wire1Tokenizer;
    private StringTokenizer wire2Tokenizer;

    void solve() {
        initData();
        addPointsToTrace(wire1Tokenizer, wire1Trace);
        addPointsToTrace(wire2Tokenizer, wire2Trace);

        List<WirePoint> intersectionPoints = new ArrayList<>(wire1Trace);
        intersectionPoints.retainAll(wire2Trace);

        int smallestDistance = Integer.MAX_VALUE;
        for (WirePoint intersectionPoint : intersectionPoints) {
            int distance = wire1Trace.indexOf(intersectionPoint) +1 + wire2Trace.indexOf(intersectionPoint)+1;
            if (distance < smallestDistance) {
                smallestDistance = distance;
            }
        }

        System.out.println(smallestDistance);

    }

    private void addPointsToTrace(StringTokenizer tokenizer, List<WirePoint> trace) {
        WirePoint currentPoint = new WirePoint(0, 0);
        while (tokenizer.hasMoreTokens()) {

            Command command = new Command(tokenizer.nextToken());

            int xMod;
            int yMod;
            switch (command.direction) {
                case 'U':
                    xMod = 0;
                    yMod = 1;
                    break;
                case 'R':
                    xMod = 1;
                    yMod = 0;
                    break;
                case 'D':
                    xMod = 0;
                    yMod = -1;
                    break;
                case 'L':
                    xMod = -1;
                    yMod = 0;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + command.direction);
            }

            for (int i = 0; i < command.distance; i++) {
                WirePoint wirePoint = new WirePoint(
                        currentPoint.x + xMod, currentPoint.y + yMod);
                trace.add(wirePoint);
                currentPoint.x = wirePoint.x;
                currentPoint.y = wirePoint.y;
            }

        }
    }

    private void initData() {
        System.out.println("wire1 data:");
        String wire1Data = Utils.getInputData();
        System.out.println("wire2 data:");
        String wire2Data = Utils.getInputData();
        wire1Trace = new ArrayList<>();
        wire2Trace = new ArrayList<>();

        wire1Tokenizer = new StringTokenizer(wire1Data, ",");
        wire2Tokenizer = new StringTokenizer(wire2Data, ",");
    }


    private class WirePoint {
        int x;
        int y;

        public WirePoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof WirePoint) {
                WirePoint pt = (WirePoint)obj;
                return (x == pt.x) && (y == pt.y);
            }
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private class Command {
        char direction;
        int distance;

        public Command(String s) {
            direction = s.charAt(0);
            distance = Integer.parseInt(s.substring(1));
        }
    }

    public static void main(String[] args) {
        Day3 day3 = new Day3();
        day3.solve();
    }
}
