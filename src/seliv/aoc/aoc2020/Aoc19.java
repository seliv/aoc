package seliv.aoc.aoc2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc19 {
    static Map<Integer, List<List<Integer>>> rules = new HashMap<>();
    public static void main(String[] args) {
        List<List<String>> in = InputParser.parseInput(IN3);
        List<String> rulesIn = in.get(0);
        List<String> messagesIn = in.get(1);

        for (String rs : rulesIn) {
            String[] s1 = rs.split(":");
            int rn = Integer.parseInt(s1[0]);
            String[] s2 = s1[1].trim().split("\\|");
            List<List<Integer>> listSeq = new ArrayList<>();
            for (String s : s2) {
                List<Integer> seq = new ArrayList<>();
                String[] s3 = s.trim().split(" ");
                for (String si : s3) {
                    int i;
                    if ("\"a\"".equals(si)) {
                        i = -1;
                    } else if ("\"b\"".equals(si)) {
                        i = -2;
                    } else {
                        i = Integer.parseInt(si);
                    }
                    seq.add(i);
                }
                listSeq.add(seq);
            }
            rules.put(rn, listSeq);
        }
        System.out.println("rules = " + rules);


        String s = toRegex(0);
        System.out.println(s);
        Pattern p = Pattern.compile(s);

        int res = 0;
        for (String msg : messagesIn) {
            System.out.println("msg = " + msg);
            Matcher m = p.matcher(msg);
            final boolean matches = m.matches();
            System.out.println("m = "  + matches);
            if (matches) {
                res++;
            }
        }
        System.out.println("\n\nres = " + res);

//        int res = 0;
//        for (String msg : messagesIn) {
//            System.out.println("msg = " + msg);
//            int z= matches(msg, 0, 0);
//            System.out.println("======= z = " + z);
//            if (z == msg.length()) {
//                res++;
//                System.out.println("!!!!!!!!!!!!!!!!! " + msg + " MATCHES");
//            } else {
//                System.out.println("################# " + msg + " MIS-MATCHES");
//            }
//            System.out.println();
////            break;
//        }
//        System.out.println("\n\nres = " + res);
    }

    static String toRegex(int rule) {
        String res = "";
        List<List<Integer>> seqList = rules.get(rule);
//        String s1 = "";
//        String s2 = "";
//        String s3 = "";
        for (int a = 0; a < seqList.size(); a++) {
            List<Integer> seq = seqList.get(a);
            if (res.length() > 0) {
                res += ")|(";
            }
            for (int i : seq) {
                if (i == -1) {
                    res += "a";
                    break;
                } else if (i == -2) {
                    res += "b";
                    break;
                } else {
                    res += toRegex(i);
                }
            }
        }
        if ((rule == 8)) {
            return "((" + res + ")+)";
        }
//        if (rule == 11) {
//            System.out.println("res = " + res);
//            return "((" + res + ")+)";
//        }
        return "((" + res + "))";
    }

    static int matches(String s, int rule, int index) {
        List<List<Integer>> seqList = rules.get(rule);
        outer:
        for (List<Integer> seq : seqList) {
            int resIndex = index;
            System.out.println("trying rule " + rule + ", index = " + index + ", seq = " + seq);
            for (int i : seq) {
                if (i == -1) {
                    if (index == -1 || index >= s.length()) {
                        System.out.println("non-match for seq = " + seq);
                        return -1;
                    }
                    if (s.charAt(index) == 'a') {
                        System.out.println("natch at index = " + index);
                        return index + 1;
                    } else {
                        System.out.println("a didin't match, abandoning seq = " + seq + " of rule " + rule);
//                        continue outer;
                        return -1;
                    }
                } else if (i == -2) {
                    if (index == -1 || index >= s.length()) {
                        System.out.println("non-match for seq = " + seq);
                        return -1;
                    }
                    if (s.charAt(index) == 'b') {
                        System.out.println("natch at index = " + index);
                        return index + 1;
                    } else {
                        System.out.println("b didin't match, abandoning seq = " + seq + " of rule " + rule);
//                        continue outer;
                        return -1;
                    }
                } else {
                    resIndex = matches(s, i, resIndex);
                    if (resIndex == -1) {
                        System.out.println("abandoning seq = " + seq + " of rule " + rule);
                        continue outer;
                    }
                }
            }
            System.out.println("matched seq " + seq + " of rule " + rule);
            System.out.println("resIndex = " + resIndex);
            return resIndex;
        }
//        return index;
        System.out.println("finished seq of rule " + rule);
        return -1;
    }

    public static String IN = "0: 4 1 5\n" +
            "1: 2 3 | 3 2\n" +
            "2: 4 4 | 5 5\n" +
            "3: 4 5 | 5 4\n" +
            "4: \"a\"\n" +
            "5: \"b\"\n" +
            "\n" +
            "ababbb\n" +
            "bababa\n" +
            "abbbab\n" +
            "aaabbb\n" +
            "aaaabbb";

    public static String IN2 = "42: 9 14 | 10 1\n" +
            "9: 14 27 | 1 26\n" +
            "10: 23 14 | 28 1\n" +
            "1: \"a\"\n" +
//            "11: 42 31\n" +
            "11: 42 31 | 42 42 31 31 | 42 42 42 31 31 31 | 42 42 42 42 31 31 31 31 | 42 42 42 42 42 31 31 31 31 31\n" +
//            "11: 42 31 | 42 11 31\n" +
//            "11: 42 11 31 | 42 31\n" +
            "5: 1 14 | 15 1\n" +
            "19: 14 1 | 14 14\n" +
            "12: 24 14 | 19 1\n" +
            "16: 15 1 | 14 14\n" +
            "31: 14 17 | 1 13\n" +
            "6: 14 14 | 1 14\n" +
            "2: 1 24 | 14 4\n" +
            "0: 8 11\n" +
            "13: 14 3 | 1 12\n" +
            "15: 1 | 14\n" +
            "17: 14 2 | 1 7\n" +
            "23: 25 1 | 22 14\n" +
            "28: 16 1\n" +
            "4: 1 1\n" +
            "20: 14 14 | 1 15\n" +
            "3: 5 14 | 16 1\n" +
            "27: 1 6 | 14 18\n" +
            "14: \"b\"\n" +
            "21: 14 1 | 1 14\n" +
            "25: 1 1 | 1 14\n" +
            "22: 14 14\n" +
            "8: 42\n" +
//            "8: 42 | 42 8\n" +
//            "8: 42 8 | 42\n" +
            "26: 14 22 | 1 20\n" +
            "18: 15 15\n" +
            "7: 14 5 | 1 21\n" +
            "24: 14 1\n" +
            "\n" +
            "abbbbbabbbaaaababbaabbbbabababbbabbbbbbabaaaa\n" +
            "bbabbbbaabaabba\n" +
            "babbbbaabbbbbabbbbbbaabaaabaaa\n" +
            "aaabbbbbbaaaabaababaabababbabaaabbababababaaa\n" +
            "bbbbbbbaaaabbbbaaabbabaaa\n" +
            "bbbababbbbaaaaaaaabbababaaababaabab\n" +
            "ababaaaaaabaaab\n" +
            "ababaaaaabbbaba\n" +
            "baabbaaaabbaaaababbaababb\n" +
            "abbbbabbbbaaaababbbbbbaaaababb\n" +
            "aaaaabbaabaaaaababaa\n" +
            "aaaabbaaaabbaaa\n" +
            "aaaabbaabbaaaaaaabbbabbbaaabbaabaaa\n" +
            "babaaabbbaaabaababbaabababaaab\n" +
            "aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba";

    public static String IN3 = "27: 116 44 | 127 69\n" +
            "19: 60 116 | 55 127\n" +
            "91: 127 13 | 116 127\n" +
//            "11: 42 31\n" +
//            "11: 42 31 | 42 11 31\n" +
            "11: 42 31 | 42 42 31 31 | 42 42 42 31 31 31 | 42 42 42 42 31 31 31 31 | 42 42 42 42 42 31 31 31 31 31\n" +

            "30: 80 116 | 100 127\n" +
            "53: 78 116 | 100 127\n" +
            "47: 116 129 | 127 76\n" +
            "21: 57 127 | 100 116\n" +
            "26: 86 116 | 28 127\n" +
            "86: 127 122 | 116 29\n" +
            "74: 127 39 | 116 105\n" +
            "41: 118 116 | 132 127\n" +
            "13: 116 | 127\n" +
            "115: 82 116 | 72 127\n" +
            "100: 116 116 | 116 127\n" +
            "5: 78 127 | 91 116\n" +
            "31: 101 127 | 121 116\n" +
            "134: 116 117\n" +
            "73: 123 116 | 62 127\n" +
            "69: 116 124 | 127 77\n" +
            "28: 71 116 | 66 127\n" +
            "14: 131 116 | 128 127\n" +
            "111: 127 59 | 116 117\n" +
            "6: 127 53 | 116 79\n" +
            "104: 26 116 | 65 127\n" +
            "61: 78 127 | 56 116\n" +
            "67: 127 30 | 116 130\n" +
            "32: 81 127 | 27 116\n" +
            "2: 116 117 | 127 72\n" +
            "128: 57 116 | 91 127\n" +
            "81: 127 96 | 116 51\n" +
            "38: 127 59 | 116 54\n" +
            "36: 22 127 | 107 116\n" +
            "35: 116 61 | 127 24\n" +
            "7: 116 100\n" +
            "18: 16 116 | 82 127\n" +
            "116: \"a\"\n" +
            "54: 116 116 | 127 116\n" +
            "84: 20 116 | 93 127\n" +
            "92: 38 127 | 111 116\n" +
            "64: 127 57 | 116 82\n" +
            "105: 116 43 | 127 110\n" +
            "44: 127 106 | 116 88\n" +
            "93: 33 116 | 56 127\n" +
            "82: 116 13 | 127 116\n" +
            "75: 2 127 | 9 116\n" +
            "65: 127 97 | 116 36\n" +
            "4: 5 127 | 98 116\n" +
            "1: 114 127 | 58 116\n" +
            "122: 54 127 | 59 116\n" +
            "70: 116 67 | 127 75\n" +
            "117: 127 127\n" +
            "68: 48 127 | 40 116\n" +
            "97: 127 95 | 116 113\n" +
            "132: 127 33 | 116 54\n" +
            "51: 23 127 | 30 116\n" +
            "119: 127 37 | 116 103\n" +
            "63: 57 127 | 59 116\n" +
            "34: 57 13\n" +
            "48: 116 73 | 127 47\n" +
            "23: 82 127 | 59 116\n" +
            "66: 127 82 | 116 78\n" +
            "95: 127 100 | 116 82\n" +
            "123: 117 116 | 82 127\n" +
            "24: 116 33\n" +
            "120: 49 127 | 99 116\n" +
            "103: 127 90 | 116 83\n" +
            "102: 72 13\n" +
            "58: 46 116 | 45 127\n" +
            "106: 56 127 | 117 116\n" +
            "85: 126 127 | 133 116\n" +
            "3: 74 116 | 85 127\n" +
            "20: 33 127 | 56 116\n" +
            "127: \"b\"\n" +
            "45: 116 102 | 127 18\n" +
            "126: 127 41 | 116 112\n" +
            "94: 116 87 | 127 21\n" +
            "114: 35 116 | 19 127\n" +
            "0: 8 11\n" +
            "99: 127 34 | 116 76\n" +
            "80: 116 127 | 127 116\n" +
            "50: 15 116 | 7 127\n" +
            "55: 127 91 | 116 59\n" +
            "90: 92 127 | 94 116\n" +
            "25: 57 116 | 54 127\n" +
            "124: 116 57 | 127 91\n" +
            "77: 78 116 | 78 127\n" +
            "56: 127 127 | 116 116\n" +
            "79: 116 59 | 127 57\n" +
            "12: 127 64 | 116 115\n" +
            "130: 17 127 | 33 116\n" +
            "8: 42\n" +
//            "8: 42 | 42 8\n" +
            "60: 116 72 | 127 59\n" +
            "113: 117 127 | 117 116\n" +
            "131: 16 127 | 56 116\n" +
            "46: 134 116 | 62 127\n" +
            "83: 6 127 | 50 116\n" +
            "33: 116 116\n" +
            "88: 116 57 | 127 54\n" +
            "118: 116 100 | 127 54\n" +
            "125: 100 116\n" +
            "121: 32 116 | 104 127\n" +
            "29: 127 17 | 116 57\n" +
            "101: 127 1 | 116 68\n" +
            "22: 116 117 | 127 100\n" +
            "43: 127 125 | 116 22\n" +
            "10: 29 127 | 63 116\n" +
            "112: 98 116 | 107 127\n" +
            "87: 80 127 | 57 116\n" +
            "129: 54 116 | 17 127\n" +
            "98: 116 80 | 127 117\n" +
            "57: 116 116 | 127 13\n" +
            "107: 127 72 | 116 59\n" +
            "40: 116 14 | 127 4\n" +
            "39: 12 116 | 84 127\n" +
            "133: 116 108 | 127 10\n" +
            "71: 127 56 | 116 54\n" +
            "76: 116 78 | 127 33\n" +
            "15: 127 59 | 116 78\n" +
            "42: 119 116 | 3 127\n" +
            "9: 127 72 | 116 100\n" +
            "72: 127 127 | 127 116\n" +
            "17: 13 13\n" +
            "16: 116 116 | 13 127\n" +
            "49: 53 127 | 25 116\n" +
            "62: 127 80 | 116 100\n" +
            "52: 72 116 | 33 127\n" +
            "37: 70 127 | 120 116\n" +
            "110: 89 127 | 87 116\n" +
            "96: 116 52 | 127 55\n" +
            "78: 116 127\n" +
            "108: 127 109 | 116 71\n" +
            "109: 116 59 | 127 16\n" +
            "59: 116 127 | 127 127\n" +
            "89: 57 127 | 33 116\n" +
            "\n" +
            "babababababababaabbbbbabaaabbabbabbabbaa\n" +
            "abbbbbaababbbabbbbbaaaaaaaababbbaabbabab\n" +
            "bbaaaaababababbaaaaabbababbabaabaaaaaaabaaaaaaaa\n" +
            "aabaabbaaaaaabaaaaaaabaabaabbbbb\n" +
            "bbaababbaaabaabaababbaaaabaababbaabbbaababbaaabababaabab\n" +
            "bbabbabbabaabbaabbbbaabbbbaabbaa\n" +
            "baaaabbbabaaababbababbbbababbbbbaaaabaabaabbbbaa\n" +
            "aaaabbabbaaaabbbaabaaabbaaabbbbababaabab\n" +
            "bbbaabaabbaabaaaaaababbbbaaaabbaaabaaabbbbbbbbbbbababbab\n" +
            "aabaabbaaabaaaabbbbaababbaabababaabbbbab\n" +
            "bbaabbbbbaaabbbbbabbbbaaaabaabbaabbaabbabbababbb\n" +
            "aaabaabaabaaababbabbabbb\n" +
            "abbababbabbababbaaaaaaab\n" +
            "ababbaaaabbabbabaaaabbaaaabaaabbbaabbbbaabbbabbbbbbaabbabbabbaaaababaabaabbabbaa\n" +
            "abbababaabbbabbbaaababbbabaaababbaababaabaaaaaaaaabababbabababab\n" +
            "baabbbbabaababbabaababbbababbbaa\n" +
            "aaabaabbbbbbaaaabaaaaabaaaaaabaaaababbaaaaaabaab\n" +
            "babababbabaaabbabbbbbbbabaaaabbaabababaaaabbbaab\n" +
            "aabaabbaabbabaababaaaaaabbbababaaabbbbab\n" +
            "aabaaabbbaabaabbabaaabbabbababaabbbbabbb\n" +
            "abababbaaaabbababaaaaabaaaaaabbaabbbaabb\n" +
            "bbaaabbbbaababaabbbaabbbabbbababbbbabbaa\n" +
            "babaaaaabbbbbbabbabaaaba\n" +
            "baaababbaabbbabbaaaababbababbabbbbbbbbbbaabbbaaaaaababbbbbaabbbb\n" +
            "bbbbaaabbaaaabaabbbbbbbabaaababaabbbbaaa\n" +
            "bbabbabbbbabaabbaaaaabab\n" +
            "baaababbbbbbaababaabaabbbbbbaaaaaaaaababaabbbaabaabaaaba\n" +
            "baababaaaababaaaaaaabbbaabbbbbaabaaababaaabbabaaaaabbaaa\n" +
            "abbbbbbbaaabaababaaabbaa\n" +
            "bbbabaaaabbabbbaaaaabaaabbbaabaabbbaaabbbabaaaba\n" +
            "abaabbaabaaabbabaaaaaaaa\n" +
            "aaabaabbaabaaaabaaaabbaabbabbaab\n" +
            "bbaabaaaabbaabaaababaabb\n" +
            "bbabaaababbaaabbbbabbabbabaaaaabbaaaabbbabbababbabbbbaaa\n" +
            "abbaaabbababbabbbaaaabbababaabab\n" +
            "aabababababbbaabaaabababababaaaaaaabbaaabbabaaba\n" +
            "abaaaaabbbbbbaabbaaaabababaaaabb\n" +
            "bbbbabbabababbbbaaaaaaba\n" +
            "aaaaaabbbaabbbababbabbbabaaabbbbabbabbaa\n" +
            "bbbababbbababbbbbbbbbabaabbaabaababbaaaabbababbb\n" +
            "abbabbabaaaabaaabaaabbbaabbaabababbbaabaaaaaaaabbaaababa\n" +
            "abbbabbbabaababbaababaabbabababbaaaaabaababaabaabaabbabbbabbbbbb\n" +
            "bbbaabbbbbbababbaaabbabaabbbbababbaababbabbabaaababaabbb\n" +
            "bbaaaaababbaaaaaabababaaaababbbabaabababbabbbaaa\n" +
            "aabbbabbbbabaabbabbaabba\n" +
            "ababbababbbbaaaabbabbaab\n" +
            "abbaaaababbbbbabbaaabaaabbabbabbbabababbabaabbba\n" +
            "bababaabbbaabaabbaaabbaa\n" +
            "bbbaaaabbababaabbbababab\n" +
            "babaabbbbaaababaababaaabbbaabbab\n" +
            "bbbabaaabaaaababbaabaaabbababbbbbaabbaab\n" +
            "abbbbbbbaababaabaabababb\n" +
            "abbbabaabababababbaaaaabaaabbabb\n" +
            "baaabbabbbbaababbbaabbba\n" +
            "bbbbaabababaabbabaabbbaaaababbab\n" +
            "abbaaaaaaabaaabbbaaabbababbaaaaa\n" +
            "aaaabbabaababbababbbbaababbbbaaa\n" +
            "abbbbbbbbbbaaabbbabbabbb\n" +
            "abaaababbababbbbbabaaaabbabbaabbaaaababb\n" +
            "bbaabbbabbbabbbbabaabbbb\n" +
            "babbaababbbbbbabaababaaabbaabaababaaaaba\n" +
            "bbbaaaabbbbbaaabbaabbaba\n" +
            "ababbaaabbbbbaabbbaabbbbbaabbbbb\n" +
            "baabbbabbaababbbbbbabbbb\n" +
            "abbaabaaaaaabbabbaaabbabababbaaaababbbbbaaaababb\n" +
            "bbbbbaabbbbaaabbbbabaaba\n" +
            "bababbaaaababaaabbbabbba\n" +
            "bbbbaabbaababaabababbbaa\n" +
            "bbbaaaaaabaabbabaabaabaa\n" +
            "bbbaaabbbbabbabbaababbbb\n" +
            "aabababababbaabaabbaaaaaabbabbabaabbabab\n" +
            "babbbabbaaababbbbbabaabbabaaaabb\n" +
            "aababaaabbbbbaaabaabbabb\n" +
            "bbbbbaaabaaabbababbaaaaaabaabaaa\n" +
            "bbaaaaaababaaabbaabbaaaa\n" +
            "baababbbaaaabbaaabaabbba\n" +
            "aababaaaabababaaaaaabbabababbbbb\n" +
            "bbaababaabbababbbbbbbaaaabbbaabbabbbaaaa\n" +
            "bababbbbaababaaabaabbbbb\n" +
            "bbaaaaaababaaaababababaaabaabbba\n" +
            "bbbaaaaaabbaabaaabbaabaaabbabaaababbbabaaabbaaababaaabbb\n" +
            "aabbbbbbbbbaaaaaaabbabbb\n" +
            "bbabaababbbaabaabaaaabbaabbabbbbbabaabbabbbababaaababbababbabbbabbabbbbabbabbbababbabbaababbaaaa\n" +
            "abaaababbabbbaabbabaabbaabaaabbaabbbaaabbaabbaabbbaaaabb\n" +
            "aabaabbbbbbaababbabbaababbbbbbaa\n" +
            "baabaaabbbbbbaaaabbbbbabaabbbaba\n" +
            "bbbbbbbabbaababbabababab\n" +
            "aaabaaabbbbbbababaaababbbbbbbbabaaababababbbbaaaabaababaaabaabababbaabba\n" +
            "abababaabbaabbbbaabbaaaa\n" +
            "bababbbbbbbbbaabbaaabbaa\n" +
            "abbbbbbaaabbbaaaaabbabab\n" +
            "baaaababbbbaaabbaaaaaabbababaabb\n" +
            "abbbbbabbbabaabbbabbaabb\n" +
            "aababbbabbbbaabbaababbaa\n" +
            "bbbbaabbbbaaaabaaabbabaa\n" +
            "abbaaaabbbaaabaabbbababbbbbbbbbabbbaababbaababbabababbabbbbabbbabbaaabba\n" +
            "aaabbabaababaaaaabababbaabbababbbbbbbababbbbbabb\n" +
            "abbbbbababbbaaabaabbaaba\n" +
            "abbbbbbaaabbaaabaabbbbbaaabbaababaaaaaaa\n" +
            "ababaaaaaaababbbaaabaaaa\n" +
            "babbbbaaabaaababbbbbbbaa\n" +
            "abaaaaabbabaaaabbbbbbbaa\n" +
            "bbbaaaabbbbbabbababbbbbb\n" +
            "baabbaaaabaabbaabbbbbbaaabaaabababbbbabaabbbabbaaaababbaaaababbaaabbaaababbbbaba\n" +
            "bababbbabaaababbbabbbbaaaaaabbbaabbabaab\n" +
            "bbbbaabbbababbbbabbbabbbaabbbbba\n" +
            "aaabbbbaabbbaaabaababbaa\n" +
            "aaabbabababbbabbbbbbabbb\n" +
            "ababaaabbbababaabbbbbbbbbabaaabbabbbbbbbababaabb\n" +
            "abbbaaababbbbabaabbaaaaabbbabbaaaaabbabb\n" +
            "baabbbababbabaaabbbbbaaaaaabaabaabaaabbababbaaab\n" +
            "aabaabbabababbbbbbbababbbbabbbabbbaaabba\n" +
            "baaababbbbbaabaabaabbaba\n" +
            "abbbbabaabbbaaabbaabbbabbbbbaaabbbababab\n" +
            "babaaabbabaabbababaaabaa\n" +
            "abaaabbabaaaaaababaaabababaaaaabaabababb\n" +
            "abbbabbbaaababbbababaaaabbabababbbaabbab\n" +
            "aaaaabaaabbbbbbbbaaabbbaabbbbbbabbaabbabaaabbaab\n" +
            "bbbbbbbabbbaaabbaaabaabaababbabaabaaabaaabaaabbb\n" +
            "abbbbbbaabbabbbabbbbabbabbbbabbaaaaababb\n" +
            "bbbbbaaababbbaabaabbbbbbbbbbbbbbbababaaa\n" +
            "abbbbbbbbbbbbaabaabbaaba\n" +
            "abbaaaababbbbbaaabbabbbaaabaabbabbbbbbbababaabaa\n" +
            "abaaabababbbbabaabbaabbaaabababaaabbbabbabbababaabbbaaaaabaabbaaaaaaabba\n" +
            "bbaaabbbaabaaabbbbbaaaaababbabab\n" +
            "baaabaaabaababbabbabaaabaaabbabb\n" +
            "bbbbabbaabababaaabbbaaba\n" +
            "aabbbabbbaabbbbaaaabaaab\n" +
            "abaaabbabbaabaabaababababbabaaabbbbbbabbabbbbbbbbbbbbbbbababbbaabbabbaababbaaaba\n" +
            "bbbaabbbabbbaaabbbbbbabaabaaaaba\n" +
            "abbbbabababbbaabaaaababa\n" +
            "abbabbbaabbbbbbbaaabaabbbbbbaabaaabbabaabaabbbbb\n" +
            "babbbabbbbbaaabbabbaaaaaabbaaaabbbbbbbbb\n" +
            "bbbbaaaaabbabababbaaabbaabaaabbaababababbbbabbbabbaaaabaabbaabaaabbababb\n" +
            "abbabbbabaabbbabaaabbaab\n" +
            "bbaababbbaababaaaabaabab\n" +
            "bbbbababaaabababbbabbbbbbabababaabbaabaababbaaab\n" +
            "baaaababaaaaabbaaaabbbbbbbaaaaabbabbababaaaabbbb\n" +
            "baaaaaabbbaaaaabaaabbaabbabbbaaababbabba\n" +
            "abbbbabababbaabaababbbab\n" +
            "aaabaabaaaabaaabbbbababbaabbaaaabbbabbaa\n" +
            "aaaaaabbbbaaabaaaaaaabaabbbabababaabbaaa\n" +
            "abbbbbbbabbbbabaaabbabaa\n" +
            "bbaaaaabbaababbabbbaabba\n" +
            "abbbbababaaababbbbbbabab\n" +
            "abbababbababbbabaabbaabbaaaaaaaaaaabaaaa\n" +
            "bababbbbbbaaaaaaabbbabba\n" +
            "baaabbabbbbbabbabaabbaababbbaaaa\n" +
            "baabaaababbbaaabbaabaaabaababbbbababaaba\n" +
            "aaaabbaabbbaabbbbaabaabbbaabbaaa\n" +
            "baaabaaaabbbbbabaaabaababaabababbaaababa\n" +
            "abbabbabbaaaaaabbaaaabaababaaaababbbaabb\n" +
            "bbaaaaaaaababbbaaabaaaabbaaaabaababaabaa\n" +
            "baaaaaabaaaabbbaaabbaaba\n" +
            "aababababbbbbaaababababbbbaaaaaabbaaabba\n" +
            "bbbaaabbbabababbbababbaaaaabbaaa\n" +
            "baababaabababaabbbaaaabb\n" +
            "bbaababbaababbbaaababbbb\n" +
            "aaaaaabbaabbbbbbbbaaaaabbaaaababbbbababbbbaaaababababaaaabbbbaab\n" +
            "baaabbabababbabababaabbaabbaabaaabaababbbbabaaabbabbbaaaaaababbabbabbbabaabbbaab\n" +
            "abbbaaabbababbbbabbbabba\n" +
            "aababaababbbbbbbabbababbbbbbbaba\n" +
            "abbbabbbbabaaabbbbaabbbbababbaaaaabababaabbaababbbbbbbaababbaabbbbbbbbaaabababbb\n" +
            "bbbaaaaaaababbabbbbbabaabaabbbabaabbbbbbbababbbb\n" +
            "baababbabaaabaaababaaabbbaabaabbbbbaaabbbabbabaaababaaba\n" +
            "aaabaaababbaaaaaaaabababbabaaaba\n" +
            "abaaababaabaaaabaaaabbbb\n" +
            "bbbaabaababbaababaaaaabaaaabaababbababab\n" +
            "bbbbbabaabbaaaaabaaaaabaabaabaab\n" +
            "baaaabaaabbabaabbbbbbbbb\n" +
            "bbaaaababbbbbbabbaababbaabababbabbbbaaaaabbbbbaababaabaabbbaaabaaaaababbababbbaaaaababba\n" +
            "bbbababbababbabbbabbbaabaabaaabbbababaabaabbaaaa\n" +
            "bbbbaabaaaaabbabbababbab\n" +
            "baabaaabbabaaaaababaaaba\n" +
            "abbbabaabaabbbbaabaababa\n" +
            "abbbbbaabbbbbbabbbbbaabbbaaababbabbabaaaaabababb\n" +
            "bbbbaaabaaababbbaaaaabab\n" +
            "bbbaaaaabbbbaabababbbbba\n" +
            "abbabbbaabbbabbbbaaaabbababbaaababbbaaaa\n" +
            "aaabbaaababbbabaaaababaa\n" +
            "ababaabbabaaaaaaaaaaababbbaaabbbbbbababababbabbaaaaabaabaaabbbab\n" +
            "baababaabbbaabaabababbaabaabbbbabbbbbabb\n" +
            "abbbabbbaabababaabbbaabb\n" +
            "baaaabbaaababaabaabaaabbabbabaabbaaabbbababbbaaa\n" +
            "baababaababbbbaabbaaaaaabbbbaaababaabbaaabaaaaabaaaaabbb\n" +
            "abbabbbbbaaabbbbabbbaaaa\n" +
            "abbbbabababaaaaaabbaaaaababbbbbabbbbabab\n" +
            "bbbaaabbabaaaaaaaaaaaabbababaaaabbaaaaabaabbaaaa\n" +
            "bbbbaabbbababbaaabababbabaaaabaabaabaaaa\n" +
            "baaababbabbaaaababbabbaa\n" +
            "abaaaaabbbabaaabaabaaaabaaabababbbaaababaaababaabbaaabab\n" +
            "bbbaaaabbababbbbababbbba\n" +
            "baaababbbababaababbbbabb\n" +
            "aaabbbbbabbabaababbaaaabbaabbaba\n" +
            "baabaabbaaababababbbbbaababababbaabaabbaaabaaababbabbbaabaabbabbaabbabba\n" +
            "baaaabbbbabaaaaabbabaaabaabaaaabbbaaaababbaaaabaabaabaaaabaabaaaabbbabbaaabbaaab\n" +
            "bababbbbbbbbaabbabbababbbabaaaaabbbabaab\n" +
            "baaabbababaabbababaabaab\n" +
            "abbaaabbbbbbaabaabbbbababababbbbababaaba\n" +
            "bbbbaabaabbaabbaabaabbbb\n" +
            "baababbbbbbbbababaaaabbbaaaaabbb\n" +
            "aaaabbabbabbaababbabaaababbaabab\n" +
            "aababbbabaabaabbbbbaabaabbabbbaa\n" +
            "abaabbaabbaabbbbaabbaabb\n" +
            "bbbaaaabaaabbbbabaaabaaabbababbbaabbbaab\n" +
            "abaabbaaaaabaabbbbbbbababbbbbababbbaabbbbbaabbba\n" +
            "baababbbbbbababbaaaaabaababbbbba\n" +
            "abbabaaaaababaabababbaaabbaaabbabaaabbaa\n" +
            "abaaabbaabababaaaabaaabbbababbbbbaabbbbbaabbabbaababbbab\n" +
            "bbaabbbbbbabbbbaabbaabaababbbaababbababbbbabbabaabbaabbbaabbaaaa\n" +
            "baabababababaaaaaabbabba\n" +
            "babababbbbaaabaaabaabbbb\n" +
            "abbabaaaaaaaabaaabaaabbababbbbaabababaabaaaaaaaaaabbbbbaaaaabababbababba\n" +
            "bbabbabbabaaabbaaabbabab\n" +
            "aabbbbbbbbaababbbaaabaaaaabaaaba\n" +
            "bbaababbabbaaaabababaaba\n" +
            "bbabaabbbaabbbbbabaaabaabbbbbbaa\n" +
            "bbbabaaabbbaaaababababbaabaaababbbbaaabbbbbabbaabbabaababaaababa\n" +
            "abababaaaababbbababbabab\n" +
            "bbbbbabaabbababbbbabbabbaabbaaaa\n" +
            "babbaabababaaaabbbaabbaa\n" +
            "bbbaabbbbababababbabbaab\n" +
            "bbbaabaaabaaaaaaababbbbbbbaaaabababbabababbaaabbababaabbabbaabaabbbabbaa\n" +
            "bbbbabbabababbaaaaaabaab\n" +
            "babbbaabbababbabaaababbbaaababbaaabbaabb\n" +
            "abaabbababaababbabbbaaabbbbbabbb\n" +
            "bbbabaaabaaaabbababbbabbabbbabbbabbbbbbabbabbaaa\n" +
            "bbabaabbaaabaabaabbabbabaabaaabbaabbbbaa\n" +
            "bbababaabaaaaaabbabbbbaabbaabaabaabbbababbaabbba\n" +
            "abbaaabbbaaabbbbbabbbabbaaabbbaa\n" +
            "bbbabaaaabaaabbaabbabbaa\n" +
            "abbbbbbaabbabaaaababbbab\n" +
            "abbbabbbaaaabaaaabbbbbaababaaaababbabbbaaabbbbba\n" +
            "abbabaabbaaabaaaaabaabbbaaabaabaabbbaaaa\n" +
            "bbbaaaaaabbaaaaabaabbbaaabaaaababaaaaabb\n" +
            "baabbbbabababbbabababaabbaaabbabaabbbbaa\n" +
            "aabaabbbabbabbabbabbabba\n" +
            "babbbabbbbbaabbbaababbbabbabaaababbaabab\n" +
            "bbbaabaaaaaabaaaabbbabaababaaaaababaabaaaabbaaabbabbabbb\n" +
            "aabaabbbbaabababaaaaabbb\n" +
            "baababaabaaabbabbaaabbaa\n" +
            "abababbabaabbbabbbaaaabaabbaabbabbbbabbb\n" +
            "abbbabbbabbababbbababaaa\n" +
            "bbabbbbaabababbaabbabaabababbaaaaabbabaabbbbabab\n" +
            "aaabbabababbbabbaabbbbab\n" +
            "bbaabababbaaabbbaabbbbab\n" +
            "baaabbaababbbbbaaabaaabaabaabbbababbaaab\n" +
            "bababbbbbbbbaabbbbbbabaa\n" +
            "bbaaaabaaaaabaaaabbababaaabbbaab\n" +
            "aaabbaabaaaabbabbbaaabababbbbababbbaababbbaaaabbabaabbab\n" +
            "ababbaabaabbbbbbbabaaaabaaaaaaab\n" +
            "baaabbbaaaabbabaababbbab\n" +
            "bbbbaababaabaaababbbabbbbaaabbbbaaaaaabbbabaabaaaabbabbb\n" +
            "bababbaaaaaabaaabbbaababaaaaabbaabbaaaaaaabaaaaaabaabbbbbabbbbababbbaabb\n" +
            "aaabbababaaabbbbabbaaaaaaaaabbbaaaabbbaababaabbb\n" +
            "aabaaaabaaaabbbabababababbbbaabaaabaaaaa\n" +
            "aaaabbaaabbabbbaabbabaaababababbabbabaaaabbaabbb\n" +
            "aaaabbbbbbababbaabbbaabbbabbbbaaabbaaabbbababbaabbbababbabbbababbaababbbaababbaaaaababbb\n" +
            "babababbbbabaabbbbaaaabb\n" +
            "abaabbabaaabaababbababaabbabaaababbbbaaa\n" +
            "aaaaabaabaaaabaaaaabbaaa\n" +
            "baaabbbaabbaaabbaababaabbbaaaaaaabbbaabb\n" +
            "babaaaabbaabbbabbbbabaab\n" +
            "baaababbbbbbbaaaaaaaabaaababbbabaaaaabab\n" +
            "bbbaabaabaabbbabbbaabbba\n" +
            "aaaaabbbabaabbbaabaaabbaabbbbbabaaaabaabaabbabbbbbbabbbbbbabbbbaaaaabbaa\n" +
            "abaaaaabbabbaababbaababbaaaaabaaababbaaaaaaabbbbbaabbaaa\n" +
            "babaaaabbbaababbbaabbaaa\n" +
            "babbbbaaabaabbaababaaaabbbababaabbbbbbaababaabaa\n" +
            "baaaaaababababaabbbabbba\n" +
            "bbaaabaabbaaabaaaabaabbaabbaabbaaabbaaaa\n" +
            "aaabbbbbaaabaababbbbbaababbbaaababbabbbabbbbbbbbaaaababbabbbaaba\n" +
            "bbaabaabbbbbbaaabababbab\n" +
            "baaabbbbbbbbbabbbaaaaabb\n" +
            "aabbbabbbaaaabbbaabaaabbaaabaabaabaabbbbbaabaaba\n" +
            "abbaaaaabbbbbaaaaaaabbbababbabba\n" +
            "bababbbaababbabaababaaab\n" +
            "bababbaaabbbbbabbbabbaab\n" +
            "bbabaabbaaabaababbbbabbaaaaabbbaaaabbbbabbabbaab\n" +
            "bbabaabbbaaaabbaabbbbbaababbbbaaaabbbbba\n" +
            "babaaaabbababababbaaaabababbaaab\n" +
            "bbbbaabaaabababaaabbbbbababbbababaaaaaabbbaabbabaaabbbaa\n" +
            "baaabbbbabbababaabababbaabbaabababaabaaa\n" +
            "bbabaaabaababababaaaababbbbbbbababbaababaabbbaab\n" +
            "bbbbbabbaababaaabbbbbababbbababa\n" +
            "bbbabaaaaabbbbbbbaabbaab\n" +
            "bbbbabbaabbbbbababaaabbaaaabbbbbbbbbbabababbaabb\n" +
            "abbabbabbbbbabbabaaaaaaa\n" +
            "bbaabaabbabbbbaabbbabbaababbbababbababbbbbbbabbb\n" +
            "abababbbaaaababbbabbaabb\n" +
            "bbabaabbaaaaabaaabbabaabaabaaaba\n" +
            "bbabbbbaaaabababbbabbbbb\n" +
            "bbaaaaabaaabbabaabbaabaaaabbbabaaabaaaaa\n" +
            "baaaaaabaabaaabbbaabababaaabaaababaaabbbbabaabaabaabbaba\n" +
            "bbbababbbaababbabaaaabaabbbaabaaabababbbababbbbb\n" +
            "bbaababbbabbbabbaababbbababababbbbbaaaba\n" +
            "baaabababbabbabbabbbbabbbabaaabaaabbaabababbbbbbbaabbbaa\n" +
            "ababaaaabbbbbabaabaabbabababbabbaabbabba\n" +
            "bbabbabbbabababaaabbaaba\n" +
            "baaabbbaabababbbbaabbabbaabbaabbbababbbaabbaaabbbabaaabbaaabbbab\n" +
            "aabababaabaaabbaaaaababb\n" +
            "babaabbaaaabbbbbaaababaa\n" +
            "aaabaaababbbbabbabbabaababababab\n" +
            "abaaaaaabbabbabbababbaabbabaabaa\n" +
            "aaaaabaaabababaabbababba\n" +
            "babbaabbaaaabababbabbbbbaaaababa\n" +
            "bbaabbbbbababaababaabbbb\n" +
            "bbaaaaaabbbbaabbbbbaaabbaaabbaabaababbbb\n" +
            "bbabbbbaaababbaaabbabbaaabbbaabaaababbab\n" +
            "bababababaaaabaaabababbb\n" +
            "baaabbbaaabaaaaaaabbbbba\n" +
            "baabbbbabbbaabaaaaaabbababbaaaaabbaaabbb\n" +
            "babaabbabbbbbabaabaaaaaabbbabbaaabaabbba\n" +
            "babababbbabbbbaaabbaaaababaaaaabababbaaaaaabbbabababbbabababbbbbbbbbabbb\n" +
            "aabaabbbaaabbbbaabbabbabbabbababbaaaaabb\n" +
            "baaabaaabaaabbabaaaabbabbbaabbaaabaabaaa\n" +
            "aaabaababbbbbbabaabbbbbbaaaabaab\n" +
            "aabbabbbbbbbbbbbababbbabaaabbbaa\n" +
            "bbbaabbbababbababbbbaaabbababbbabbababba\n" +
            "bbabbbbabaaabaaababababbaabbaaaa\n" +
            "abbabbabbaaababbbbbaaaba\n" +
            "baaabbbabbbbabbabbbbabbb\n" +
            "abbbabaabbaabaaabbbaaaababbababbabbbaaaaaabbaabb\n" +
            "bbbbbaaabababbbabbbaaaba\n" +
            "baaaababbbaababbaababbaa\n" +
            "baabbbbabbaabaaaaabbaaba\n" +
            "abbbabaabbbaababbbabaaabaaaabbaabbabaaba\n" +
            "bbababaabbbabaaabababbbbabaaabbb\n" +
            "ababbaaaabbbbbabbbabaaba\n" +
            "bbbbaabbbbbaaaabbbaabbbaaaabbabb\n" +
            "bbbbbbababbabbbbabbaaaababbbbbbbbbabbbbb\n" +
            "abbaababbbaabbaaaaabbbababaababaabaaabaaaaaababb\n" +
            "baaabaaabbbbabbababababaaabaaabbabbaaabbbaabbabb\n" +
            "baabbbaaaaaaabbaabaaaaababbabbabbbaaabab\n" +
            "ababababbabbaabbbabbabbbbabaaaaaaaaabbbb\n" +
            "baababbabbbaabbbabaaabbaabbbabba\n" +
            "bbaabbbbbbaababbbabababbbbbaaabbabbaaaaabbbbbbbbbbbbabaaabbbaaaa\n" +
            "baaaaabaaabaaaabaaabbbbaababbaabbbbbbabaaababbaa\n" +
            "abaabbaaabaababbbbaabbaa\n" +
            "baaabaaabbaaaaaababaabab\n" +
            "bbbbaaabbabababbbbaaabba\n" +
            "aaaaaabbababbabbabbabababbabaabbaabbabbbbbbaaababbabbaba\n" +
            "bbbbbabababaaaabaabbaaaa\n" +
            "baaaabaaaaabbbbbbababbbbbbbaabbbbaaabaabababbbaa\n" +
            "bbaaaabaabbbbbaabbaaabaaaababaaabaaaabaababbaaab\n" +
            "bbaabbbbbbaaaabaaaabbbbbbbaaabaaababbaaaabbaaaabaabaaabaabbbabbaababbbab\n" +
            "bbbbaababaaabaaaaabbbbba\n" +
            "aaabbbbaabababaabbaababaaaabababaaaaaaaa\n" +
            "bbaabaabaaaabbbabababbaaabbbbbaabbabaabaaaabbbab\n" +
            "aaabaaabbbaabaaabaaabbababaabbabbbabbbaa\n" +
            "abbabaababbabbbabbbaaabbbbbababa\n" +
            "aaabbbbbaabaabbababbbbba\n" +
            "baaaabaabbaababbababbbba\n" +
            "bbbbbbabbabbbaaaababababaababbbaaababbbabaabbbbaabaabbaaabbbbaaa\n" +
            "abbabaabbaaabaaabaabbaaabaabbaaababbbbbbbbbbaabaabaabbab\n" +
            "abbbbbbbbbbababaaaabaaaaabaababaababbbbababbaaaa\n" +
            "ababbabababbbaababbabbbaabbabbbbbbabbaaa\n" +
            "baaaabaababaabbabbaaaaaabbbaaaaaabaaabababaaaabbbabbaaaa\n" +
            "abbbbbaababbbaababbaaabbbbaaabbbbbababab\n" +
            "aabaabbaabbbbbababaabaaaabbaaabaabbaaababbbbabaaabbababbabbbbabaabbbaaaaaaabaabb\n" +
            "babbaababababababbaabbba\n" +
            "abbabaabaababbbababaabaa\n" +
            "bbaabaaabaabababbaabababbaaababbbbbaaaabbabbbbbbababbbbaababbbbbbabbbaba\n" +
            "aaabaaabbbaaabbbbbaaabaabaaaababbbabbbabababababbbbabbaa\n" +
            "bbbbbbaaabbbabaababaabaaababbbabaaabbbbababaaaabbbbbbbbabaaababbbaaabaabbbbbabbaaaabaaba\n" +
            "aaabbbbabbbaabaaabbaabaabaaababaaabbbaab\n" +
            "abbaaabbbbbbaabbbaababaababbbbba\n" +
            "bbaaaaabbbbabaaabaaabaab\n" +
            "bbbaaaaababababbbaaabbbbbaababbabaabaaaaababbbbabbababba\n" +
            "ababbaaabbbbbbbaabaaaaaababaabaaabaaabaa\n" +
            "baababbbbaaababbaabbbbba\n" +
            "babbabbbabbabbaabababbbbaaaabbbbbbbbaaabbabbaabaaababbab\n" +
            "bbabbabbabbaaaabbaabbbaaaaabaaababbbbbabbabbabba\n" +
            "bbbbaabababababbbabaabab\n" +
            "abbbbabbababbaabaabbabab\n" +
            "aaabaabbbbbabaaaaababbab\n" +
            "baaaaabaabaaabbabbaabbbbbaaabbaa\n" +
            "bbbbaabaababbababbbabbab\n" +
            "aababaaabbbbbbbabbbababbbabbaabbabaaabbb\n" +
            "aabbbaaaaaaaabaabaaaaaabaabbbbbbaabababb\n" +
            "aaaaaabbbaabaabbaabbabba\n" +
            "aaaaabbaaaabaabbabaababa\n" +
            "aaabababbbbaaabbabbaaabbbbbaaaaababaaaaaababbababbbabaab\n" +
            "abaababbabaabbabbbbaababbbaabbba\n" +
            "aababaaabbbbbabaabbabbbbabbabbbbaaabbbbbabbababbbbaaabababaaaaba\n" +
            "aaabaaababbababbaaaabbbabbbbabaaaabbbaba\n" +
            "bbababbbaaabbaababaabbbbaaaabbbabaaaabbababbabbbabbaaaaaabbaaabababaabba\n" +
            "aaaabbaabaaaaaabbababbbbaababbaa\n" +
            "abaababbaaabbbbabbaabaabbbbbaabaababbbabbabbaaab\n" +
            "aababbbbaabbaaaabbaabbabababbabaaabaaaaabaaaabbbabababab\n" +
            "babbbabbbbbaaabbaabbabbb\n" +
            "bbaaaaaaaabbbabbaabaabbabbbbaaaabbababba\n" +
            "bababbbbbbbaabbbbbbabbab\n" +
            "bbbaaabbabbbbbaaababbbbb\n" +
            "abbbbbbbbbbbbaabaabbabbabbbbbabbaabbabbaabaabaababbbabbbbbbbbbaabbbabaaaabbbaaba\n" +
            "aabbbbbbaaaaaabbbbbbaaababbbbbabbbaabaaabbbbbbbb\n" +
            "bbaabababaababbbaaabbbab\n" +
            "bbbbbabbbbbbaabababbabbb\n" +
            "bbabbabbabababbaaaababbbbaabababbaabbabb\n" +
            "aabbbbbbbaaabbabaaabbbbbbaabbaaaaaaabbbb\n" +
            "bababbbaababbaabbaaababbabbabbbbbababbbaaaabbbbbabbabbaa\n" +
            "aabbbaaaaaaabaaaaababaaababbbbabaabaabab\n" +
            "abbaabaaaaabaaabbbaaabbbaaabbbbbbbbbbbaaaabbaabaababbbbb\n" +
            "bbbabbbababababaabbabbbaaaaaabaaaaaaabaabbbbaabaaabbabaabaababbbaaaaabaaabbbbbbbbaaabbaabaaaabaa\n" +
            "baabaaabbaabbbaaaaabbbaa\n" +
            "babaaaaaabbabbababbbabba\n" +
            "bbbbaabbbbabbbbaaaabbbbb\n" +
            "aabaaabbbabbbabbbbabaabbaabbbabbbbaaabaabababaaabbabbaaaabbaabbaaabababb\n" +
            "bababababbbbaaaabaabbbbaaababbaaaaabbaab\n" +
            "bababaabbaaaababaaaaaaba\n" +
            "abbababbbabbaabababbbbaaaabababaaaabababbababaaa\n" +
            "abbaabaaabbabaabbbaaabbbbaaabbbb\n" +
            "abaaababbbaababbabbbabbbaaababaa\n" +
            "bababbbbabbbbababbbabbab\n" +
            "baaabbbababbbabbaabbbbba\n" +
            "aaaabbbabbaaabaaaabbbaaabbababba\n" +
            "bbbaaaabbbabbbbaabbbabba\n" +
            "abaaaaabbbbaaaaabbabaaabaabbabbababbbbbb\n" +
            "aaaaabbaaaabababbbbaaaba\n" +
            "ababaaaaabaabbabbbbaababbbbbbbaababbaaaa\n" +
            "babbbabbabaaaaaabababababaabaababbbabbaa\n" +
            "aaaaaabbaaabaababbaaabba\n" +
            "baabbbaabbababaabbbbaaaabbaabaabbabaabbbaaaababa\n" +
            "abbabaaabaaaabbbaaaaabbb\n" +
            "aababbbaabbbbbabbbababaabbbaabababbbabaaabbbbaab\n" +
            "abbabaaababbabaaabbabbabbaabbbab\n" +
            "bbabbbbaabbbbbabbbaabaabaaabaababbbabaababaaabbbabbaabba\n" +
            "abaabbbabaabbabababaaabbbaabbabaaaababaababbbaabaababbab\n" +
            "abababbabbbababbabbbbabbbabaabab\n" +
            "aaaaabbabaaabaaaabaaabaa\n" +
            "aaabbbbabbabbabbababaaaaaaaaabaabaabbbbb\n" +
            "ababbababbbbabbabbbaaaaabbabbaba\n" +
            "abbbbbbababbbbaaabbabaabaabababb\n" +
            "bbaaabaaaaaabaababaabbabababbaaabbaababbaaabaababbaababbaabababbbaaababaaabbaaabbbbbbaaa\n" +
            "aaaabaaabaababbbabbbbbbaabaabaaa\n" +
            "bbaabaabaababaaaabbbabaabbaabbab\n" +
            "abbbabaaaaaaaabbaababbbaabaaaaababaaaaaabbaabbaa\n" +
            "abbabbababbbabbbabbbabbbaaaabaaabbaabbaaabbbabbabbbbbbbb\n" +
            "bababaabaababbbaabbababaababaaab\n" +
            "bbaaabaaababbaaabbaabbba\n" +
            "aabaabbaabaaaaaabbababbb\n" +
            "ababbbbbbbbbbabbbabbaabbbbbaabababababbabbaabbabaaaabbbbabbbbaab\n" +
            "abaabbaaabbababaabbbaabb\n" +
            "ababaaaaabbabbbababaaaba\n" +
            "baabaabbabaabbaabaababaaabbbbbbbaababaaabaaabbaaabaabbbb\n" +
            "baaabbabaaabbbbbaababaabbabaaabaaaababaa\n" +
            "bbaaabbbbbbbaaabaaaaaaba\n" +
            "aabaaaababbaaaabbbbbbabbaabbaaba\n" +
            "abaabbababbaaaabbababbbaaaabbabb\n" +
            "bbaaababbaabbbababaaaaabbababaabaaaabbaaababbaaaaaaaababaaabbaab\n" +
            "aababbbabababbbaabbbabba";

}
