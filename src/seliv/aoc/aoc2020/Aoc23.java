package seliv.aoc.aoc2020;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Aoc23 {
    public static void main(String[] args) {
        String in = IN2;

        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < in.length(); i++) {
            q.add(Integer.parseInt("" + in.charAt(i)));
        }

        int idx = 0;
        int all = 9;

        for (int m = 0; m < 100; m++) {
            System.out.println("q = " + q);

            int i1 = q.get((idx + 1) % all);
            int i2 = q.get((idx + 2) % all);
            int i3 = q.get((idx + 3) % all);

            int dest = q.get(idx) - 1;
            if (dest < 1) {
                dest = 9;
            }

            while (
                    (dest == i1) ||
                            (dest == i2) ||
                            (dest == i3)
            ) {
                dest--;
                if (dest < 1) {
                    dest = 9;
                }
            }

            System.out.println("dest = " + dest);

            int f = q.poll();
            q.add(f);
            q.poll();
            q.poll();
            q.poll();

            int next = q.peek();

            int now = q.poll();
            while (now != dest) {
                q.add(now);
                now = q.poll();
            }

            q.add(now);
            q.add(i1);
            q.add(i2);
            q.add(i3);

            while (q.peek() != next) {
                now = q.poll();
                q.add(now);
            }
//
//            while (dest != q.get(newIdx)) {
//                newIdx++;
//            }
//
//            newQ.add(newIdx + 1, i1);
//            newQ.add(newIdx + 2, i2);
//            newQ.add(newIdx + 3, i3);
//
//            idx++;
//
//            System.out.println("newQ = " + newQ);
//            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("q = " + q);
    }

    public static String IN = "389125467";

    public static String IN2 = "792845136";

    public static String IN3 = "";

}
