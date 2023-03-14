package calendar.year._2022.day08;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TreetopTreeHouse extends Exercise {
    public TreetopTreeHouse(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;

            // Forst initialization
            Forest forest = new Forest();
            int yCpt = 0;
            while ((line = br.readLine()) != null) {
                List<Integer> treeSizes = Arrays.stream(line.split(""))
                                .map(Integer::parseInt)
                                .collect(Collectors.toList());

                for (int xCpt = 0; xCpt < treeSizes.size(); xCpt++) {
                    forest.addTree(new Tree(xCpt, yCpt, treeSizes.get(xCpt)));
                }
                yCpt++;
            }

            long score;
            if (Part.PART_1.equals(part)) {
                score = exercise1(forest);
            } else {
                score = exercise2(forest);
            }

            return print(score);
        }
    }

    private long exercise1(Forest forest) {
        return forest.getTrees().stream()
                .filter(forest::isTreeSeeable)
                .count();
    }

    private long exercise2(Forest forest) {
        return forest.getTrees().stream()
                .mapToLong(forest::computeScenicScore)
                .max().orElse(0L);
    }
}
