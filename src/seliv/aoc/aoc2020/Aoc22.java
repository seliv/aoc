package seliv.aoc.aoc2020;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Aoc22 {
    static Queue<Integer> queue1 = new LinkedList<>();
    static Queue<Integer> queue2 = new LinkedList<>();

    public static void main(String[] args) {
        List<List<String>> in = InputParser.parseInput(IN3);

        loadQueue(in.get(0), queue1);
        loadQueue(in.get(1), queue2);

        System.out.println("queue1 = " + queue1);
        System.out.println("queue2 = " + queue2);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            deal(queue1, queue2);
            System.out.println("queue1 = " + queue1);
            System.out.println("queue2 = " + queue2);
            System.out.println();
        }

        Queue<Integer> wq = queue1.isEmpty() ? queue2 : queue1;
        score(wq);
    }

    static void loadQueue(List<String> in, Queue<Integer> queue) {
        for (int i = 1; i < in.size(); i++) {
            queue.add(Integer.parseInt(in.get(i)));
        }
    }

    static void deal(Queue<Integer> q1, Queue<Integer> q2) {
        int i1 = q1.poll();
        int i2 = q2.poll();
        if (i1 == i2) {
            throw new IllegalArgumentException();
        }
        Queue<Integer> wq = i1 > i2 ? q1 : q2;
        wq.add(Math.max(i1, i2));
        wq.add(Math.min(i1, i2));
    }

    static void score(Queue<Integer> q) {
        long res = 0;
        long mult = q.size();
        while (!q.isEmpty()) {
            int i = q.poll();
            res += mult * i;
            mult--;
        }
        System.out.println("res = " + res);
    }

    public static String IN = "Player 1:\n" +
            "9\n" +
            "2\n" +
            "6\n" +
            "3\n" +
            "1\n" +
            "\n" +
            "Player 2:\n" +
            "5\n" +
            "8\n" +
            "4\n" +
            "7\n" +
            "10";

    public static String IN2 = "";

    public static String IN3 = "Player 1:\n" +
            "31\n" +
            "24\n" +
            "5\n" +
            "33\n" +
            "7\n" +
            "12\n" +
            "30\n" +
            "22\n" +
            "48\n" +
            "14\n" +
            "16\n" +
            "26\n" +
            "18\n" +
            "45\n" +
            "4\n" +
            "42\n" +
            "25\n" +
            "20\n" +
            "46\n" +
            "21\n" +
            "40\n" +
            "38\n" +
            "34\n" +
            "17\n" +
            "50\n" +
            "\n" +
            "Player 2:\n" +
            "1\n" +
            "3\n" +
            "41\n" +
            "8\n" +
            "37\n" +
            "35\n" +
            "28\n" +
            "39\n" +
            "43\n" +
            "29\n" +
            "10\n" +
            "27\n" +
            "11\n" +
            "36\n" +
            "49\n" +
            "32\n" +
            "2\n" +
            "23\n" +
            "19\n" +
            "9\n" +
            "13\n" +
            "15\n" +
            "47\n" +
            "6\n" +
            "44";

}
