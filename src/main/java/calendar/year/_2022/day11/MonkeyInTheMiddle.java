package calendar.year._2022.day11;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MonkeyInTheMiddle extends Exercise {

    public MonkeyInTheMiddle(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        // Initialize the monkeys
        List<Monkey> monkeys = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            List<String> monkeyLines = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) {
                    monkeyLines.clear();
                } else {
                    monkeyLines.add(line);
                    if (line.contains("If false")) {
                        monkeys.add(new Monkey(monkeyLines, part));
                    }
                }
            }
        }

        int modulo = monkeys.stream()
                .mapToInt(Monkey::getTestFunctionCoefficient)
                .reduce(1, (a, b) -> a * b);
        Monkey.setModulo(modulo);

        // Execute rounds
        int nbRounds = Part.PART_1.equals(part) ? 20 : 10000;
        for (int i = 0; i < nbRounds; i++) {
            executeRound(monkeys);
        }

        List<BigInteger> greatestInspectionCounts = monkeys.stream()
                .map(Monkey::getInspectionCount)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .map(BigInteger::valueOf)
                .collect(Collectors.toList());

        return print(greatestInspectionCounts.get(0).multiply(greatestInspectionCounts.get(1)));
    }

    private void executeRound(List<Monkey> monkeys) {
        for (Monkey monkey : monkeys) {
            while (!monkey.getItems().isEmpty()) {
                monkey.throwItem(monkeys);
            }
        }
    }
}
