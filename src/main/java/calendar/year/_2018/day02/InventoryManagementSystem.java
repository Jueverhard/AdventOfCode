package calendar.year._2018.day02;

import utils.enums.Part;
import utils.Exercise;
import utils.fileparser.Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InventoryManagementSystem extends Exercise {
    public InventoryManagementSystem(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        return Part.PART_1.equals(part) ? exercise1(testMode) : exercise2(testMode);
    }

    private String exercise1(boolean testMode) throws IOException {
        int nbCharsAppearingTwice = 0;
        int nbCharsAppearingThrice = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while (null != (line = br.readLine())) {
                // Counts occurences of each character
                Map<Character, Long> nbOccurencesPerChar = Arrays.stream(line.split(""))
                        .map(str -> str.charAt(0))
                        .collect(Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        ));

                if (nbOccurencesPerChar.containsValue(2L)) {
                    nbCharsAppearingTwice++;
                }
                if (nbOccurencesPerChar.containsValue(3L)) {
                    nbCharsAppearingThrice++;
                }
            }

        }
        return print(nbCharsAppearingTwice * nbCharsAppearingThrice);
    }

    private String exercise2(boolean testMode) {
        List<String> boxes = Parser.parseLines(this.getInputPath(testMode));
        for (String currentBox : boxes) {
            Optional<String> optCloseEnoughBox = boxes.stream()
                    .map(box -> commonPart(currentBox, box))
                    .filter(boxCommonPart -> boxCommonPart.length() == currentBox.length() - 1)
                    .findAny();

            if (optCloseEnoughBox.isPresent()) {
                return print(optCloseEnoughBox.get());
            }
        }

        throw new IllegalArgumentException("There is no pair of boxes with only one character that differs");
    }

    /**
     * @param left  A string
     * @param right Another string
     * @return the common part between both strings
     */
    private String commonPart(String left, String right) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < left.length(); i++) {
            char c = left.charAt(i);
            if (c == right.charAt(i)) {
                stringBuilder.append(c);
            }
        }

        return stringBuilder.toString();
    }
}
