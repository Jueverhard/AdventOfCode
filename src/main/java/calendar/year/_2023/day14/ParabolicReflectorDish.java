package calendar.year._2023.day14;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ParabolicReflectorDish extends Exercise {

    public ParabolicReflectorDish(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<Position> rocks = new ArrayList<>();
        List<Position> stops = new ArrayList<>();
        int y = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while (null != (line = br.readLine())) {
                for (int x = 0; x < line.length(); x++) {
                    if ('O' == line.charAt(x)) {
                        rocks.add(new Position(x, y));
                    } else if ('#' == line.charAt(x)) {
                        stops.add(new Position(x, y));
                    }
                }
                y++;
            }
        }
        Dish dish = new Dish(rocks, stops, y);
        dish.rollNorth();

        int result = dish.computeValue();
        return print(result);
    }
}
