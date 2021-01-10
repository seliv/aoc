package seliv.aoc.aoc2020;

import java.util.List;
import java.util.Map;

public class Aoc12 {
    public static void main(String[] args) {
        List<List<String>> in = InputParser.parseInput(IN2);
        List<String> ops = in.get(0);

        int x = 0;
        int y = 0;

        int dx = 10;
        int dy = 1;

        Head head = Head.EAST;

        for (String op : ops) {
            char dir = op.charAt(0);
            int raange = Integer.parseInt(op.substring(1));
            switch (dir) {
                case 'E':
                    dx += raange;
                    break;
                case 'W':
                    dx -= raange;
                    break;
                case 'N':
                    dy += raange;
                    break;
                case 'S':
                    dy -= raange;
                    break;
                case 'F':
                    x += raange * dx;
                    y += raange * dy;
                    break;
                case 'L':
                    int n = raange / 90;
                    while (n > 0) {
                        int newDy = dx;
                        int newDx = -dy;
                        dx = newDx;
                        dy = newDy;
                        n--;
                    }
                    break;
                case 'R':
                    n = raange / 90;
                    while (n > 0) {
                        int newDx = dy;
                        int newDy = -dx;
                        dx = newDx;
                        dy = newDy;
                        n--;
                    }
                    break;
                default:
                    throw new IllegalStateException();
            }
        }

        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("d = " + x + y);
    }

    public enum Head {
        EAST(1, 0),
        WEST(-1,0),

        NORTH(0,1),

        SOUTH(0,-1);

        public final int dx, dy;

        Head(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        public static Map<Head, Head> LEFT = Map.of(EAST, NORTH, NORTH, WEST, WEST, SOUTH, SOUTH, EAST);
        
        public static Map<Head, Head> RIGHT  = Map.of(EAST, SOUTH, SOUTH, WEST, WEST, NORTH, NORTH, EAST);
    }


    public static final String IN = "F10\n" +
            "N3\n" +
            "F7\n" +
            "R90\n" +
            "F11";

