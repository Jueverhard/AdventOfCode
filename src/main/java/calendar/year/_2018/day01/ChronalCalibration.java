package calendar.year._2018.day01;

import utils.fileparser.Parser;
import utils.Exercise;
import calendar.year.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChronalCalibration extends Exercise {
    public ChronalCalibration(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        return Part.PART_1.equals(part) ? exercise1(testMode) : exercise2(testMode);
    }

    private String exercise1(boolean testMode) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            int frequency = 0;
            while (null != (line = br.readLine())) {
                frequency = updateFrequency(frequency, line);
            }

            return print(frequency);
        }
    }

    private String exercise2(boolean testMode) {
        List<String> lines = Parser.parseLines(this.getInputPath(testMode));
        int nbLines = lines.size();
        List<Integer> frequencies = new ArrayList<>();
        int frequency = 0;

        for (int i = 0; !frequencies.contains(frequency); i++) {
            frequencies.add(frequency);
            frequency = updateFrequency(frequency, lines.get(i % nbLines));
        }

        return print(frequency);
    }

    private int updateFrequency(int currentFrequency, String operation) {
        char operator = operation.charAt(0);
        int delta = Integer.parseInt(operation.substring(1));
        switch (operator) {
            case '+':
                return currentFrequency + delta;
            case '-':
                return currentFrequency - delta;
            default:
                throw new IllegalArgumentException();
        }
    }
}
