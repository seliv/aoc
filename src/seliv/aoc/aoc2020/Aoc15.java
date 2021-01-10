package seliv.aoc.aoc2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aoc15 {
    public static void main(String[] args) {
        List<List<String>> in = InputParser.parseInput(IN3);
        List<String> ops = in.get(0);
        String[] ins = ops.get(0).split(",");

        List<Long> start = new ArrayList<>();
        for (String s : ins) {
            long l = Long.parseLong(s);
            start.add(l);
        }

        Map<Long, Long> last = new HashMap<>();
        long turn = 1;
        long nextNum = 0;
        for (Long sss : start) {
            System.out.println("turn " + turn + ", sss = " + sss);
            if (last.containsKey(sss)) {
                nextNum = turn - last.get(sss);
            } else {
                nextNum = 0;
            }
            last.put(sss, turn);
            turn++;
        }

        // This straightforward brute force solution finds an answer in about 6 seconds.
        while (turn < 30000001) {
            long num = nextNum;

            if (last.containsKey(num)) {
                nextNum = turn - last.get(num);
            } else {
                nextNum = 0;
            }

            last.put(num, turn);
            if ((turn == 30000000) || (turn == 2020)) {
                System.out.println("turn " + turn + ", num = " + num);
            }
            turn++;
        }
    }

    public static String IN = "0,3,6";

    public static String IN2 = "";

    public static String IN3 = "12,1,16,3,11,0";

}
