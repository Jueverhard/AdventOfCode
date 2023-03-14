package calendar.year._2019.day01;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class TheTyrannyOfTheRocketEquation extends Exercise {
    public TheTyrannyOfTheRocketEquation(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            int fuelNeeded = 0;
            while (null != (line = br.readLine())) {
                int fuel = Integer.parseInt(line) / 3 - 2;

                if (Part.PART_2.equals(part)) {
                    fuel += computeExtraFuel(fuel);
                }

                fuelNeeded += fuel;
            }

            return print(fuelNeeded);
        }
    }

    /**
     * Recursively computes the needed fuel to carry a given amount of fuel
     *
     * @param fuel Amount of fuel to be carried
     * @return The extra amount of fuel that needs to be carried
     */
    private int computeExtraFuel(int fuel) {
        int extraFuel = fuel / 3 - 2;
        if (extraFuel > 0) {
            int recursiveExtraFuel = computeExtraFuel(extraFuel);
            extraFuel += Math.max(0, recursiveExtraFuel);
        }
        return extraFuel;
    }
}
