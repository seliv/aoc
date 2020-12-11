package seliv.aoc.aoc2020;

import java.util.ArrayList;
import java.util.List;

public class InputParser {
    public static List<List<String>> parseInput(String raw) {
        List<List<String>> result = new ArrayList<>();
        String[] in = raw.split("\n");
        List<String> block = new ArrayList<>();
        for (String s : in) {
            if (s.length() == 0) {
                result.add(block);
                block = new ArrayList<>();
            } else {
                block.add(s);
            }
        }
        if (!block.isEmpty()) {
            result.add(block);
        }
        return result;
    }
}