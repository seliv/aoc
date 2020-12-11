package seliv.aoc.aoc2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Aoc10 {
    public static void main(String[] args) {
        List<List<String>> in = InputParser.parseInput(IN3);
        List<String> ops = in.get(0);

        List<Long> vv  = ops.stream().map(v -> Long.valueOf(v)).sorted().collect(Collectors.toList());
        System.out.println("vv = " + vv);

        long[] perm = new long[vv.get(vv.size() - 1).intValue() + 1];

        Arrays.fill(perm, 0);
        perm[0] = 1;

        for (Long v : vv) {
            int i = v.intValue();
            long res = 0;
            if (i - 1 >= 0) {
                res += perm[i - 1];
            }
            if (i - 2 >= 0) {
                res += perm[i - 2];
            }
            if (i - 3 >= 0) {
                res += perm[i - 3];
            }
            perm[i] = res;
        }

        System.out.println("perm[perm.length - 1] = " + perm[perm.length - 1]);

//        int diff1 = 0, diff2 = 0, diff3  = 0;
//        for (long v : vv) {
//            if (1 == v - now) {
//                diff1++;
//            } else if (2 == v - now) {
//                diff2++;
//            } else if (3 == v - now) {
//                diff3++;
//            } else {
//                throw new IllegalStateException();
//            }
//            now = v;
//        }


//        long now = 0;
        int diff1 = 0, diff2 = 0, diff3  = 0;
//        for (long v : vv) {
//            if (1 == v - now) {
//                diff1++;
//            } else if (2 == v - now) {
//                diff2++;
//            } else if (3 == v - now) {
//                diff3++;
//            } else {
//                throw new IllegalStateException();
//            }
//            now = v;
//        }


        System.out.println("diff1 = " + diff1);
        diff3++;
        System.out.println("diff2 = " + diff2);
        System.out.println("diff3 = " + diff3);

        System.out.println("diff1 * diff3 = " + diff1 * diff3);
    }

    public static String IN = "16\n" +
            "10\n" +
            "15\n" +
            "5\n" +
            "1\n" +
            "11\n" +
            "7\n" +
            "19\n" +
            "6\n" +
            "12\n" +
            "4";

    public static String IN2 = "28\n" +
            "33\n" +
            "18\n" +
            "42\n" +
            "31\n" +
            "14\n" +
            "46\n" +
            "20\n" +
            "48\n" +
            "47\n" +
            "24\n" +
            "23\n" +
            "49\n" +
            "45\n" +
            "19\n" +
            "38\n" +
            "39\n" +
            "11\n" +
            "1\n" +
            "32\n" +
            "25\n" +
            "35\n" +
            "8\n" +
            "17\n" +
            "7\n" +
            "9\n" +
            "4\n" +
            "2\n" +
            "34\n" +
            "10\n" +
            "3";

    public static String IN3 = "77\n" +
            "10\n" +
            "143\n" +
            "46\n" +
            "79\n" +
            "97\n" +
            "54\n" +
            "116\n" +
            "60\n" +
            "91\n" +
            "80\n" +
            "132\n" +
            "20\n" +
            "154\n" +
            "53\n" +
            "14\n" +
            "103\n" +
            "31\n" +
            "65\n" +
            "110\n" +
            "43\n" +
            "38\n" +
            "47\n" +
            "120\n" +
            "112\n" +
            "87\n" +
            "24\n" +
            "95\n" +
            "33\n" +
            "104\n" +
            "73\n" +
            "22\n" +
            "66\n" +
            "137\n" +
            "21\n" +
            "109\n" +
            "118\n" +
            "63\n" +
            "55\n" +
            "124\n" +
            "146\n" +
            "148\n" +
            "84\n" +
            "86\n" +
            "147\n" +
            "125\n" +
            "23\n" +
            "85\n" +
            "117\n" +
            "71\n" +
            "48\n" +
            "136\n" +
            "151\n" +
            "130\n" +
            "83\n" +
            "56\n" +
            "140\n" +
            "9\n" +
            "49\n" +
            "113\n" +
            "131\n" +
            "133\n" +
            "74\n" +
            "37\n" +
            "127\n" +
            "34\n" +
            "32\n" +
            "106\n" +
            "1\n" +
            "78\n" +
            "11\n" +
            "72\n" +
            "40\n" +
            "96\n" +
            "17\n" +
            "64\n" +
            "92\n" +
            "102\n" +
            "123\n" +
            "126\n" +
            "90\n" +
            "105\n" +
            "57\n" +
            "99\n" +
            "27\n" +
            "70\n" +
            "98\n" +
            "111\n" +
            "30\n" +
            "50\n" +
            "67\n" +
            "2\n" +
            "155\n" +
            "5\n" +
            "119\n" +
            "8\n" +
            "39";
}
