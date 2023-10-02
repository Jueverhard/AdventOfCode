package calendar.year._2021.day03;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BinaryDiagnostic extends Exercise {
    public BinaryDiagnostic(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<char[]> bitsLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while (null != (line = br.readLine())) {
                bitsLines.add(line.toCharArray());
            }
        }

        int lineSize = bitsLines.get(0).length;

        int result;
        if (Part.PART_1.equals(part)) {
            int gammaRate = extractGammaRate(bitsLines, lineSize);
            int epsilonRate = ((int) Math.pow(2, lineSize)) - 1 - gammaRate;
            result = gammaRate * epsilonRate;
        } else {
            int oxygenRating = extractOxygenRating(bitsLines, 0);
            int co2Rating = extractCo2Rating(bitsLines, 0);
            result = oxygenRating * co2Rating;
        }

        return print(result);
    }

    private int extractGammaRate(List<char[]> bitsLines, int lineSize) {
        String mostCommonBits = IntStream.range(0, lineSize)
                .mapToObj(i -> String.valueOf(extractMostCommonBit(bitsLines, i)))
                .reduce(String::concat).orElseThrow();
        return Integer.parseInt(mostCommonBits, 2);
    }

    private int extractOxygenRating(List<char[]> bitsLines, int currentIndex) {
        if (1 == bitsLines.size()) {
            return Integer.parseInt(reduceCharArray(bitsLines.get(0)), 2);
        } else {
            char mostCommonBit = extractMostCommonBit(bitsLines, currentIndex);
            List<char[]> filteredBitLines = bitsLines.stream()
                    .filter(bits -> mostCommonBit == bits[currentIndex])
                    .toList();
            return extractOxygenRating(filteredBitLines, currentIndex + 1);
        }
    }

    private int extractCo2Rating(List<char[]> bitsLines, int currentIndex) {
        if (1 == bitsLines.size()) {
            return Integer.parseInt(reduceCharArray(bitsLines.get(0)), 2);
        } else {
            char mostCommonBit = extractMostCommonBit(bitsLines, currentIndex);
            List<char[]> filteredBitLines = bitsLines.stream()
                    .filter(bits -> mostCommonBit != bits[currentIndex])
                    .toList();
            return extractCo2Rating(filteredBitLines, currentIndex + 1);
        }
    }

    private char extractMostCommonBit(List<char[]> bitsLines, int index) {
        // Comptage du nombre d'occurrences de chaque bit
        Map<Character, Long> bitFrequencies = bitsLines.stream()
                .map(bits -> bits[index])
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
        return bitFrequencies.get('0') > bitFrequencies.get('1') ? '0' : '1';
    }

    private String reduceCharArray(char[] characters) {
        return Stream.of(characters)
                .map(String::valueOf)
                .reduce(String::concat)
                .orElseThrow();
    }
}