    public static final String IN2 = "F36\n" +
            "L90\n" +
            "S5\n" +
            "F67\n" +
            "W3\n" +
            "R90\n" +
            "F67\n" +
            "R90\n" +
            "E1\n" +
            "S5\n" +
            "W2\n" +
            "N1\n" +
            "E1\n" +
            "F21\n" +
            "S4\n" +
            "E2\n" +
            "S5\n" +
            "R90\n" +
            "N1\n" +
            "F69\n" +
            "R180\n" +
            "N3\n" +
            "F40\n" +
            "E1\n" +
            "S3\n" +
            "R90\n" +
            "S1\n" +
            "R90\n" +
            "F100\n" +
            "W3\n" +
            "F82\n" +
            "N2\n" +
            "W2\n" +
            "F47\n" +
            "W5\n" +
            "F77\n" +
            "W5\n" +
            "R90\n" +
            "N2\n" +
            "W2\n" +
            "F34\n" +
            "N3\n" +
            "E3\n" +
            "F54\n" +
            "L90\n" +
            "S1\n" +
            "E3\n" +
            "R90\n" +
            "F9\n" +
            "R90\n" +
            "E1\n" +
            "N1\n" +
            "F91\n" +
            "L90\n" +
            "S4\n" +
            "W5\n" +
            "S3\n" +
            "L90\n" +
            "W3\n" +
            "F6\n" +
            "L180\n" +
            "N5\n" +
            "F34\n" +
            "E2\n" +
            "L90\n" +
            "F84\n" +
            "L90\n" +
            "W5\n" +
            "L90\n" +
            "W2\n" +
            "N2\n" +
            "E4\n" +
            "L270\n" +
            "F31\n" +
            "N1\n" +
            "R90\n" +
            "N1\n" +
            "W4\n" +
            "L90\n" +
            "F72\n" +
            "S4\n" +
            "F2\n" +
            "L90\n" +
            "F92\n" +
            "N4\n" +
            "E5\n" +
            "F88\n" +
            "R180\n" +
            "E1\n" +
            "S4\n" +
            "E1\n" +
            "S1\n" +
            "F9\n" +
            "N2\n" +
            "W2\n" +
            "L90\n" +
            "F61\n" +
            "R90\n" +
            "F93\n" +
            "S3\n" +
            "L90\n" +
            "W3\n" +
            "F26\n" +
            "E1\n" +
            "L90\n" +
            "E4\n" +
            "S3\n" +
            "R270\n" +
            "N2\n" +
            "F39\n" +
            "R180\n" +
            "S4\n" +
            "E4\n" +
            "F65\n" +
            "S4\n" +
            "R180\n" +
            "S2\n" +
            "F46\n" +
            "W5\n" +
            "R90\n" +
            "E4\n" +
            "L90\n" +
            "F98\n" +
            "W4\n" +
            "R90\n" +
            "E2\n" +
            "R180\n" +
            "F14\n" +
            "L90\n" +
            "S1\n" +
            "F8\n" +
            "S3\n" +
            "L90\n" +
            "N3\n" +
            "F11\n" +
            "S2\n" +
            "L90\n" +
            "W3\n" +
            "S3\n" +
            "L90\n" +
            "W1\n" +
            "L90\n" +
            "F64\n" +
            "S2\n" +
            "E2\n" +
            "N4\n" +
            "W3\n" +
            "F38\n" +
            "E3\n" +
            "N2\n" +
            "F19\n" +
            "S2\n" +
            "L90\n" +
            "E2\n" +
            "F94\n" +
            "E1\n" +
            "R90\n" +
            "E2\n" +
            "S5\n" +
            "L90\n" +
            "F6\n" +
            "E3\n" +
            "S4\n" +
            "L180\n" +
            "W5\n" +
            "R90\n" +
            "N3\n" +
            "W3\n" +
            "S5\n" +
            "F24\n" +
            "L90\n" +
            "F1\n" +
            "W4\n" +
            "F47\n" +
            "W2\n" +
            "S1\n" +
            "E1\n" +
            "S2\n" +
            "E1\n" +
            "N4\n" +
            "L180\n" +
            "F61\n" +
            "W2\n" +
            "F20\n" +
            "E5\n" +
            "S3\n" +
            "F37\n" +
            "F6\n" +
            "L90\n" +
            "E1\n" +
            "R90\n" +
            "W5\n" +
            "S2\n" +
            "L90\n" +
            "E2\n" +
            "N2\n" +
            "L90\n" +
            "F50\n" +
            "W2\n" +
            "F49\n" +
            "R90\n" +
            "S1\n" +
            "R180\n" +
            "S5\n" +
            "R90\n" +
            "S1\n" +
            "E4\n" +
            "R90\n" +
            "F65\n" +
            "L90\n" +
            "S5\n" +
            "E1\n" +
            "F61\n" +
            "S2\n" +
            "F40\n" +
            "L90\n" +
            "E5\n" +
            "R90\n" +
            "E5\n" +
            "L90\n" +
            "S1\n" +
            "F67\n" +
            "S2\n" +
            "F48\n" +
            "R270\n" +
            "E4\n" +
            "F35\n" +
            "S5\n" +
            "F14\n" +
            "L180\n" +
            "E3\n" +
            "L90\n" +
            "F64\n" +
            "W3\n" +
            "R90\n" +
            "E2\n" +
            "R90\n" +
            "S3\n" +
            "L90\n" +
            "S2\n" +
            "W4\n" +
            "F94\n" +
            "R180\n" +
            "N1\n" +
            "W1\n" +
            "R90\n" +
            "F41\n" +
            "R90\n" +
            "N1\n" +
            "L90\n" +
            "W2\n" +
            "N2\n" +
            "R90\n" +
            "F43\n" +
            "N1\n" +
            "L180\n" +
            "F6\n" +
            "E4\n" +
            "F99\n" +
            "N1\n" +
            "F54\n" +
            "N3\n" +
            "R90\n" +
            "F57\n" +
            "W5\n" +
            "F16\n" +
            "S5\n" +
            "L90\n" +
            "E5\n" +
            "R90\n" +
            "S4\n" +
            "L90\n" +
            "F68\n" +
            "L180\n" +
            "S1\n" +
            "E4\n" +
            "R90\n" +
            "F88\n" +
            "S2\n" +
            "F19\n" +
            "R90\n" +
            "W2\n" +
            "W1\n" +
            "F62\n" +
            "S2\n" +
            "R90\n" +
            "N3\n" +
            "R180\n" +
            "S5\n" +
            "E4\n" +
            "F41\n" +
            "W2\n" +
            "S5\n" +
            "W1\n" +
            "R90\n" +
            "W2\n" +
            "W4\n" +
            "L90\n" +
            "S4\n" +
            "R90\n" +
            "F1\n" +
            "W5\n" +
            "F44\n" +
            "W3\n" +
            "N2\n" +
            "F41\n" +
            "W5\n" +
            "L90\n" +
            "N5\n" +
            "W1\n" +
            "F13\n" +
            "E2\n" +
            "R90\n" +
            "N3\n" +
            "W3\n" +
            "F8\n" +
            "W1\n" +
            "R90\n" +
            "F51\n" +
            "R90\n" +
            "E1\n" +
            "S5\n" +
            "F18\n" +
            "S2\n" +
            "E3\n" +
            "F23\n" +
            "L180\n" +
            "F26\n" +
            "N2\n" +
            "F25\n" +
            "E4\n" +
            "F24\n" +
            "E5\n" +
            "N1\n" +
            "L90\n" +
            "S1\n" +
            "E5\n" +
            "R90\n" +
            "F88\n" +
            "S2\n" +
            "W3\n" +
            "R90\n" +
            "F30\n" +
            "L90\n" +
            "E4\n" +
            "L270\n" +
            "S1\n" +
            "E2\n" +
            "F87\n" +
            "W4\n" +
            "N1\n" +
            "R180\n" +
            "N1\n" +
            "F2\n" +
            "L90\n" +
            "S3\n" +
            "F29\n" +
            "S4\n" +
            "L90\n" +
            "N2\n" +
            "F59\n" +
            "L90\n" +
            "W2\n" +
            "N5\n" +
            "F45\n" +
            "N2\n" +
            "W4\n" +
            "N4\n" +
            "F27\n" +
            "W5\n" +
            "F4\n" +
            "S2\n" +
            "F56\n" +
            "L180\n" +
            "S2\n" +
            "R90\n" +
            "W4\n" +
            "F95\n" +
            "L90\n" +
            "R90\n" +
            "F68\n" +
            "L90\n" +
            "S4\n" +
            "W5\n" +
            "F46\n" +
            "N1\n" +
            "W2\n" +
            "F80\n" +
            "R270\n" +
            "F35\n" +
            "L90\n" +
            "E3\n" +
            "S5\n" +
            "R90\n" +
            "S1\n" +
            "W2\n" +
            "F53\n" +
            "S3\n" +
            "R180\n" +
            "F38\n" +
            "F57\n" +
            "W1\n" +
            "R90\n" +
            "N1\n" +
            "W4\n" +
            "S3\n" +
            "R180\n" +
            "E1\n" +
            "F24\n" +
            "S5\n" +
            "F96\n" +
            "W4\n" +
            "F53\n" +
            "S4\n" +
            "F59\n" +
            "F7\n" +
            "E5\n" +
            "S2\n" +
            "L90\n" +
            "E2\n" +
            "L90\n" +
            "N5\n" +
            "L90\n" +
            "N3\n" +
            "F75\n" +
            "S2\n" +
            "R180\n" +
            "N1\n" +
            "W3\n" +
            "N3\n" +
            "R90\n" +
            "F71\n" +
            "L180\n" +
            "S4\n" +
            "L90\n" +
            "F53\n" +
            "S5\n" +
            "L90\n" +
            "N5\n" +
            "S5\n" +
            "F14\n" +
            "L90\n" +
            "E3\n" +
            "F40\n" +
            "N1\n" +
            "E3\n" +
            "N2\n" +
            "F69\n" +
            "W1\n" +
            "N5\n" +
            "W3\n" +
            "S3\n" +
            "R90\n" +
            "N3\n" +
            "F3\n" +
            "S5\n" +
            "E1\n" +
            "R90\n" +
            "E5\n" +
            "F16\n" +
            "R90\n" +
            "F94\n" +
            "E2\n" +
            "L180\n" +
            "F16\n" +
            "E2\n" +
            "F71\n" +
            "W1\n" +
            "F4\n" +
            "L90\n" +
            "W4\n" +
            "F45\n" +
            "L90\n" +
            "S3\n" +
            "W3\n" +
            "S5\n" +
            "R90\n" +
            "E3\n" +
            "N1\n" +
            "W1\n" +
            "S3\n" +
            "L90\n" +
            "W2\n" +
            "W1\n" +
            "S5\n" +
            "F1\n" +
            "L90\n" +
            "E3\n" +
            "L180\n" +
            "S5\n" +
            "F5\n" +
            "W1\n" +
            "L90\n" +
            "N5\n" +
            "E5\n" +
            "R90\n" +
            "E2\n" +
            "R90\n" +
            "W5\n" +
            "R90\n" +
            "F11\n" +
            "W5\n" +
            "S2\n" +
            "E5\n" +
            "R90\n" +
            "F29\n" +
            "W5\n" +
            "S5\n" +
            "F14\n" +
            "S4\n" +
            "L90\n" +
            "F48\n" +
            "R180\n" +
            "F63\n" +
            "E4\n" +
            "N4\n" +
            "E5\n" +
            "W5\n" +
            "S4\n" +
            "W5\n" +
            "S5\n" +
            "F9\n" +
            "L270\n" +
            "W3\n" +
            "F78\n" +
            "W2\n" +
            "F100\n" +
            "W2\n" +
            "N4\n" +
            "F44\n" +
            "F11\n" +
            "E2\n" +
            "F17\n" +
            "E4\n" +
            "F80\n" +
            "S5\n" +
            "F36\n" +
            "L90\n" +
            "W2\n" +
            "F12\n" +
            "L90\n" +
            "N4\n" +
            "E3\n" +
            "S5\n" +
            "F90\n" +
            "F73\n" +
            "W1\n" +
            "S3\n" +
            "W4\n" +
            "F50\n" +
            "N4\n" +
            "N3\n" +
            "F79\n" +
            "W5\n" +
            "F38\n" +
            "L90\n" +
            "W5\n" +
            "S3\n" +
            "F50\n" +
            "R90\n" +
            "L270\n" +
            "R180\n" +
            "F84\n" +
            "L180\n" +
            "E2\n" +
            "R180\n" +
            "N3\n" +
            "N3\n" +
            "W5\n" +
            "S3\n" +
            "L180\n" +
            "N3\n" +
            "E1\n" +
            "N4\n" +
            "N5\n" +
            "F23\n" +
            "N2\n" +
            "E1\n" +
            "N1\n" +
            "E3\n" +
            "F64\n" +
            "E4\n" +
            "F4\n" +
            "R90\n" +
            "S4\n" +
            "E5\n" +
            "S2\n" +
            "L90\n" +
            "E3\n" +
            "F32\n" +
            "W1\n" +
            "N2\n" +
            "F20\n" +
            "E4\n" +
            "W5\n" +
            "F93\n" +
            "E5\n" +
            "N1\n" +
            "W4\n" +
            "F18\n" +
            "E1\n" +
            "N1\n" +
            "F7\n" +
            "N3\n" +
            "F55\n" +
            "E3\n" +
            "F91\n" +
            "W4\n" +
            "F86\n" +
            "N5\n" +
            "W1\n" +
            "F38\n" +
            "N4\n" +
            "R180\n" +
            "W4\n" +
            "S5\n" +
            "F95\n" +
            "R180\n" +
            "F22\n" +
            "R90\n" +
            "F58\n" +
            "W3\n" +
            "F62\n" +
            "L90\n" +
            "F17\n" +
            "S2\n" +
            "R90\n" +
            "N5\n" +
            "L90\n" +
            "N4\n" +
            "E1\n" +
            "N1\n" +
            "E2\n" +
            "E4\n" +
            "R180\n" +
            "F72\n" +
            "N4\n" +
            "E4\n" +
            "R180\n" +
            "F74\n" +
            "W5\n" +
            "N5\n" +
            "W5\n" +
            "N5\n" +
            "W2\n" +
            "F26\n" +
            "S2\n" +
            "E4\n" +
            "N4\n" +
            "F23\n" +
            "E2\n" +
            "F95\n" +
            "E1\n" +
            "N4\n" +
            "E1\n" +
            "L270\n" +
            "S3\n" +
            "E3\n" +
            "R90\n" +
            "S2\n" +
            "E5\n" +
            "R180\n" +
            "F73\n" +
            "S5\n" +
            "W1\n" +
            "F61\n" +
            "F60\n" +
            "E2\n" +
            "F97\n" +
            "S2\n" +
            "L90\n" +
            "W5\n" +
            "R180\n" +
            "F99\n" +
            "R180\n" +
            "F52\n" +
            "N1\n" +
            "L180\n" +
            "S4\n" +
            "W4\n" +
            "R180\n" +
            "F70\n" +
            "S4\n" +
            "L90\n" +
            "S5\n" +
            "W2\n" +
            "L90\n" +
            "N2\n" +
            "W5\n" +
            "F31\n" +
            "L90\n" +
            "E3\n" +
            "F87\n" +
            "L90\n" +
            "S2\n" +
            "W4\n" +
            "F25\n" +
            "L180\n" +
            "F50\n" +
            "S5\n" +
            "E1\n" +
            "F75\n" +
            "N2\n" +
            "F30\n" +
            "S4\n" +
            "F100\n" +
            "E3\n" +
            "L180\n" +
            "F57\n" +
            "L90\n" +
            "W5\n" +
            "R90\n" +
            "W2\n" +
            "F65\n" +
            "L90\n" +
            "F29\n" +
            "E4\n" +
            "L270\n" +
            "E3\n" +
            "L90\n" +
            "N4\n" +
            "E2\n" +
            "E5\n" +
            "F36\n" +
            "N4\n" +
            "E4\n" +
            "N4\n" +
            "F41\n" +
            "E2\n" +
            "S3\n" +
            "F72\n" +
            "W4\n" +
            "F26\n" +
            "L90\n" +
            "E5\n" +
            "R90\n" +
            "N4\n" +
            "F97\n" +
            "L90\n" +
            "W1\n" +
            "F31\n" +
            "N2\n" +
            "L90\n" +
            "E3\n" +
            "N5\n" +
            "L90\n" +
            "F5\n" +
            "R180\n" +
            "F97\n" +
            "S1\n" +
            "E5\n" +
            "N4\n" +
            "R90\n" +
            "F77\n" +
            "N2\n" +
            "F92\n" +
            "S5\n" +
            "F84\n" +
            "E3\n" +
            "S3\n" +
            "L270\n" +
            "N3\n" +
            "R180\n" +
            "N4\n" +
            "F63\n" +
            "N4\n" +
            "E5\n" +
            "F62\n" +
            "S3\n" +
            "F54\n" +
            "N2\n" +
            "E5\n" +
            "F89\n" +
            "R90\n" +
            "S4\n" +
            "F95\n" +
            "F56\n" +
            "L90\n" +
            "S4\n" +
            "L90\n" +
            "S4\n" +
            "R180\n" +
            "F93";
}
