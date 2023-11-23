package calendar.year._2021.day06;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Lanternfish extends Exercise {
    public Lanternfish(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        // Initialize the lantern-fish population
        LanternfishPopulation population;
        Pattern digitsPattern = Pattern.compile("\\d+");
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line = br.readLine();
            List<Integer> timers = digitsPattern.matcher(line).results()
                    .map(MatchResult::group)
                    .map(Integer::parseInt)
                    .toList();

            population = new LanternfishPopulation(timers);
        }

        int nbOfDays = Part.PART_1 == part ? 80 : 256;
        for (int i = 0; i < nbOfDays; i++) {
            population.passADay();
        }

        return print(population.count());
    }
}
