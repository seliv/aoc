package seliv.aoc.aoc2020;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aoc17 {
    public static void main(String[] args) {
        List<List<String>> in = InputParser.parseInput(IN2);
        List<String> ops = in.get(0);

        Map<Point, Boolean> board = new HashMap<>();

        for (int y = 0; y < ops.size(); y++) {
            String s = ops.get(y);
            for (int x = 0 ; x < s.length(); x++) {
                if (s.charAt(x) == '#') {
                    Point p = new Point(x, y, 0);
                    board.put(p, true);
                }
            }
        }

        for (int i = 0; i < 7; i++) {
            System.out.println("board.size() = " + board.size());
            board = doIter(board);
        }
    }

    public static Map<Point, Boolean> doIter(Map<Point, Boolean> board) {
        Map<Point, Boolean> newBorad = new HashMap<>();
        for (int z = -50; z < 50; z++) {
            for (int y = -50; y < 50; y++) {
                for (int x = -50; x < 50; x++) {
                    Point p = new Point(x, y, z);
                    int c = countNeighbors(board, x, y, z);
                    if (board.containsKey(p)) {
                        if ((c == 2) || (c == 3)) {
                            newBorad.put(p, true);
                        }
                    } else {
                        if (c == 3) {
                            newBorad.put(p, true);
                        }
                    }
                }
            }
        }
        return newBorad;
    }

    public static int countNeighbors(Map<Point, Boolean> board, int x, int y, int z) {
        int res = 0;
        for (int dz = - 1; dz <= 1; dz++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dx = - 1; dx <=1; dx++) {
                    if ((dx == 0) && (dy == 0) && (dz == 0)) {
                        continue;
                    }
                    if (board.containsKey(new Point(x + dx, y + dy, z + dz))) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    public static class Point {
        public final int x, y, z;

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            if (y != point.y) return false;
            return z == point.z;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            result = 31 * result + z;
            return result;
        }
    }

    public static String IN = ".#.\n" +
            "..#\n" +
            "###";

    public static String IN2 = ".##..#.#\n" +
            "#...##.#\n" +
            "##...#.#\n" +
            ".##.##..\n" +
            "...#.#.#\n" +
            ".##.#..#\n" +
            "...#..##\n" +
            "###..##.";

    public static String IN3 = "";

}
