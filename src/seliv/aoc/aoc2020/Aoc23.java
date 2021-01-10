package seliv.aoc.aoc2020;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Aoc23 {
    public static void main(String[] args) {
        String in = IN2;

        int all = 1_000_000;

        Node[] byValue = new Node[all + 1];

        Node last = null;
        Node prev = null;
        for (int i = in.length() - 1; i >= 0; i--) {
            final int v = Integer.parseInt("" + in.charAt(i));
            Node n = new Node(v);
            byValue[v] = n;
            if (last == null) {
                last = n;
                prev = n;
            } else {
                n.next = prev;
                prev = n;
            }
        }
        for (int v = in.length() + 1; v <= all; v++) {
            Node n = new Node(v);
            byValue[v] = n;
            last.next = n;
            last = n;
        }
        last.next = prev;

        Node now = prev;

        for (int m = 0; m < 10_000_000; m++) {
//        for (int m = 0; m < 10; m++) {
            if (m % 100_000 == 0) {
                print(all, now);
            }
//            print(all, now);

            Node n1 = now.next;
            Node n2 = n1.next;
            Node n3 = n2.next;

            int dest = now.v;

            do {
                dest--;
                if (dest == 0) {
                    dest = all;
                }
            } while (
                    (dest == n1.v) ||
                    (dest == n2.v) ||
                    (dest == n3.v)
            );

//            Node f = n3.next;
//            while (f.v != dest) {
//                f = f.next;
//            }
            Node f = byValue[dest];

            now.next = n3.next;
            n3.next = f.next;
            f.next = n1;

            now = now.next;
        }

        System.out.println();
        System.out.println();
        System.out.println();
        print(all, now);
    }

    private static void print(int all, Node nn) {
        while (nn.v != 1) {
            nn = nn.next;
        }
        long res = (long)nn.next.v * (long)nn.next.next.v;
        for (int i = 0; i < Math.min(all, 20); i++) {
            System.out.print(" " + nn.v);
            nn = nn.next;
        }
        System.out.println(" ( " + res + " )");
        System.out.println();
    }

    static class Node {
        public final int v;
        public Node next;

        public Node(int v) {
            this.v = v;
        }
    }


    public static void main1(String[] args) {
        String in = IN0;

        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < in.length(); i++) {
            q.add(Integer.parseInt("" + in.charAt(i)));
        }
//        for (int i = 10; i < 1_00; i++) {
//            q.add(i);
//        }

        int idx = 0;
        int all = q.size() - 1;

        for (int m = 0; m < 100; m++) {
//            if (m % 1_00_000 == 0) {
//                System.out.println("m = " + m);
//                System.out.println("q = " + q);
//            }
            System.out.println("q = " + q);

            int i1 = q.get((idx + 1) % all);
            int i2 = q.get((idx + 2) % all);
            int i3 = q.get((idx + 3) % all);

            int dest = q.get(idx) - 1;
            if (dest < 1) {
                dest = all;
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

//            System.out.println("dest = " + dest);

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

    public static String IN0 = "123456789";


    public static String IN = "389125467";

    public static String IN2 = "792845136";

    public static String IN3 = "";

}
