package seliv.aoc.aoc2020;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Aoc13 {
    public static void main(String[] args) {
        List<List<String>> in = InputParser.parseInput(IN2);
        List<String> ops = in.get(0);

        int time = Integer.parseInt(ops.get(0));
        String buses[] = ops.get(1).split(",");
        List<Integer> bts = new ArrayList<>();
        for (String bus : buses) {
            if (!bus.startsWith("x")) {
                int bt = Integer.parseInt(bus);
                bts.add(bt);
            }
        }

        int minTime = Integer.MAX_VALUE;
        int route = 0;
        for (Integer bt : bts) {
            int t = bt - time % bt;
            System.out.println("bt = " + bt);
            System.out.println("t = " + t);
            if (t < minTime) {
                minTime = t;
                route = bt;
            }
        }

        System.out.println("route = " + route);
        System.out.println("minTime = " + minTime);
        System.out.println("route * minTime = " + route * minTime);
    }

    public static String IN = "939\n" +
            "7,13,x,x,59,x,31,19";

    public static String IN2 = "1002394\n" +
            "13,x,x,41,x,x,x,37,x,x,x,x,x,419,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,19,x,x,x,23,x,x,x,x,x,29,x,421,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,17";

    public static String IN3 = "";

}
