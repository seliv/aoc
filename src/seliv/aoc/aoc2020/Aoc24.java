package seliv.aoc.aoc2020;

import javax.swing.text.StyledEditorKit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aoc24 {
    public static void main(String[] args) {
        List<List<String>> in = InputParser.parseInput(IN3);
        List<String> ops = in.get(0);

        Map<Point, Boolean> tiles = new HashMap<>();

        for (String seq : ops) {
            int x = 0;
            int y = 0;
            while (seq.length() > 0) {
                int ds;
                if (seq.startsWith("e")) {
                    x += 2;
                    ds = 1;
                } else if (seq.startsWith("se")) {
                    x += 1;
                    y -= 1;
                    ds = 2;
                } else if (seq.startsWith("ne")) {
                    x += 1;
                    y += 1;
                    ds = 2;
                } else if (seq.startsWith("w")) {
                    x -= 2;
                    ds = 1;
                } else if (seq.startsWith("sw")) {
                    y -= 1;
                    x -= 1;
                    ds = 2;
                } else if (seq.startsWith("nw")) {
                    y += 1;
                    x -= 1;
                    ds = 2;
                } else {
                    throw new IllegalArgumentException();
                }

                seq = seq.substring(ds);
            }

            Point p = new Point(x, y);
            if (tiles.containsKey(p)) {
                tiles.put(p, !tiles.get(p));
            } else {
                tiles.put(p, true);
            }

//            System.out.println("tiles = " + tiles);
        }

        int res = count(tiles);
        System.out.println("res = " + res);

        System.out.println("tiles = " + tiles);

        for (int m = 0; m < 100; m++) {
            Map<Point, Boolean> newTiles = new HashMap<>();

            for (int by = -1000; by < 1000; by += 2) {
                for (int bx = -1000; bx < 1000; bx += 2) {
                    step(tiles, newTiles, bx, by);
                    step(tiles, newTiles, bx + 1, by + 1);
                }
            }

            res = count(newTiles);
            System.out.println("res = " + res);
            tiles = newTiles;
        }
    }

    private static int count(Map<Point, Boolean> tiles) {
        int res = 0;
        for (Boolean value : tiles.values()) {
            if (value) {
                res++;
            }
        }
        return res;
    }

    static final int DX[] = new int[] {2, 1,  -1, -2, -1, 1};
    static final int DY[] = new int[] {0, -1, -1, 0,  1,  1};

    static void step(Map<Point, Boolean> tiles, Map<Point, Boolean> newTiles, int x, int y) {
        int cnt = 0;
        for (int i = 0; i < DX.length; i++) {
            int xx = x + DX[i];
            int yy = y + DY[i];

            if (tiles.getOrDefault(new Point(xx, yy), false)) {
                cnt++;
            }
        }

        final Point point = new Point(x, y);
        boolean it = tiles.getOrDefault(point, false);

        if (it) {
            if ((cnt == 0) || (cnt > 2)) {
                newTiles.put(point, false);
            } else {
                newTiles.put(point, true);
            }
        } else {
            if (cnt == 2) {
                newTiles.put(point, true);
            }
        }
    }

    static class Point {
        final int x;
        final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            return y == point.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public String toString() {
            return "[" + x + "," + y + "]";
        }
    }

    public static String IN = "sesenwnenenewseeswwswswwnenewsewsw\n" +
            "neeenesenwnwwswnenewnwwsewnenwseswesw\n" +
            "seswneswswsenwwnwse\n" +
            "nwnwneseeswswnenewneswwnewseswneseene\n" +
            "swweswneswnenwsewnwneneseenw\n" +
            "eesenwseswswnenwswnwnwsewwnwsene\n" +
            "sewnenenenesenwsewnenwwwse\n" +
            "wenwwweseeeweswwwnwwe\n" +
            "wsweesenenewnwwnwsenewsenwwsesesenwne\n" +
            "neeswseenwwswnwswswnw\n" +
            "nenwswwsewswnenenewsenwsenwnesesenew\n" +
            "enewnwewneswsewnwswenweswnenwsenwsw\n" +
            "sweneswneswneneenwnewenewwneswswnese\n" +
            "swwesenesewenwneswnwwneseswwne\n" +
            "enesenwswwswneneswsenwnewswseenwsese\n" +
            "wnwnesenesenenwwnenwsewesewsesesew\n" +
            "nenewswnwewswnenesenwnesewesw\n" +
            "eneswnwswnwsenenwnwnwwseeswneewsenese\n" +
            "neswnwewnwnwseenwseesewsenwsweewe\n" +
            "wseweeenwnesenwwwswnew";

    public static String IN2 = "";

    public static String IN3 = "nenwnwnwnwnewnwsenwnwnwsenwnwsenwnenww\n" +
            "eeeeeenewsweneeeeseeenwwnene\n" +
            "seeesesesenweeseewnw\n" +
            "nesesweneewseeseeseenwweeswesw\n" +
            "neeneeswneeeeeeeneeneeneweee\n" +
            "seseesenwsesesesesesesesenewseeseswsese\n" +
            "senwwwenwneswnwnwewswsewnesewesw\n" +
            "swswswswswnwseswwswswswswswswswsweswnw\n" +
            "neeneeweeeeseeneneeseeneneenenwe\n" +
            "seneswwnenewsewesenenenenenenenwnenenene\n" +
            "wwnwnenwsenwwnwwwwwwwwwwnww\n" +
            "seeeeneenesewseeswneeewnwseseswse\n" +
            "nwwwsenwnwwwwewwwwewnwwwwswnw\n" +
            "neneswenenenenenesweneneenewnwenenewne\n" +
            "nwwwneseswswnwesenwnesewswwsenwseww\n" +
            "nwwwnwnwnwnwnwwnwnwsewnwnwnw\n" +
            "nwsewsenenenwnweswnwnenwenenwswnwnwnwnw\n" +
            "nwnenwneewnwneswwnwnenwnwnwnwnwnwnwese\n" +
            "nenwneneenwnewnwnwnenw\n" +
            "seswswseswwseneswswsenwseswseswswe\n" +
            "swseswwswswswswswnwswswswswwnwseswswsww\n" +
            "wswseswswseswswsweswswneeseswwnwswnesw\n" +
            "nenenenwnwnwnenwnesenwnwnwnwnewnenenwnw\n" +
            "seneswnenwnenwnenewneseenwneneswnwnenenwne\n" +
            "nwnwnwnwnwnwnwswnwnwenwnwnwnwnwnwnwnww\n" +
            "nwweenweswneneneneneneeseeesewnene\n" +
            "nwswswswswswsweswswseswswswswswswswswsw\n" +
            "eeeenwneneeswneeeeneneneneeeneew\n" +
            "swwsewwwnwswnwwenewswswwseswswnesw\n" +
            "eswseswneneseswswwsenwseswswsesw\n" +
            "wewnewwwswswewsewneswswswswwwsw\n" +
            "eneeneeneneeneweenewneneeenenesw\n" +
            "enweneeesesesesesewneswneswesesewse\n" +
            "nwswswswwwswwswswwwswwswswswewsw\n" +
            "eeseweseseswseneseeseseeseseesee\n" +
            "nwswswswswswswswswnenewwswswwsenweswsw\n" +
            "wwwnwsesewnenewswsenenewww\n" +
            "neneneneneneneneeswnenwneneneswnwnwnenwnw\n" +
            "nenwnenwnwneswswswsewnenwnwneeswswnww\n" +
            "ewswswnewnwswwswswswswnesweswnwsesesw\n" +
            "nweenwnwnwswwnwnwnwnwwswnwnwnwenwswnwnw\n" +
            "senenwneneneeswneeeswnewenwnewswne\n" +
            "swsesweswnwwswswswswsweseseseswswwsene\n" +
            "swswweswnwwswnwwnwswwseewwwwsw\n" +
            "ewenwswwwwwwwwnwsewnwnenwsenww\n" +
            "eneenesenwswnenwnwnwneneneseseeswenwsw\n" +
            "sesesesenewnwnewsewseesewseseseesene\n" +
            "neeweneseeeeeswenweeneeswee\n" +
            "nwseseeeseeeeeseseewseweesenwe\n" +
            "eenweeneneeeeneseee\n" +
            "nwnwnwnwnwswnwnwnwnenwenwnwnwnwnwwswnw\n" +
            "sesenenwweseseeseesweeeneeseeese\n" +
            "nwenenenwnenwnenwnwsenwwnwsenwneseswsw\n" +
            "enwneneenwseswwneswwnwneswneseswwe\n" +
            "swswswswswwswseeswswswwneseseswswsese\n" +
            "nwnwnwenwneswnwnenenwwnwnwnwnenwnwnwne\n" +
            "nwwnenwewswswswswewswsweswenwswnwsw\n" +
            "nwwnesweswwswswwwwwewwwswwsww\n" +
            "sweeseeeeeeeenweeeeenwesw\n" +
            "wsewnewwwwnwwwwswwwwnew\n" +
            "wewwwswnewsesenewswswwwwwww\n" +
            "eeewneneeeeeeeseswesweseesee\n" +
            "nwwnwnenwneneswseneswsenwsenewenesenene\n" +
            "nwnwnwnwsenwnenewnwnwnwnenwnwnwsenenwnene\n" +
            "swswswswenwswseseeswnwswnwne\n" +
            "wesenwnwneenwnwnwnwnwnwswnwwnwnwnwsww\n" +
            "eeeweeeneenwneeesweseeeeee\n" +
            "neeswneswnewswswswnenenenwnenenwnenenenw\n" +
            "nwnwnenwnwnenwnenwwenwnwswwnwnwnwsenwe\n" +
            "wwwnwwwwwwwewwewwnwwwwnw\n" +
            "wnweswswwwwnwnwnwwnwnwwnewswnenwnw\n" +
            "nwsenwnwenwsewnwnwnenwnewnwenewnenwswnw\n" +
            "swnwsewweseswwsesesewnenesenesenesw\n" +
            "swwswswswsweswswswswswswswsw\n" +
            "nenewswneswneneneneneneneseneswnenenene\n" +
            "eswnwswswseswswswnwseswseeswseswseswsesew\n" +
            "newnewneneseeweeee\n" +
            "senwwseeseswseseseeseswswswswseswseswse\n" +
            "wsenenewneneweneeneseneneneeneenee\n" +
            "eneseseswsesewswseseseseew\n" +
            "wweneswwswswwwsewwswswwnwswwwsw\n" +
            "eeseeeewsweweseeeeenwewe\n" +
            "nwnewnwnwewswenenwnenwenwswnwneswnw\n" +
            "nenwseswnesesesweseseseswenwseewsesee\n" +
            "nwnewwseswwsenwnwneewnesenenwesenw\n" +
            "wnenenwnewneseneneneswswnesenwnwneenenene\n" +
            "esesesenwswseseseeeseeswsenwe\n" +
            "swswswswswswwswneswswwswwswswsww\n" +
            "neswsweswneneeeswwswwnwenwseswnenenene\n" +
            "nwswnenenenenwnenenwneneneneneswneenwnw\n" +
            "eesenweneeseseeeeswseeweesenw\n" +
            "swneneswnwenenwnwsenwnwnwnwnenwnwnwenw\n" +
            "nwnwsenwwnwnwsenwnewwwnwnwnwnwnw\n" +
            "nwseseneeewsenwnenenwneseswsweseswesw\n" +
            "nwnwnesenwnwnwwnwnwnwswnwnwnwnenwnenwne\n" +
            "eswswnewseswsenweswswswnesenewswswsw\n" +
            "eeneswneeeweneseswenenwnee\n" +
            "nenenwneseswwnwneneewnwenwswwswese\n" +
            "sewwwnwwwwwenwswwwwwwwwnw\n" +
            "eeewnweeneseeesweeeeweewswe\n" +
            "newsewswneeseswseseeseeseenesenwe\n" +
            "nwnwnwenwswneswsenwnwnwwnwnenenwswnwnwe\n" +
            "swnwswswswneswsesweswsewswseswswswseswnwsw\n" +
            "nwwwnwwsewwnweneswwwnwwewwww\n" +
            "wwswnwwwewnwwwsewnwnenwnwwwww\n" +
            "swswsweswswswswswneswneswswseswsesew\n" +
            "seeeeeeeeeeeeeeeenwswee\n" +
            "swseswseneswswswwswswswsesesesewneswsw\n" +
            "sesenwwnwseewnenwsee\n" +
            "seseeseseeseseeswseeesesesenwwsee\n" +
            "wseswsenenesweenwwswwnwwswnwnwenw\n" +
            "eneeeeeewenwweseseseeeneswese\n" +
            "swswseswseseswwseneseswswsweswsesesw\n" +
            "enwswsenwwnwnwnwsewwnwwewwnwnwwsw\n" +
            "nenwnwneneswnwnwwnwnwsenwwnwnenwsenwne\n" +
            "eseseeeneneeeeeeweeewesesew\n" +
            "seseswseeseswnwseswsesenwswseswnwseesenese\n" +
            "swswwsenwnewswsewsewsenwswwwnenenwse\n" +
            "nwswwswswwwswswswswswswswswseswnwesw\n" +
            "swwnwnwwnwsenwnenwenwwwnwnwnwnwnenwnww\n" +
            "neeneneneswnwneneenenenenwnenenwneneneswne\n" +
            "swneeswnenenenenenwnwswswnwewsenenene\n" +
            "seweweswseswswnenewnewswswnewenwne\n" +
            "wsewwwweswwnewwwsewswnewwew\n" +
            "newnwnwnwnwnwnwnwnwnwnwnwnwnwnwenw\n" +
            "swsenwswseseseseswnwswswswswsweswsesesese\n" +
            "nwnwnwwnwwwwnwwnwwwwse\n" +
            "nwnenwenenenwwnwnwnwnenenenenwnewene\n" +
            "sweseswnwnwswwsweswswswseseswswnwswsene\n" +
            "swwnewnweswewweneswesewenwswsw\n" +
            "nweeneswnwswswsesesesewwe\n" +
            "weswwnwswswsenwsewwswneswwswwww\n" +
            "nwnwnwneswnwnwnwnwnw\n" +
            "swswswswswswswswenwswswswswswnewswswswsw\n" +
            "nwnenwnwnwswswneneswsenwnwnenwnwnwswnenwnw\n" +
            "seseeseseswsenwseeseseneseseseseenwse\n" +
            "neenwnwnwwnwnwswnwnw\n" +
            "sesesewsesesesesesesesenesesewsesene\n" +
            "eeeneeeneneeenesweeenweeenesw\n" +
            "swsesesesenwswesweseswnwswsenwswseswsw\n" +
            "eseseseswseeeeswneesesesenweseeewe\n" +
            "neseeeeenweeeeneeeeneeneesenw\n" +
            "seeseseseseseseswswsenwsesewsesesesese\n" +
            "wswewwwwwewwswswwwwwwww\n" +
            "nwnwnwnwnwneenwnwnwsenenwnwnwsenwwnwnw\n" +
            "wwsewwwwwwnewnwwwwnwnwwwnw\n" +
            "swswseswswswswswsweswswswswwsweswswswnw\n" +
            "nenwnwnenwnenwnwneswnwnenwnwnwnwsenenesw\n" +
            "eweeseesweeeenwnwseswneeswnwenw\n" +
            "nenenewnenwnenwesenenenenenwne\n" +
            "nwenwsesenwseneeseneesenwseswwswsww\n" +
            "swswsenwnenwswswswseswwenenenwswswsese\n" +
            "wnewsewwnwwwwwwwwwnww\n" +
            "nwnenwnwnenenwnwnwnwnwnenwnwnwsenwnwnww\n" +
            "wwwneswwsenwwsenwwweewnwwnwnwe\n" +
            "wnewsewwnwwwswwenenwwnwsew\n" +
            "nwseswwsenwnwswnwneenewweneswwswne\n" +
            "weweeseseweswnwene\n" +
            "nwnwsewnwwnwnwwwwnwnwnwnwnwnwnw\n" +
            "enwseseswswswswswneswwswneswnewswswwsw\n" +
            "seswwenwnwwwwwsenwwwwnwswnewnwwnw\n" +
            "wnwwnwwwsenewnenwnwswwwwwnwwnwnw\n" +
            "neneneenewneenenesewneeeneneeene\n" +
            "wneswnwwwwwswwwwwseeseewwsw\n" +
            "neswnenenenwneneneenenenenwnwnwnenenene\n" +
            "nwnwnwnwsenwswnwnwnwnwnwnwnwnwnwenwnwnw\n" +
            "nenwseseneweseneneswwwseswsewswsesese\n" +
            "esesweseeeseeseseeeseeenwseese\n" +
            "sweneseswseseseseeseneseseesesesesee\n" +
            "nwnwnwswneneneneenwnenwnenenwnwnenw\n" +
            "nwwwswewsesewneww\n" +
            "wwwwwnewswwwswwwwwewwwwsw\n" +
            "sewwwneswswwsewnwnwwewwwwwe\n" +
            "nwnwnwnwnwnwnwnwnenwenwswnwnwnwnwnwnwnw\n" +
            "swswswswwswswnwswswwwswswswseswneswse\n" +
            "senewseseseswsewneswseweseseesesene\n" +
            "swseswswswneeswswswswseswswnwswswwswsw\n" +
            "nwesweenewsweneeneenesewneseee\n" +
            "eswseseseesenwseeenwnwseeseseesee\n" +
            "nwnewsenwwwwwwnewwwsenwnwnwww\n" +
            "eneneneneweneewenenene\n" +
            "senwnwnwnwnwnwnwnwnww\n" +
            "eneeeeneneeeeeeseeneeenewne\n" +
            "eseswswswswnwswswswswnwswseneswswswsww\n" +
            "nwwnwwnwnwnwnwnwsenwwenwnwnwnwsenwnw\n" +
            "wswsweswswswswwswswwwsenewswswwsww\n" +
            "enenewneneeeenenewsenewsenenene\n" +
            "eseeeeeseeeseeeesenwsweeese\n" +
            "eneeswwsewneseeeneseseeneewwee\n" +
            "swseseneswnwswswwswswswswswseswsweswswsese\n" +
            "neesewesesenwewneeeeneeneeeenwsw\n" +
            "eswwseenweseenweeeseeeeseee\n" +
            "wswseswneseneeswwnwsewse\n" +
            "sweswswswwseneswswneswewnwswneseseswsw\n" +
            "sesenwseseeseseseeenwseseeswnwsesee\n" +
            "seseswnwswseswswswswneseseswswseseswswsesw\n" +
            "nenweswwseeneeneneenenenesenenenenwnene\n" +
            "nwnwnwnwnwnwnwnwnwnwnwnwenwnwnwnwnwnwsw\n" +
            "swswwwwwswwneseswswwswwwwwswew\n" +
            "nwnenwnwnwwsenenwsenwwnwnenwenwnenwnwnwnw\n" +
            "wsewnwnwnenwnwnwnwnwnwsenwnwnwnwnwnenw\n" +
            "nenenenenwneeeeeeneeneeneeneseswe\n" +
            "wnenenenwnewnwnenenwnenenwseenenesenwne\n" +
            "nesenwsewnenwswenwneesewswnwswnesww\n" +
            "wwnwenwewswswwnwwseswnesweswnww\n" +
            "neneneenwneeneneneneswnenwneneneswneswne\n" +
            "nwnwswnwenwnwwewnwnweswnwnwwwnwwsww\n" +
            "eeeeeweeeneeeseseseeeesee\n" +
            "wwwwwswnewwwneswsewwwwwww\n" +
            "nweeswwseeseeenweeeeenene\n" +
            "nwnwnwnenenwnwnwnwsenwnwnwnwnww\n" +
            "seweeeseseseseeseeseeeeewesese\n" +
            "wseewseneseeseseesesese\n" +
            "senenwnwswnwnwswnenwnwnweswwwnwnwnene\n" +
            "swseseseesesesesewseseseenesesesewsese\n" +
            "seswswewseswswneswse\n" +
            "nenwnwnwnwnwnwenwnenenwswnenwnwswnwnwnwnw\n" +
            "neneeswwswnenwnwenwnenewnwnwnwnesewe\n" +
            "wnwnwswwnweeenenwsenwseenwnwwswnw\n" +
            "eeseseseeeeeeseeseneesewswnwse\n" +
            "neswseswewswsweswwswseswswwswswnwesw\n" +
            "nwwnwewsewnesewnwwsewww\n" +
            "weseeneneneeenesenweesenwwenenenee\n" +
            "nwwnwwenwnwnwswwwnwwnwnewnwnwswnww\n" +
            "nwwnwnwnwnwnwenwnwsenwnenwwnwnwnw\n" +
            "wsewseesenwneswnenenwenesewwwsww\n" +
            "swsesesesesesesenwwnese\n" +
            "newswewenenesewsenwweesweseenenw\n" +
            "nwnwnwnwnwnwnwnwsenwnwnwnwnwnw\n" +
            "swsweeswswseeneswswswnwwwswwnenwswsww\n" +
            "neneeneswneneneewnesenenenesenwnenw\n" +
            "nwnesenwnwnwnwnwnenwnwnw\n" +
            "nwnwswnwwwsewnwnwwsenenwwnenwnwswnwne\n" +
            "nwenwseseseeeeswsesesesesenwseweese\n" +
            "nwnwnwnwnwnwsenwnwwnwnwnwwnwnwnwnwnw\n" +
            "seneweseseseswnwswesesewseneseseswnew\n" +
            "eeeseseeeeeeseeseswnweeene\n" +
            "swnwseeeswnwseeseeseseesenw\n" +
            "swswswswsesweseswswseseswswseswswswnwsenew\n" +
            "swseseseswneneswswseswewsesweswswswnw\n" +
            "ewwswnewwwwnwswnwwwenwwwww\n" +
            "newneneeneseswwwseswwnenweenwseene\n" +
            "nwseswsesweseswswseswswnwnwswswseeseswsw\n" +
            "eeneeswneneeneseeeweenenenwnee\n" +
            "wsesesewswseeseseseseswseseswsesesenwne\n" +
            "swswnewewswsewnewwwnewwnwwnwew\n" +
            "swseswswneswwnwswneeww\n" +
            "sweswneeeeewneseenwneew\n" +
            "nwnwnwwwenesenwnwwnwnwswsenwenwswnwe\n" +
            "nwneenenwwsenenenenenwswsenenwneswnwnenew\n" +
            "wwwwsewswwwwnewwwwwwwnwww\n" +
            "wneneneeneseneeeeeneneneneneneene\n" +
            "seseeseeseeeseeesweseeesewnwew\n" +
            "seseeeswnwseeseseewseseeseseseeseese\n" +
            "seswsesesesenwsenwseseseswsesesesesesesese\n" +
            "swsweswswswswswnwswswswswswswneswswswsw\n" +
            "eeeeeweseeee\n" +
            "eewseseeeseeeseseeswsenesesesesenw\n" +
            "sewsewneseswseseweseneseeenwsewnwse\n" +
            "wnwwwwnwnenwwnwnwnwnwwswwnwwewnw\n" +
            "eeseseeewsesweeseneneseeeseee\n" +
            "neneseswseeeewneeeeseseswwseswee\n" +
            "neneeswenenweeneneenenenesewneeneene\n" +
            "swnwnenewwswseswwswsw\n" +
            "seseseswsesesesesesesesesenewwsenesese\n" +
            "nenesenesewesewsewneswseswneneswswse\n" +
            "neswswswswswswswneswseswwseswsenwneseswsw\n" +
            "neeeneeneneneneswnenwswneneneswnwenene\n" +
            "wwwwswwwnewwwswnwnwswseswwwesw\n" +
            "swneenwneeneewnenenenenewneswnenene\n" +
            "swswseseswswswseseneeswnwsesenwswswsese\n" +
            "senesenwsenenewnenwenwsenenwswenwnwwne\n" +
            "seseenwseenwseswseesesesesesesesesese\n" +
            "senwnwswnwnwswnwnenwwseeneneeswswnwnwne\n" +
            "nwwwnwnwwnwnwwewesewnwwwwwnw\n" +
            "enwnenweeseswnweewseneesweneeene\n" +
            "swswseseswsenwseseseeeswseseswswswnwsewse\n" +
            "nwwnwnwnenwseseswnwnwswnwne\n" +
            "senewseseseseeseseseseseeseeseseese\n" +
            "swswnwnwnwnenwnwnwnenwnwnwnenwwnenwsenwnwnw\n" +
            "neeneeneweeneeenweneseeeseswne\n" +
            "senenwnwnwnwnwnenenenesw\n" +
            "swswswwswneswnenwsesesweneswwswswswnew\n" +
            "nesewseeeseseseseesesee\n" +
            "eeeswnweweeeneeneneneeneeneee\n" +
            "senenenwnwnwswnwenenenwnwnenwnenene\n" +
            "senwnwnwnwwnwnwnwnwnwnwnwsewnwnwwnwnwnw\n" +
            "seseswseseseswseswwwseseseswsesweneswsese\n" +
            "eswnwsenwswswnwenenesesewwswnwnewe\n" +
            "eneneeseneeeeeeeseweeneneswenwnw\n" +
            "swwnwwswswnwswwseweswswweeswswsw\n" +
            "wswnwnewnwswweewsewnewnwnwseswsese\n" +
            "wwswwwswwswswnewswwnewswswswseswsww\n" +
            "sesesenesesesenwsewseseseseseewesesese\n" +
            "swneseswseswseswswswswswwswseseenewsese\n" +
            "ewswneseneswnwsesenwsewwnwenesewnew\n" +
            "eswnenwswnesweenwnwnwnwnenewneswsene\n" +
            "neweeeeneseeeeeeenewnenene\n" +
            "seseseseseeseseseseseseseesesesewwsene\n" +
            "swswswswswswswswswswswswswwswswswswewne\n" +
            "neenenewnwnenenenesenwneneswnwnee\n" +
            "nwewnwewsewswenewwseswswnwneneswne\n" +
            "swswseswneneseeswswseswseneseseswsewnw\n" +
            "wneneneenenenwnenwwnenenenenwneneene\n" +
            "weswnwnenwwsewswwwne\n" +
            "nwnenwnwnwnwswnwnwenwnweswnwnwsenwnwnwswnw\n" +
            "wnwwsenwswwnwnwnwenwwswnwwnwsenwwnwe\n" +
            "nwseeseswswseswseseswnewwseneseswseswsene\n" +
            "wwnwnenwwwswnwwwwweswnwwwwese\n" +
            "swseswswswswweneswswnenwseswneswnwswsw\n" +
            "ewswneeeseesewseeeseseeenewe\n" +
            "seswneseseswwswseseseseswseseswseseswsw\n" +
            "neneweneeeeesweneeenee\n" +
            "seeeseneweswnwwenweneenenwnesesw\n" +
            "eeeeeeeewesesesesesesesesesewse\n" +
            "newwenesesewseseseseswseswsesesesesese\n" +
            "eeseeseeweeneewseeeeeeeeee\n" +
            "swswwswwswswwswwwseweneneswswsesww\n" +
            "eeeweeeeseeeeeeeenweswe\n" +
            "wnwwwnwenwwwneseeseenwwnw\n" +
            "eewewesweneenenweewsweseweene\n" +
            "enwseswswnweswwswseneeenenewsenese\n" +
            "sweseswneseswswswswswswseswseeseswswnwswnw\n" +
            "eneswswnewwnwnwwnwsenwwee\n" +
            "swwwnwnwwnwwsewsewnwnwnewwnwenw\n" +
            "wenenwnenenwnenwneneneneneenenwwnwne\n" +
            "wnewwnwwsenwwnwwnwnwswnwwnwnwwwnw\n" +
            "wnwwwwwseeeneswnwnwnenesenwswsewsew\n" +
            "neneneneswnenenenenenenwnwnenesenenenenwne\n" +
            "nwwwwwwwswwwenww\n" +
            "nwseswwswnenenesenewesenw\n" +
            "swnwnwnwesweneseswnenenwnwnenenenenenw\n" +
            "eswwswswswwneswswwswwwewneswwswse\n" +
            "swneeseswnwswseswnwseseweeseenwswsew\n" +
            "wnwnenenenenwsenenenenenenenwnenene\n" +
            "neneseneeneneneneneneneneneeneenwswene\n" +
            "eenwenesenewsenenwneswewsw\n" +
            "swseseswseseeswneseswswswswswsesesenwsw\n" +
            "neeeneneneneneneneneneneneswnenenenewne\n" +
            "wnwnwnwwwswnwwnwnwwwnwnwnwnwnwenenw\n" +
            "neeswneewseeneeneneneneneneneneeeene\n" +
            "wswwwsewwwwwwwwwnwwweww\n" +
            "seseseswenwnwseesesesewseswnwsesesese\n" +
            "newneseeeseneneseswseewswenwseeese\n" +
            "wnwwwwnwwwenwnwswnwwnwwwswneww\n" +
            "nwneeeeeesenenweeeeeseeneesewe\n" +
            "nwnwnwnwnwnwenwwnwnwnwnwsenwnwnwnwnww\n" +
            "eseesewsewneeseneswneseneseesew\n" +
            "wseswenwnenewnwwswwewsesewwwsee\n" +
            "wsweseswsenwnwnwesesewseneseseseseese\n" +
            "wseswswseneneewswseseseseseswswsesese\n" +
            "swseswneswseseswseeswseswwswwswewswne\n" +
            "esweeeneeeeeseneeeeenwwee\n" +
            "wswswewnwswswswswnwswswswswe\n" +
            "nwweneswewwneneeeeneeweeeeee\n" +
            "sesesesenweswseseenwseseeeswsenwsese\n" +
            "neesewsenenweswsw\n" +
            "wswwswwseswweswswwwwnewwwwsww\n" +
            "swneswswswswswswswswnewswswseswswswswswsw\n" +
            "eweswnwnwwewswwswswswwswwswnwwswsw\n" +
            "nwsesesesweseseseseseseenwewnesenwsesesw\n" +
            "wwswswswewweswswswwwenewswwsww\n" +
            "seeeeweeeeeesweseeneeeee\n" +
            "esweeeseseeeseweseneseeeeese\n" +
            "wwswweswwwswswswwswwwwswww\n" +
            "neswseneseewnweenwnenenwneseswenwenwse\n" +
            "esenweeeseeeeswnwesweenwsenwe\n" +
            "swnwsenwnwnwnwnenwnwenwsenwwnenenw\n" +
            "swnwsenwnwsewsenwnenwnwnwnwe\n" +
            "neneweesweneneewenenewwsesew\n" +
            "nwnwwnwnwnwnwwnwwwewswnwnwnwnw\n" +
            "nwwnwwswwwnwenwwwnwnwwenwnwwnwnwnw\n" +
            "wseseswneeneweseseswswswsenwnwsesew\n" +
            "wwswswwwwswswwwnwswewswwwwne\n" +
            "eeeeeeeeesweeenweee\n" +
            "ewnwnwnwnwwnwnwseswnwnwnwenwsenwnwnw\n" +
            "nwnenweswnenwnwswenenwswnwesenwnesw\n" +
            "nwwnwwnwnesesenwsenwnwnwneenwenwswnw\n" +
            "senwneneweswenwswnwnwwwnwwsenwnwne\n" +
            "eseeswseeeenwsenwe\n" +
            "sewsesweseseeesenweseeneseseseseese\n" +
            "nenwnenesesenwneneesweneeenewnenee\n" +
            "seswswswswwwswwwnewwswwwwwww\n" +
            "swswwswnwswswneswwwswewswswswsewsw\n" +
            "nwwwswnwenwswnwsenwnwwwwewnwseneene\n" +
            "swwwneswnwewnwwwseswwwseswwsw\n" +
            "nwwenwnwnwsewnwwwnwnewnwwwwnwnwew\n" +
            "nwsenwsenewenwnwnewnwnwsenwswnwnwnwwnw\n" +
            "seswseseseswseswseseseeseesesesenwsewsenw\n" +
            "neswnwswnwewswswswwewnewwesweww\n" +
            "nwnenwnenwnenwnwnwnenwsenenwsenwnwnwnenwne\n" +
            "seswswwwnwwswwwswwnwswnewwwewww\n" +
            "nenenenenenenenenenenenwswnenenenenenenesew\n" +
            "neswswswswswswswswswswswswswenwswswswsw\n" +
            "sesweeeneeseesweeneeeeee\n" +
            "nwwswwswswwneenwswwneswnwswswswee\n" +
            "sewseesesenwseseseseesesesesesw\n" +
            "neenenesweweenweeeneneneswe\n" +
            "nwwwwneswwwwwsewwnenwswwnewwnw\n" +
            "eeeswnenweeeswswneeeneneswneene\n" +
            "sewnwnwseneneswneseswsesewswnwsww\n" +
            "enwnwswnenwswwsenwsenwnwnenwnwnwswnenwnw\n" +
            "neeeswneeeenwnewnweneeneneeseese\n" +
            "swsesewnwswnesewwwwswwwwwwneww\n" +
            "swswswwswswseswneswwwwswswswwsw\n" +
            "sewnewwwwwnewwwwwwswswwww\n" +
            "nenwenwnwneneswenwseseeeewneswnee\n" +
            "swwewnwwwnwsesenenesenwsweneswnenwnww\n" +
            "eeeseeneeeeeeweneeeeeene\n" +
            "swseseeswseswnwswsewswneseseswsesesese\n" +
            "nwseeweweneenweseeesweeeee\n" +
            "eeeeeneneneneneneeneeswwnewne\n" +
            "wwwwsewsweswswweswswnwwwswswswsw\n" +
            "nwswnwnenwwnwwnwnwnw\n" +
            "eeesenweeeseeeenwsweeesesese\n" +
            "enwnwseenwnwnwnwnwnwnwnenwnenenenwwnwsw\n" +
            "sewswswnewneswswneswswswseswswswswswswswsw\n" +
            "nwnenwnenwnwnwnenwnwnwnwnenwnwsenwnwnw\n" +
            "nwswswneswswwwswneeswwnenwnewwswswsese\n" +
            "sweswneswswwwswswswswwwswswswswswsw\n" +
            "wnwnwenwnewnwswwnwwswwwnwwenwese\n" +
            "swseneswswswswwswswswswneswswswswswswswsw\n" +
            "nwseeswnwnwsenwwswnwseenwnenwnwwe\n" +
            "wwwsewwnwwnewnwwwwwwwwww\n" +
            "sesweseeseeeseeseneenenwsewswsesese\n" +
            "wnwswnwwnwnwwnwwnwwnwnwnewswnenewsw\n" +
            "nenwnwnwnwnwnenwsenwnwnwnwnwnwnwnwnwnww\n" +
            "wnwnenwswnwwwnwwesesew\n" +
            "nwnenenenwnenenenwswnwnenwnenwneenwnene\n" +
            "eswnwwswswseseswswnwseswneswswsw\n" +
            "nwnwnenwwnenesenwnenenwnwneenenww\n" +
            "nwwwneneneenenwnwnenwneswneneneeneene\n" +
            "seseneneswwnwenewwswenewsenesewesww\n" +
            "neenewnenewneneneneseneneeeneneene\n" +
            "wwwwnenwwwnwwwwnwwwwsewwew\n" +
            "neswnwswnwseeseeswnweseneenwsesenew\n" +
            "nwnenwneenwnwnenwnwnenwswnwnenwnenwswnwnwe\n" +
            "nenewseswwwswswswswswswse\n" +
            "seswsenesesesewseswneswseeseseswseseswnw\n" +
            "wnwsewseswsewwwwnwwwwwwwenw\n" +
            "swwswwswswswsweeswswswswswswww\n" +
            "swnwnwnwnwwnwnwnwwwnwnwnwnwenwwnewsw\n" +
            "wweeneeeswneeeeeese\n" +
            "swswswswswswswwneneswswswswwswswswwswsesw\n" +
            "seseseneswseseseseswseswseseseseswwsenwsese\n" +
            "wwwewswnwwwwwnwwnwswnwne\n" +
            "wenwnwwwnwwwswwnwwwnewnewswsenw\n" +
            "swwswwwnenesewswswswnwswswsewwwwsw\n" +
            "nenwnewneneneseneswneneneneenwenenenene\n" +
            "nenenewneneneswnenenenenenenenenenesene\n" +
            "swnwwsewwwwwwwwswwneswwesesw\n" +
            "wwnwnwnwwwsewwwwwwwwwwsew\n" +
            "nenenesenewnenwneswnenwseeenwnenwsee\n" +
            "senwenenwnwnenwnwnenwnwneneneswnwnwnesene\n" +
            "neneenwneneneweeseswwnesenenwnwnewnesw\n" +
            "ewnwnenwnenwnenenwnenwsenwnwneswnwsenw\n" +
            "seswswsweseseneswnesesenwswnwseseswswwsese\n" +
            "swswnwswenenwsewsesw\n" +
            "sesweeseeneseseswsewesenenwnwsw\n" +
            "seseseswseseeswweseswseswnwsesesesesw\n" +
            "neeswnwswnenesesenwnwnwewsenwnwnewwsese\n" +
            "wneswwwwwwwswwswwswswswww\n" +
            "eeeeeeneneeenwswnewsweswnwnenw\n" +
            "neneswweeneeenwwseneswnweneenee\n" +
            "nenenewnwnenwnenewneneeneneneene\n" +
            "wswswswseswswsenenwswseswsweswsweswswnwsw\n" +
            "wsesesesesesesesesesesesesesesenesesese\n" +
            "wnwwnwwwnwswnwwsenwnwenwnewnwwnw\n" +
            "wwswneneesewseneswnenenenenenenenwneese\n" +
            "neeseswnenenwwnenenwseneneneenwneneww\n" +
            "enwnenwswneneneneneswenenwnenenenenenenwne\n" +
            "neneneneneneneenenesenenwneeneswnenenene\n" +
            "swsenewswsweseswswswewneewwswwnw\n" +
            "wsewswswnwneswwwwswswwswwwwwww\n" +
            "seneneneneeneneswsenenewnwnwnene\n" +
            "nenewnenenenwnenenwnenenwnenwnenenenese\n" +
            "seseseswswnenwseseseenwswnwswsesesewsese\n" +
            "swnwnwnewsweseeneseswneeenw\n" +
            "wwsenwnwnwnwewwwnwwwnwnwwwnwnw\n" +
            "nwwnwneseseeneswnwnwnenwnwsenwnwswse\n" +
            "eeseneeeseeeeseneseswweseeeswe\n" +
            "swswneseswseswwswswswswwswswswswswnenwsw\n" +
            "nwnwnwnenwwsenenenenwnwswnenwnwenwnwnwnw\n" +
            "eseseseeesesesesenwseseswseesenwsese\n" +
            "enweeseeeswenwseseeeseeeeeesw\n" +
            "sesenweeeeseseeeesweseeseeee\n" +
            "nwnwnwnwnwnwnwsenwnwnwnwnw\n" +
            "nenwneswnwnwnenwnwnwnenwnwnene\n" +
            "neseeeeswwenweeweweweenewe\n" +
            "neweeeseeseseseseeswenweeesenee\n" +
            "swwswnewseeswneneeseenwnwenwnenenenw\n" +
            "seswesenesewswenweseenweeweeene\n" +
            "seesewnwswseseesee\n" +
            "nwneswseseesewwnwseesenewneseswsese\n" +
            "nwseeeeenwesesewneseseeseeswsesee\n" +
            "nenenenewneseesenenweeesenenwnenenene\n" +
            "eeenwswwnwneenweeewsenwswesese\n" +
            "wneswwwwwswwwnewswswswwwswwseswsw\n" +
            "swseswseseswsesenwsesesenesesesweswseswse\n" +
            "wwswneswwsewwwweswsw\n" +
            "wseeseeenwseseneswswseese\n" +
            "nenwswnwswwwswsee\n" +
            "nwnwnwnenwnwnwnwnwnewnwnwnwswneenenenwne\n" +
            "eeeeswneseeeesesee\n" +
            "enenenwnwnwnwnenenenwnwnwnwswnenwnwswnw\n" +
            "eenewesesenenenenenewnenenenwneswnene\n" +
            "nwnwnwnwsenwnwesewnwsenenwwwwwnwsenwne\n" +
            "eesenwwsenwseswswwseweeeesenene\n" +
            "eeeneneeneeneeeeeneeewseesw\n" +
            "swwwwwswswswswwwswnwswwswwsweesw\n" +
            "neneneeenenewesweenwneeseeenenwee\n" +
            "swwswnwwswswswwswewswwwswswwww\n" +
            "neneneneneeewneeweneneenene\n" +
            "swswseswswnwswswsweseswswswwswswswswswsee\n" +
            "nwnwneswswswsewneswswseswnweswswweswse\n" +
            "eswswwswswswswswwneswswsweswsw\n" +
            "nwwwswswswwwsenwwwwnesewwswww\n" +
            "newneneeswneneneenesenenesw\n" +
            "nwwnwnwenwnwnwwnwww\n" +
            "nenesesewseseseseseseseseseseseseseesewe\n" +
            "neneneneneneneneneneeswneneswnenwneenene\n" +
            "nwswnwnwwwwnwnwnwewnwnww\n" +
            "nwnwnenwnwnwnwnwnenwnwsenwwnwnwnwnwswnwnw\n" +
            "nwnwnwnenewsenenenenwnenwewwsenenwne\n" +
            "wwsweswwwwswwswwswswsww\n" +
            "newseenewenwseswswsw\n" +
            "neswsenwnwnweenenwesenenwnesenenenenenesw\n" +
            "nwswwnwwenwnwnwnwnwenwnwswnwnwwnwwnw\n" +
            "nenwneswnenwswnenwnenenwnwnenenenenene\n" +
            "swswswswwweswswnwswseswswnewsweswswsw\n" +
            "ewseeseseseseeseeesenwseseseesese\n" +
            "nwnewnwsenwwnwwsenwnwww\n" +
            "nwswswswweneswswwswwneseswseswswswe\n" +
            "eewseweneeeseesweneesesenwneswe\n" +
            "wswwnesewnewwneneswsewwwwsewwww\n" +
            "nenenenwnenwnenwnenwenwnewneswnwnwnee\n" +
            "eeweeeeesesewnewseenweseee\n" +
            "enenenewswnenenwseneneeneneenenenenenee\n" +
            "eswenwswnwnwnenewswwneseseseeesenw\n" +
            "swswseswswswswseseswneswsw\n" +
            "neneeenwswnesweswneseneneenenweeenene\n" +
            "newwwwesewsewwwwwwnwswwneww\n" +
            "wesenenwnweseeseseeeeswe\n" +
            "seeeenweneneeneeeeeeene\n" +
            "swwsewnwseseneweneswneeswswnwseswswse\n" +
            "wnenenwswneenewneswneneneeeswenwne\n" +
            "sewnesenwwnwnwnwwnewnwnwnwnwswsewnw\n" +
            "eeeenwswesweeswneeweeeeenee\n" +
            "nwseswswsesesenwseseseswswsw\n" +
            "eeeseseeseesesewnenwewesweweee\n" +
            "wswsewwwsenwnenewsewwsenwnewwnew\n" +
            "eeeeenwneeeenwenesesweeeneeee\n" +
            "nesewseswneneenenwnene\n" +
            "neneenwneneneswsweenewnenwneneenene\n" +
            "nwnwnwnwnwnwwnwnwnwweswnwnwnwnwnwwenw\n" +
            "seswsweseswseswswseneswswwswseseswswsw\n" +
            "seseseswwseseswswswseneenewsew\n" +
            "swsewswswsesesewswseseswswneswnesw\n" +
            "ewweenwneseneeneesweeeweee\n" +
            "swseswswseswsweswneswswswsenwswsw\n" +
            "neseswseseseseseesesenwwseseesesese\n" +
            "nwswnwswnwnwnwwnwenwnwnwenwnwnwnwnenww\n" +
            "neneneneneenesweseenewnewneeswnesee\n" +
            "sweseseeewwneswseenweesesewnwe\n" +
            "nwnwnwnwewnwnwnwnwnwnwwnwwnwwsenwenw\n" +
            "nwwwwswswswwewwsenwww\n" +
            "eswwnwnwneswswswnwwswswweseswwswswsw\n" +
            "swneweseneenenwneneneeseneseweww\n" +
            "nwnenenwnwnenwnenenenwnenwnwnwsenwne\n" +
            "neswswswswswnwsesenenewwseseseswseswse\n" +
            "nesenwswseseseseewsesesweswesenwsesew\n" +
            "nwwnwnwnwnwsenwnweswnwwswnwnwnwnwenenw\n" +
            "nwneneeswnenwnwnenwwnwnwenwneswnwnwsw\n" +
            "nwnenwnwswnenenwnewnwnwnweweswnwswseswe\n" +
            "wnwsenewswnwnwnwnwwnwnwswnwnwswnwenwnwe\n" +
            "swwswswswswswswswswswneseswswswswswswsw\n" +
            "enwesenwseneesewwnwswse\n" +
            "neeneenewenesesweneneeeeneeene\n" +
            "esewesenwnenewneewnenene\n" +
            "nenenenenewneneneneenwnwneneneneneswe\n" +
            "seenweeseeseseesesesee";

}
