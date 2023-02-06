package calendar.year._2022.day06;

import utils.Exercise;
import calendar.year.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Queue;

public class TuningTrouble extends Exercise {

    public TuningTrouble(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        int markerSize = Part.PART_1.equals(part) ? 4 : 14;
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line = br.readLine();
            char[] characters = line.toCharArray();
            Queue<Character> lastChars = new ArrayDeque<>();
            int index;
            for (index = 0; index < characters.length; index++) {
                char c = characters[index];
                while (lastChars.contains(c)) {
                    lastChars.remove();
                }
                lastChars.add(c);
                if (lastChars.size() == markerSize) {
                    break;
                }
            }

            return print(index + 1);
        }
    }
}
