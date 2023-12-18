package calendar.year._2023.day15;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class LensLibrary extends Exercise {

    public LensLibrary(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<Step> steps;
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String input = br.readLine();
            steps = Arrays.stream(input.split(","))
                    .map(Step::new)
                    .toList();
        }

        int result;
        if (Part.PART_1 == part) {
            result = steps.stream()
                    .map(Step::computeHashValue)
                    .reduce(0, Integer::sum);
        } else {
            List<Lens> part2Steps = steps.stream()
                    .map(step -> new Lens(step.text()))
                    .toList();
            result = exercise2(part2Steps);
        }

        return print(result);
    }

    private int exercise2(List<Lens> lenses) {
        Map<Integer, List<Lens>> lensesPerBox = new HashMap<>();

        for (Lens lens : lenses) {
            int hash = lens.computeHashValue();
            if ('=' == lens.getOperand()) {
                if (lensesPerBox.containsKey(hash)) {
                    List<Lens> existingLenses = lensesPerBox.get(hash);
                    existingLenses.stream()
                            .filter(existingLens -> existingLens.getLabel().equals(lens.getLabel()))
                            .findFirst()
                            .ifPresentOrElse(
                                    existingLens -> existingLenses.set(existingLenses.indexOf(existingLens), lens),
                                    () -> existingLenses.add(lens)
                            );
                } else {
                    lensesPerBox.put(hash, new ArrayList<>(List.of(lens)));
                }
            } else if ('-' == lens.getOperand()) {
                lensesPerBox.getOrDefault(hash, new ArrayList<>())
                        .removeIf(existingLens -> existingLens.getLabel().equals(lens.getLabel()));
            }
        }

        return lensesPerBox.entrySet().stream()
                .map(entry -> IntStream.range(0, entry.getValue().size())
                        .mapToObj(i -> (entry.getKey() + 1) * (i + 1) * entry.getValue().get(i).getPower())
                        .toList()
                )
                .flatMap(List::stream)
                .reduce(0, Integer::sum);
    }
}
