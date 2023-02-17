package calendar.year._2016.day02;

import calendar.year.enums.Part;
import utils.Exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class BathroomSecurity extends Exercise {

    public BathroomSecurity(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        Keyboard keyboard = initKeyboard(part);
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            StringBuilder resBuilder = new StringBuilder();
            String line;
            while (null != (line = br.readLine())) {
                for (String str : line.split("")) {
                    keyboard.update(Direction.valueOf(str));
                }
                resBuilder.append(keyboard.getKey());
            }
            return print(resBuilder);
        }
    }

    /**
     * Initializes the keyboard according to the exercise part
     *
     * @param part The exercise part
     * @return the corresponding keyboard
     */
    private Keyboard initKeyboard(Part part) {
        Keyboard keyboard;
        if (Part.PART_1.equals(part)) {
            List<Row> rows = List.of(
                    new Row(List.of('1', '2', '3')),
                    new Row(List.of('4', '5', '6')),
                    new Row(List.of('7', '8', '9'))
            );
            keyboard = new Keyboard(rows, 1, 1);
        } else {
            List<Row> rows = List.of(
                    new Row(List.of(' ', ' ', '1', ' ', ' ')),
                    new Row(List.of(' ', '2', '3', '4', ' ')),
                    new Row(List.of('5', '6', '7', '8', '9')),
                    new Row(List.of(' ', 'A', 'B', 'C', ' ')),
                    new Row(List.of(' ', ' ', 'D', ' ', ' '))
            );
            keyboard = new Keyboard(rows, 0, 2);
        }

        return keyboard;
    }
}
