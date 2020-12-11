package seliv.aoc.aoc2020;

import java.util.*;

public class Aoc8 {
    public static void main(String[] args) {
        List<List<String>> in = InputParser.parseInput(IN);
        List<String> ops = in.get(0);

//        Map<Long, Command> program = new HashMap<>();
//        for (long i = 0; i < ops.size(); i++) {
//            String s = ops.get((int)i);
//            String[] ss = s.split(" ");
//            Operaion oper = Operaion.valueOf(ss[0].toUpperCase());
//            long arg = Long.parseLong(ss[1]);
//
//            Command cmd = new Command(oper, arg);
//            program.put(i, cmd);
//        }

        Map<Long, Machine.Command> program = Machine.parseProgram(ops);

        for (long i = 0; i < ops.size(); i++) {
            Map<Long, Machine.Command> programCopy = new HashMap<>(program);
            Machine.Command command = programCopy.get(i);
            if (Machine.Operation.ACC == command.operation) {
                continue;
            } else if (Machine.Operation.JMP == command.operation) {
                Machine.Command newCommand = new Machine.Command(Machine.Operation.NOP, command.argument);
                programCopy.put(i, newCommand);
            } else if (Machine.Operation.NOP == command.operation) {
                Machine.Command newCommand = new Machine.Command(Machine.Operation.JMP, command.argument);
                programCopy.put(i, newCommand);
            }

            Machine.runSingle(programCopy);
        }
    }

//    private static void check(Map<Long, Command> program) {
//        long a = 0;
//        long pc = 0;
//        Set<Long> visited = new HashSet<>();
//
//        while (!visited.contains(pc)) {
//            visited.add(pc);
//
//            Command command = program.get(pc);
//            if (Operaion.NOP == command.operation) {
//                pc++;
//            } else if (Operaion.ACC == command.operation) {
//                a += command.argument;
//                pc++;
//            } else if (Operaion.JMP == command.operation) {
//                pc += command.argument;
//            } else {
//                throw new IllegalStateException("Unknown operation: " + command.operation);
//            }
//
//            if (!program.containsKey(pc)) {
//                System.out.println("a = " + a);
//                return;
//            }
//        }
//    }

//    public static class Command {
//        public final Operaion operation;
//        public final long argument;
//
//        public Command(Operaion operation, long argument) {
//            this.operation = operation;
//            this.argument = argument;
//        }
//    }
//
//    public enum Operaion {
//        NOP, ACC, JMP;
//    }

//    public static String IN = "nop +0\n" +
//            "acc +1\n" +
//            "jmp +4\n" +
//            "acc +3\n" +
//            "jmp -3\n" +
//            "acc -99\n" +
//            "acc +1\n" +
//            "jmp -4\n" +
//            "acc +6";

