package seliv.aoc.aoc2020;

import java.util.*;

public class Machine {
    public long a = 0;
    public long pc = 0;

    public static Map<Long, Command> parseProgram(List<String> ops) {
        Map<Long, Command> program = new HashMap<>();
        for (long i = 0; i < ops.size(); i++) {
            String s = ops.get((int)i);
            String[] ss = s.split(" ");
            Operation oper = Operation.valueOf(ss[0].toUpperCase());
            long arg = Long.parseLong(ss[1]);

            Command cmd = new Command(oper, arg);
            program.put(i, cmd);
        }
        return program;
    }

    public static Machine runSingle(Map<Long, Command> program) {
        Machine machine = new Machine();
        machine.run(program);
        return machine;
    }

    public void run(Map<Long, Command> program) {
        Set<Long> visited = new HashSet<>();

        while (!visited.contains(pc)) {
            visited.add(pc);

            Command command = program.get(pc);
            if (Operation.NOP == command.operation) {
                pc++;
            } else if (Operation.ACC == command.operation) {
                a += command.argument;
                pc++;
            } else if (Operation.JMP == command.operation) {
                pc += command.argument;
            } else {
                throw new MachineException("Unknown operation: " + command.operation);
            }

            if (!program.containsKey(pc)) {
                System.out.println("a = " + a);
                return;
            }
        }
    }

    public static class Command {
        public final Operation operation;
        public final long argument;

        public Command(Operation operation, long argument) {
            this.operation = operation;
            this.argument = argument;
        }
    }

    public enum Operation {
        NOP, ACC, JMP
    }

    public static class MachineException extends RuntimeException {
        public MachineException(String message) {
            super(message);
        }
    }
}
