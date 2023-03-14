package calendar.year._2017.day02;

import utils.enums.Part;
import utils.Exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CorruptionChecksum extends Exercise {
    public CorruptionChecksum(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            int score = 0;
            while (null != (line = br.readLine())) {
                // Extracts all the line numbers
                List<Integer> numbers = Arrays.stream(line.split("\\s+"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                // Increments the score according to the exercise part
                score += Part.PART_1.equals(part) ? exercise1(numbers) : exercise2(numbers);
            }

            return print(score);
        }
    }

    /**
     * @param numbers The list of numbers
     * @return the sum of the lowest and the greatest numbers of a given list
     */
    private int exercise1(List<Integer> numbers) {
        numbers.sort(Integer::compareTo);
        return numbers.get(numbers.size() - 1) - numbers.get(0);
    }

    /**
     * @param numbers The list of numbers
     * @return the sum of the only two divisible numbers of a given list
     */
    private int exercise2(List<Integer> numbers) {
        for (int number : numbers) {
            Optional<Integer> optDiviser = numbers.stream()
                    .filter(num -> num % number == 0 || number % num == 0)
                    .filter(num -> num != number)
                    .findAny();

            if (optDiviser.isPresent()) {
                int diviser = optDiviser.get();
                return Math.max(diviser, number) / Math.min(diviser, number);
            }
        }
        throw new IllegalArgumentException("There is no pair of divisible numbers in " + numbers);
    }
}
