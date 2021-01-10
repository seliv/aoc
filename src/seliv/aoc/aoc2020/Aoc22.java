package seliv.aoc.aoc2020;

import java.util.*;

public class Aoc22 {
    public static void main(String[] args) {
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();

        List<List<String>> in = InputParser.parseInput(IN3);

        loadQueue(in.get(0), queue1);
        loadQueue(in.get(1), queue2);

//        queue1.add(2);
//        queue1.add(8);
//        queue1.add(1);
//
//        queue2.add(6);
//        queue2.add(3);
//        queue2.add(4);
//        queue2.add(10);
//        queue2.add(9);
//        queue2.add(7);
//        queue2.add(5);

        System.out.println("queue1 = " + queue1);
        System.out.println("queue2 = " + queue2);
//        played.add(new State(queue1, queue2));
        System.out.println();

//        deal2(queue1, queue2);
        Game game = new Game(queue1, queue2);
        game.play();

//        while (!queue1.isEmpty() && !queue2.isEmpty()) {
//            if (played.contains(new State(queue1, queue2))) {
//                System.out.println("Combination repeats");
//                score(queue1);
//                System.out.println("-------------------");
//                break;
//            }
//            played.add(new State(queue1, queue2));
//            deal2(queue1, queue2);
//            System.out.println("queue1 = " + queue1);
//            System.out.println("queue2 = " + queue2);
//            System.out.println();
//        }

        Queue<Integer> wq = queue1.isEmpty() ? queue2 : queue1;
        score(wq);
    }

    static class Game {
        public final Queue<Integer> q1;
        public final Queue<Integer> q2;
        private final Set<State> played = new HashSet<>();

        public Game(Queue<Integer> q1, Queue<Integer> q2) {
            this.q1 = q1;
            this.q2 = q2;
        }

        boolean play() {
            while (!q1.isEmpty() && !q2.isEmpty()) {
                if (played.contains(new State(q1, q2))) {
                    System.out.println("Combination repeats");
//                score(q1);
                    System.out.println("-------------------");
                    return true;
                }
                played.add(new State(q1, q2));
                deal2(q1, q2);
                System.out.println("queue1 = " + q1);
                System.out.println("queue2 = " + q2);
                System.out.println();
            }
            return q2.isEmpty();
        }
    }


    static void loadQueue(List<String> in, Queue<Integer> queue) {
        for (int i = 1; i < in.size(); i++) {
            queue.add(Integer.parseInt(in.get(i)));
        }
    }

    static boolean deal2(Queue<Integer> q1, Queue<Integer> q2) {
        System.out.println("-- q1 = " + q1);
        System.out.println("-- q2 = " + q2);
        int i1 = q1.peek();
        int i2 = q2.peek();
        if (i1 == i2) {
            throw new IllegalArgumentException();
        }
//        if ((q1.size() >= i1) && (q2.size() >= i2)) {
//            Queue<Integer> sq1 = copyQ(q1, i1);
//            Queue<Integer> sq2 = copyQ(q2, i2);
//
//        } else {
//            Queue<Integer> wq = i1 > i2 ? q1 : q2;
//            wq.add(Math.max(i1, i2));
//            wq.add(Math.min(i1, i2));
//        }
        int w1, w2;
        Queue<Integer> wq;
        final boolean b = firstWins(q1, q2);
        if (b) {
            wq = q1;
            w1 = i1;
            w2 = i2;
        } else {
            wq = q2;
            w1 = i2;
            w2 = i1;
        }
        wq.add(w1);
        wq.add(w2);
        return b;
    }

    static boolean firstWins(Queue<Integer> q1, Queue<Integer> q2) {
        int i1 = q1.poll();
        int i2 = q2.poll();
        if (i1 == i2) {
            throw new IllegalArgumentException();
        }
        if ((q1.size() >= i1) && (q2.size() >= i2)) {
            Queue<Integer> sq1 = copyQ(q1, i1);
            Queue<Integer> sq2 = copyQ(q2, i2);
            System.out.println("Sub-game:");
            Game subGame = new Game(sq1, sq2);
            return subGame.play();
//            return play(sq1, sq2);
        } else {
            System.out.println("player1 wins: " + (i1 > i2) + " : " + i1 + ", " + i2);
            return i1 > i2;
        }
    }

    static Queue<Integer> copyQ(Queue<Integer> src, int len) {
        Queue<Integer> temp = new LinkedList<>(src);
        Queue<Integer> res = new LinkedList<>();
        while (len > 0) {
            int i = temp.poll();
            res.add(i);
            len--;
        }
        return res;
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

    static class State {
        public final Queue<Integer> q1;
        public final Queue<Integer> q2;

        public State(Queue<Integer> q1, Queue<Integer> q2) {
            this.q1 = new LinkedList<>(q1);
            this.q2 = new LinkedList<>(q2);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            State state = (State) o;

            if (!q1.equals(state.q1)) return false;
            return q2.equals(state.q2);
        }

        @Override
        public int hashCode() {
            int result = q1.hashCode();
            result = 31 * result + q2.hashCode();
            return result;
        }
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
