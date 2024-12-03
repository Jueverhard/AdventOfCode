package calendar.year._2024.day03;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class MullItOver extends Exercise {

    public MullItOver(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<Operation> operations = new ArrayList<>();

        // Data initialization
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            Pattern operationPattern = Pattern.compile("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)");
            while (null != (line = br.readLine())) {
                List<Operation> lineOperations = operationPattern.matcher(line).results()
                        .map(MatchResult::group)
                        .map(input -> input.startsWith("mul") ? new Multiplication(input) : new Activation(input))
                        .toList();

                operations.addAll(lineOperations);
            }
        }

        int result = Part.PART_1 == part ?
                computeAllMultiplicationsSum(operations) :
                computeAllEnabledMultiplicationsSum(operations);

        return print(result);
    }

    /**
     * Computes the sum of all given multiplications.
     *
     * @param operations The operations to consider.
     * @return The sum of all computed results from multiplication operations.
     */
    private int computeAllMultiplicationsSum(List<Operation> operations) {
        return operations.stream()
                .filter(Multiplication.class::isInstance)
                .map(Multiplication.class::cast)
                .map(Multiplication::compute)
                .reduce(Integer::sum)
                .orElseThrow();
    }

    /**
     * Computes the sum of all given enabled multiplications.
     *
     * @param operations The list of operations to consider, which includes (de-)activations and multiplications.
     * @return The sum of the results of enabled multiplication operations.
     */
    private int computeAllEnabledMultiplicationsSum(List<Operation> operations) {
        List<Operation> enabledOperations = new ArrayList<>();
        boolean enabled = true;
        for (Operation operation : operations) {
            if (enabled && operation instanceof Multiplication multiplication) {
                enabledOperations.add(multiplication);
            } else if (operation instanceof Activation activation) {
                enabled = activation.isDoesActivate();
            }
        }

        return computeAllMultiplicationsSum(enabledOperations);
    }
}
