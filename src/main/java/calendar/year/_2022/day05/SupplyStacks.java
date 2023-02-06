package calendar.year._2022.day05;

import utils.Exercise;
import calendar.year.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class SupplyStacks extends Exercise {

    public SupplyStacks(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            List<String> initializationLines = new ArrayList<>();

            // Initializing crate stacks
            while (!(line = br.readLine()).isBlank()) {
                initializationLines.add(line);
            }
            List<Stack<String>> crateStacks = initializeCrateStacks(initializationLines);

            // Executing moves
            while ((line = br.readLine()) != null) {
                moveCrates(crateStacks, line, part);
            }

            String topCrates = crateStacks.stream()
                    .map(Stack::pop)
                    .reduce(String::concat)
                    .orElse("");
            return print(topCrates);
        }
    }

    private List<Stack<String>> initializeCrateStacks(List<String> lines) {
        int nbStacks = (int) Arrays.stream(lines.get(lines.size() - 1).split(""))
                .filter(elt -> !elt.isBlank())
                .count();
        List<Stack<String>> crateStacks = new ArrayList<>(nbStacks);
        for (int i = 0; i < nbStacks; i ++) {
            crateStacks.add(new Stack<>());
        }
        for (int i = lines.size() - 2; i >= 0; i--) {
            String line = lines.get(i);
            for (int j = 0; j < nbStacks; j++) {
                int index = j * 4 + 1;
                char c;
                try {
                    c = line.charAt(index);
                } catch (StringIndexOutOfBoundsException e) {
                    break;
                }
                if (' ' != c) {
                    crateStacks.get(j).add(String.valueOf(c));
                }
            }
        }
        return crateStacks;
    }

    private void moveCrates(List<Stack<String>> crateStacks, String line, Part part) {
        String[] instructions = line.split(" ");
        int nbToMove = Integer.parseInt(instructions[1]);
        Stack<String> from = crateStacks.get(Integer.parseInt(instructions[3]) - 1);
        Stack<String> to = crateStacks.get(Integer.parseInt(instructions[5]) - 1);

        if (Part.PART_1.equals(part)) {
            for (int i = 0; i < nbToMove; i++) {
                to.add(from.pop());
            }
        } else if (Part.PART_2.equals(part)) {
            // Intermediate stack to keep the order
            Stack<String> tmpStack = new Stack<>();
            for (int i = 0; i < nbToMove; i++) {
                tmpStack.add(from.pop());
            }
            for (int i = 0; i < nbToMove; i++) {
                to.add(tmpStack.pop());
            }
        }
    }
}
