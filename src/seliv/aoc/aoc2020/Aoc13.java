package seliv.aoc.aoc2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aoc13 {
    public static void main(String[] args) {
        List<List<String>> in = InputParser.parseInput(IN2);
        List<String> ops = in.get(0);

        int time = Integer.parseInt(ops.get(0));
        String buses[] = ops.get(1).split(",");
        Map<Integer, Integer> bts = new HashMap<>();

        for (int i = 0; i < buses.length; i++) {
            String bus =  buses[i];
            if (bus.startsWith("x")) {
                continue;
            }
            int bt = Integer.parseInt(bus);
            bts.put(bt, i);
            System.out.println("i, bt = " + i + ", " + bt);
        }

        long ts = 0;
        long dts = 0;

        int a = 419;
        int b = 421;
        int tdt = 31;
        long lastTa = 0;
        for (long l = 0; l < 10000; l++) {
            long ta = l * a;
            long bp = ta / b;

            long dt = (bp + 1) * b - ta;

            if (dt == tdt) {
//                if (lastTa != 0) {
//                    ts =
//                }
                System.out.println("dt, ta, dta  = " + dt + ", " + ta + ", " + (ta - lastTa));
                lastTa = ta;
            }

//            System.out.println("dt = " + dt);

        }

        outer:
        // 176399 = 421 * 419 - the longest intervals from input, both are prime numbers.
        // Let's just iterate over potential solution moments and find the first one fitting all conditions.
        for (long t = 94694 - 13; true; t += 176399) {
//            System.out.println("t = " + t);
            for (Map.Entry<Integer, Integer> entry : bts.entrySet()) {
                int bt = entry.getKey();
                int tm = entry.getValue();
                if ((t + tm) % bt != 0) {
                    continue outer;
                }
            }
            // My machine takes about 100 seconds to reach the correct solution.
            System.out.println("t = " + t);
            break;
        }


//        int minTime = Integer.MAX_VALUE;
//        int route = 0;
//        for (Integer bt : bts) {
//            int t = bt - time % bt;
//            System.out.println("bt = " + bt);
//            System.out.println("t = " + t);
//            if (t < minTime) {
//                minTime = t;
//                route = bt;
//            }
//        }
//
//        System.out.println("route = " + route);
//        System.out.println("minTime = " + minTime);
//        System.out.println("route * minTime = " + route * minTime);
    }

    public static String IN = "939\n" +
            "7,13,x,x,59,x,31,19";

    public static String IN2 = "1002394\n" +
            "13,x,x,41,x,x,x,37,x,x,x,x,x,419,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,19,x,x,x,23,x,x,x,x,x,29,x,421,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,17";

    public static String IN3 = "";

}
