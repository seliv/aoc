package seliv.aoc.aoc2020;

import java.util.List;
import java.util.Map;

public class MachineSample {
    public static void main(String[] args) {
        List<List<String>> in = InputParser.parseInput(IN);
        List<String> ops = in.get(0);
        Map<Long, Machine.Command> program = Machine.parseProgram(ops);
        Machine.runSingle(program);
    }

    public static String IN = "";

    public static String IN2 = "";

}