    public static String IN = "acc +40\n" +
            "acc -14\n" +
            "nop +386\n" +
            "jmp +262\n" +
            "acc -4\n" +
            "nop +25\n" +
            "jmp +500\n" +
            "acc +13\n" +
            "acc -1\n" +
            "acc -7\n" +
            "acc +37\n" +
            "jmp +319\n" +
            "acc +46\n" +
            "jmp +429\n" +
            "acc -4\n" +
            "acc -8\n" +
            "jmp +335\n" +
            "acc +12\n" +
            "jmp +78\n" +
            "acc +16\n" +
            "acc -11\n" +
            "nop +137\n" +
            "acc +41\n" +
            "jmp +210\n" +
            "jmp +83\n" +
            "acc +7\n" +
            "jmp +48\n" +
            "nop +374\n" +
            "acc +11\n" +
            "jmp +268\n" +
            "acc +1\n" +
            "acc -17\n" +
            "acc +15\n" +
            "jmp +178\n" +
            "acc +23\n" +
            "jmp -5\n" +
            "jmp +374\n" +
            "acc +8\n" +
            "acc +5\n" +
            "nop +231\n" +
            "jmp +1\n" +
            "jmp -22\n" +
            "acc +44\n" +
            "acc +39\n" +
            "jmp +415\n" +
            "acc +44\n" +
            "acc -8\n" +
            "acc -10\n" +
            "jmp +36\n" +
            "nop +385\n" +
            "acc +6\n" +
            "jmp -37\n" +
            "nop +245\n" +
            "acc +5\n" +
            "jmp +261\n" +
            "acc -3\n" +
            "jmp +23\n" +
            "acc +37\n" +
            "jmp +532\n" +
            "acc -18\n" +
            "acc -8\n" +
            "nop +405\n" +
            "jmp +499\n" +
            "acc -1\n" +
            "acc +3\n" +
            "acc +47\n" +
            "acc +38\n" +
            "jmp +67\n" +
            "jmp -9\n" +
            "acc +16\n" +
            "acc -4\n" +
            "acc +23\n" +
            "acc +6\n" +
            "jmp -14\n" +
            "jmp +229\n" +
            "nop +235\n" +
            "acc +3\n" +
            "acc +17\n" +
            "jmp +521\n" +
            "acc +49\n" +
            "acc -8\n" +
            "acc +10\n" +
            "jmp +103\n" +
            "jmp +75\n" +
            "acc +22\n" +
            "nop +527\n" +
            "acc +36\n" +
            "acc +32\n" +
            "jmp -46\n" +
            "nop +434\n" +
            "jmp +447\n" +
            "jmp +159\n" +
            "acc +37\n" +
            "acc -19\n" +
            "acc +39\n" +
            "jmp +181\n" +
            "jmp +1\n" +
            "jmp +162\n" +
            "jmp +1\n" +
            "acc +0\n" +
            "acc +0\n" +
            "acc +34\n" +
            "jmp +241\n" +
            "acc +42\n" +
            "acc +12\n" +
            "jmp -75\n" +
            "jmp -70\n" +
            "acc +42\n" +
            "acc -4\n" +
            "acc +49\n" +
            "jmp +456\n" +
            "jmp +277\n" +
            "jmp +302\n" +
            "acc +45\n" +
            "acc +19\n" +
            "nop -41\n" +
            "jmp +318\n" +
            "jmp +153\n" +
            "acc +9\n" +
            "nop +323\n" +
            "jmp -113\n" +
            "nop -9\n" +
            "jmp +1\n" +
            "acc +37\n" +
            "acc +12\n" +
            "jmp +448\n" +
            "acc +3\n" +
            "acc +38\n" +
            "jmp -114\n" +
            "acc +7\n" +
            "jmp +180\n" +
            "acc -5\n" +
            "acc -10\n" +
            "jmp +117\n" +
            "jmp +320\n" +
            "acc +9\n" +
            "jmp +330\n" +
            "acc +18\n" +
            "jmp +1\n" +
            "jmp +330\n" +
            "nop +450\n" +
            "acc +10\n" +
            "jmp +22\n" +
            "acc +44\n" +
            "jmp +298\n" +
            "acc +38\n" +
            "nop +433\n" +
            "acc +1\n" +
            "jmp +431\n" +
            "jmp +339\n" +
            "nop +251\n" +
            "jmp -126\n" +
            "nop +191\n" +
            "jmp +294\n" +
            "acc -7\n" +
            "acc +30\n" +
            "acc -15\n" +
            "jmp +400\n" +
            "jmp +441\n" +
            "acc +5\n" +
            "acc +17\n" +
            "nop +35\n" +
            "nop +103\n" +
            "jmp +410\n" +
            "nop -122\n" +
            "acc +35\n" +
            "jmp +73\n" +
            "acc -13\n" +
            "jmp +291\n" +
            "acc -11\n" +
            "jmp +95\n" +
            "acc -12\n" +
            "acc +19\n" +
            "acc -16\n" +
            "acc +34\n" +
            "jmp +140\n" +
            "acc -15\n" +
            "acc +6\n" +
            "acc -4\n" +
            "jmp +190\n" +
            "acc +11\n" +
            "acc +0\n" +
            "acc +19\n" +
            "acc +43\n" +
            "jmp +167\n" +
            "acc +29\n" +
            "nop +371\n" +
            "jmp +1\n" +
            "jmp -36\n" +
            "acc +20\n" +
            "acc +20\n" +
            "acc +26\n" +
            "jmp +374\n" +
            "jmp -76\n" +
            "acc +20\n" +
            "jmp +115\n" +
            "acc +13\n" +
            "acc +25\n" +
            "acc +39\n" +
            "jmp -115\n" +
            "acc -18\n" +
            "nop -64\n" +
            "jmp +133\n" +
            "acc -18\n" +
            "acc +32\n" +
            "nop -198\n" +
            "jmp -157\n" +
            "acc +6\n" +
            "acc +41\n" +
            "nop +36\n" +
            "jmp -136\n" +
            "jmp +1\n" +
            "jmp +41\n" +
            "acc -14\n" +
            "acc -17\n" +
            "acc +1\n" +
            "jmp +280\n" +
            "jmp +1\n" +
            "jmp +72\n" +
            "acc +7\n" +
            "jmp +1\n" +
            "acc -12\n" +
            "acc -8\n" +
            "jmp +291\n" +
            "acc -13\n" +
            "acc +36\n" +
            "acc +0\n" +
            "jmp +97\n" +
            "acc +20\n" +
            "acc +0\n" +
            "acc +12\n" +
            "acc -16\n" +
            "jmp -196\n" +
            "nop +342\n" +
            "jmp -122\n" +
            "acc +21\n" +
            "nop -33\n" +
            "acc +38\n" +
            "jmp +285\n" +
            "acc -9\n" +
            "acc -17\n" +
            "acc -2\n" +
            "acc +25\n" +
            "jmp +232\n" +
            "jmp +146\n" +
            "jmp +312\n" +
            "acc +11\n" +
            "nop -54\n" +
            "nop +351\n" +
            "jmp -46\n" +
            "acc +27\n" +
            "jmp -244\n" +
            "jmp +262\n" +
            "acc +42\n" +
            "acc +5\n" +
            "nop +4\n" +
            "acc +20\n" +
            "jmp +239\n" +
            "jmp -62\n" +
            "nop -147\n" +
            "jmp -169\n" +
            "acc -12\n" +
            "acc +40\n" +
            "acc +29\n" +
            "jmp +178\n" +
            "nop +310\n" +
            "jmp +49\n" +
            "acc -18\n" +
            "acc +5\n" +
            "jmp +297\n" +
            "jmp +244\n" +
            "acc +31\n" +
            "acc +21\n" +
            "acc +47\n" +
            "acc +41\n" +
            "jmp +76\n" +
            "acc +29\n" +
            "acc +22\n" +
            "acc +16\n" +
            "jmp -121\n" +
            "jmp -244\n" +
            "acc +45\n" +
            "acc -9\n" +
            "acc +36\n" +
            "acc +5\n" +
            "jmp +265\n" +
            "acc +13\n" +
            "acc +47\n" +
            "acc -11\n" +
            "jmp +338\n" +
            "acc +0\n" +
            "jmp +85\n" +
            "acc +18\n" +
            "acc +27\n" +
            "jmp +1\n" +
            "acc +43\n" +
            "jmp +227\n" +
            "jmp +276\n" +
            "acc +42\n" +
            "nop +52\n" +
            "acc -15\n" +
            "nop +311\n" +
            "jmp -199\n" +
            "acc +45\n" +
            "jmp +286\n" +
            "acc -8\n" +
            "acc +7\n" +
            "acc +9\n" +
            "acc -18\n" +
            "jmp -273\n" +
            "acc +50\n" +
            "jmp +239\n" +
            "nop +31\n" +
            "acc +16\n" +
            "jmp -162\n" +
            "acc +12\n" +
            "nop -204\n" +
            "acc +27\n" +
            "jmp -166\n" +
            "acc -4\n" +
            "acc +38\n" +
            "acc +50\n" +
            "nop +120\n" +
            "jmp +243\n" +
            "acc +29\n" +
            "jmp -300\n" +
            "acc +0\n" +
            "nop +179\n" +
            "acc -6\n" +
            "jmp -136\n" +
            "nop -305\n" +
            "acc +15\n" +
            "jmp -136\n" +
            "acc -6\n" +
            "jmp +172\n" +
            "jmp +41\n" +
            "acc -7\n" +
            "nop +81\n" +
            "jmp +199\n" +
            "jmp +54\n" +
            "acc +1\n" +
            "acc +25\n" +
            "jmp -283\n" +
            "nop -132\n" +
            "acc +47\n" +
            "jmp +239\n" +
            "acc +45\n" +
            "acc -14\n" +
            "acc +1\n" +
            "acc +0\n" +
            "jmp +10\n" +
            "acc -15\n" +
            "nop -338\n" +
            "nop -257\n" +
            "jmp +1\n" +
            "jmp -218\n" +
            "acc +36\n" +
            "acc +33\n" +
            "acc +28\n" +
            "jmp -230\n" +
            "acc -5\n" +
            "acc -14\n" +
            "jmp +1\n" +
            "jmp -87\n" +
            "acc +48\n" +
            "nop +141\n" +
            "nop +224\n" +
            "acc +42\n" +
            "jmp +22\n" +
            "nop +140\n" +
            "acc +16\n" +
            "jmp +1\n" +
            "acc +16\n" +
            "jmp -92\n" +
            "acc +37\n" +
            "jmp -368\n" +
            "acc +27\n" +
            "jmp +155\n" +
            "acc +11\n" +
            "nop +196\n" +
            "acc +5\n" +
            "jmp -269\n" +
            "nop -92\n" +
            "jmp -276\n" +
            "acc +46\n" +
            "acc +6\n" +
            "acc +34\n" +
            "jmp -258\n" +
            "jmp +2\n" +
            "jmp +80\n" +
            "jmp +36\n" +
            "acc +20\n" +
            "jmp -181\n" +
            "nop -54\n" +
            "acc +48\n" +
            "nop +166\n" +
            "nop +165\n" +
            "jmp -263\n" +
            "acc +47\n" +
            "acc +33\n" +
            "jmp +54\n" +
            "nop -216\n" +
            "acc +37\n" +
            "acc +19\n" +
            "jmp -349\n" +
            "acc +12\n" +
            "nop -156\n" +
            "nop +7\n" +
            "acc -5\n" +
            "jmp -390\n" +
            "acc -10\n" +
            "jmp -315\n" +
            "nop -393\n" +
            "jmp -89\n" +
            "jmp +1\n" +
            "jmp -312\n" +
            "acc +4\n" +
            "jmp -120\n" +
            "acc -2\n" +
            "nop +23\n" +
            "acc +42\n" +
            "acc +28\n" +
            "jmp -205\n" +
            "acc +43\n" +
            "acc +6\n" +
            "jmp -49\n" +
            "acc -13\n" +
            "acc +1\n" +
            "acc +10\n" +
            "acc +19\n" +
            "jmp -394\n" +
            "acc -15\n" +
            "acc +0\n" +
            "jmp -365\n" +
            "acc +23\n" +
            "acc -17\n" +
            "nop +23\n" +
            "acc +0\n" +
            "jmp -37\n" +
            "acc +9\n" +
            "acc +31\n" +
            "jmp -7\n" +
            "jmp -278\n" +
            "nop +147\n" +
            "acc +5\n" +
            "acc +43\n" +
            "jmp -149\n" +
            "nop -65\n" +
            "acc +19\n" +
            "acc +46\n" +
            "jmp +59\n" +
            "acc +29\n" +
            "nop +169\n" +
            "jmp +131\n" +
            "acc +43\n" +
            "acc +50\n" +
            "jmp +10\n" +
            "acc -4\n" +
            "jmp -390\n" +
            "acc +24\n" +
            "jmp -236\n" +
            "acc +10\n" +
            "acc +19\n" +
            "nop -160\n" +
            "acc +32\n" +
            "jmp +162\n" +
            "acc +29\n" +
            "jmp +170\n" +
            "jmp -14\n" +
            "acc +36\n" +
            "jmp -88\n" +
            "acc +2\n" +
            "acc +1\n" +
            "acc +1\n" +
            "jmp -86\n" +
            "nop +48\n" +
            "acc +21\n" +
            "jmp -356\n" +
            "acc +0\n" +
            "nop -321\n" +
            "nop -247\n" +
            "nop +127\n" +
            "jmp -279\n" +
            "jmp +40\n" +
            "acc +23\n" +
            "acc +47\n" +
            "acc +6\n" +
            "jmp -438\n" +
            "acc +26\n" +
            "acc +35\n" +
            "nop +120\n" +
            "acc +2\n" +
            "jmp -58\n" +
            "acc +30\n" +
            "acc +2\n" +
            "jmp +42\n" +
            "acc +8\n" +
            "acc +24\n" +
            "acc +9\n" +
            "acc +19\n" +
            "jmp -351\n" +
            "acc +40\n" +
            "acc +18\n" +
            "acc +43\n" +
            "acc +29\n" +
            "jmp +9\n" +
            "jmp +1\n" +
            "jmp -210\n" +
            "jmp -82\n" +
            "acc +41\n" +
            "acc +45\n" +
            "jmp -28\n" +
            "nop -395\n" +
            "jmp +71\n" +
            "acc +38\n" +
            "acc -13\n" +
            "nop -251\n" +
            "acc -9\n" +
            "jmp -366\n" +
            "acc +34\n" +
            "acc +7\n" +
            "acc -7\n" +
            "jmp -212\n" +
            "acc +6\n" +
            "acc +24\n" +
            "acc +1\n" +
            "acc -11\n" +
            "jmp +94\n" +
            "acc +20\n" +
            "acc -14\n" +
            "acc -5\n" +
            "jmp +81\n" +
            "acc +37\n" +
            "jmp +77\n" +
            "acc -15\n" +
            "nop -486\n" +
            "jmp +39\n" +
            "jmp +1\n" +
            "acc +39\n" +
            "acc +49\n" +
            "jmp -371\n" +
            "acc +5\n" +
            "nop -446\n" +
            "jmp -267\n" +
            "acc +39\n" +
            "jmp +67\n" +
            "acc +35\n" +
            "acc +8\n" +
            "nop -419\n" +
            "jmp -53\n" +
            "acc +20\n" +
            "acc -9\n" +
            "acc +46\n" +
            "acc +30\n" +
            "jmp -136\n" +
            "acc +35\n" +
            "jmp +56\n" +
            "jmp +70\n" +
            "acc +13\n" +
            "acc +48\n" +
            "jmp -290\n" +
            "acc -18\n" +
            "acc +48\n" +
            "acc +50\n" +
            "jmp -225\n" +
            "jmp -226\n" +
            "acc +34\n" +
            "jmp -391\n" +
            "acc +49\n" +
            "nop -324\n" +
            "acc -10\n" +
            "acc +41\n" +
            "jmp -130\n" +
            "jmp +6\n" +
            "jmp -555\n" +
            "acc -17\n" +
            "jmp -433\n" +
            "acc +33\n" +
            "jmp -64\n" +
            "jmp -476\n" +
            "nop -138\n" +
            "nop -556\n" +
            "acc +47\n" +
            "jmp +27\n" +
            "jmp -78\n" +
            "acc -16\n" +
            "acc -4\n" +
            "acc -3\n" +
            "acc +48\n" +
            "jmp -292\n" +
            "acc +43\n" +
            "acc +3\n" +
            "acc +44\n" +
            "jmp +15\n" +
            "acc +49\n" +
            "acc +5\n" +
            "acc +4\n" +
            "acc +27\n" +
            "jmp -491\n" +
            "acc +6\n" +
            "acc +38\n" +
            "acc +31\n" +
            "nop -70\n" +
            "jmp -379\n" +
            "acc +0\n" +
            "acc +2\n" +
            "acc +49\n" +
            "jmp -297\n" +
            "jmp -405\n" +
            "jmp -72\n" +
            "jmp -371\n" +
            "jmp -115\n" +
            "acc +7\n" +
            "acc -15\n" +
            "acc -9\n" +
            "jmp -486\n" +
            "acc +3\n" +
            "acc +16\n" +
            "nop -19\n" +
            "acc +14\n" +
            "jmp -296\n" +
            "jmp -233\n" +
            "acc +40\n" +
            "nop -342\n" +
            "nop -58\n" +
            "acc -9\n" +
            "jmp -316\n" +
            "acc +4\n" +
            "acc +15\n" +
            "acc +14\n" +
            "acc +50\n" +
            "jmp -296\n" +
            "acc -11\n" +
            "acc +14\n" +
            "acc +43\n" +
            "acc +38\n" +
            "jmp -391\n" +
            "acc +43\n" +
            "acc +25\n" +
            "acc -5\n" +
            "acc +27\n" +
            "jmp +1";

}
