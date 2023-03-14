package calendar.year._2022.day01;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CalorieCounting extends Exercise {

    public CalorieCounting(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            int totalCalories;
            if (Part.PART_1.equals(part)) {
                totalCalories = exercise1(br);
            } else {
                totalCalories = exercise2(br);
            }

            return print(totalCalories);
        }
    }

    private int exercise1(BufferedReader br) throws IOException {
        int max = 0;
        int count = 0;
        String line;
        while ((line = br.readLine()) != null) {
            // `line` contains one input line
            if (line.isBlank()) {
                if (count > max) {
                    max = count;
                }
                count = 0;
            } else {
                count += Integer.parseInt(line);
            }
        }

        return max;
    }

    private int exercise2(BufferedReader br) throws IOException {
        List<Integer> calories = new ArrayList<>();
        int count = 0;
        String line;
        while ((line = br.readLine()) != null) {
            if (line.isBlank()) {
                calories.add(count);
                count = 0;
            } else {
                count += Integer.parseInt(line);
            }
        }

        return calories.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(0, Integer::sum);
    }
}
