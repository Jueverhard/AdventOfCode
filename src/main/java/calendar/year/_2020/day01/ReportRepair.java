package calendar.year._2020.day01;

import calendar.year.enums.Part;
import utils.Exercise;
import utils.fileparser.Parser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReportRepair extends Exercise {
    public ReportRepair(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<Integer> expenses = Parser.parseLines(this.getInputPath(testMode)).stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int nbExpenses = Part.PART_1.equals(part) ? 2 : 3;
        int result = computeExpensesProduct(expenses, 2020, nbExpenses)
                .orElseThrow(() -> new NoSuchElementException("There is no set of " + nbExpenses + " expenses that sums up to 2020"));
        return print(result);
    }

    /**
     * Recursively computes the product of expenses whose sum matches a given value
     *
     * @param expenses           Expenses list
     * @param expectedSum        Expected sum of expenses
     * @param nbExpectedExpenses Size of the expenses to sum up
     * @return The product of the matching expenses, if any
     */
    private Optional<Integer> computeExpensesProduct(List<Integer> expenses, int expectedSum, int nbExpectedExpenses) {
        if (1 == nbExpectedExpenses) {
            return Optional.of(expectedSum);
        } else if (2 == nbExpectedExpenses) {
            return computeExpensesProduct(expenses, expectedSum);
        } else {
            for (int expense : expenses) {
                List<Integer> otherExpenses = new ArrayList<>(expenses).stream()
                        .filter(elt -> elt != expense)
                        .collect(Collectors.toList());

                Optional<Integer> remainingProduct = computeExpensesProduct(
                        otherExpenses,
                        expectedSum - expense,
                        nbExpectedExpenses - 1
                );

                if (remainingProduct.isPresent()) {
                    return Optional.of(expense * remainingProduct.get());
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Computes the product of expenses whose sum matches a given value
     *
     * @param expenses    Expenses list
     * @param expectedSum Expected sum of expenses
     * @return The product of the matching expenses, if any
     */
    private Optional<Integer> computeExpensesProduct(List<Integer> expenses, int expectedSum) {
        for (int expense : expenses) {
            Optional<Integer> complementaryExpense = expenses.stream()
                    .filter(elt -> expectedSum == elt + expense)
                    .filter(elt -> elt != expense)
                    .findAny();

            if (complementaryExpense.isPresent()) {
                return Optional.of(expense * complementaryExpense.get());
            }
        }

        return Optional.empty();
    }
}
