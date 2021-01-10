package seliv.aoc.aoc2020;

import java.util.List;
import java.util.Map;

public class Aoc25 {
    private static final long dv = 20201227;
    
    public static void main(String[] args) {
//        long a1 = 5764801;
//        long a2 = 17807724;

        long a1 = 3248366;
        long a2 = 4738476;

        long f1 = find(a1);
        long f2 = find(a2);

        System.out.println("f1 = " + f1);
        System.out.println("f2 = " + f2);

        long zz = a1;
        while (f2 > 1) {
            f2--;
            zz = (zz * a1) % dv;
        }
        System.out.println("zz = " + zz);
    }

    static long find(long a) {
        long res = 1;
        long mult = 7;
        while (true) {
            if (mult == a) {
                return res;
            }
            res++;
            mult = (mult * 7) % dv;
        }
    }

    public static String IN = "5764801\n";

    public static String IN2 = "";

    public static String IN3 = "";

}
