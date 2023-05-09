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
        int gammaRate = extractGammaRate(bitsLines, lineSize);
        int epsilonRate = ((int) Math.pow(2, lineSize)) - 1 - gammaRate;
        return print(gammaRate * epsilonRate);
    }

    private int extractGammaRate(List<char[]> bitsLines, int lineSize) {
        String mostCommonBits = IntStream.range(0, lineSize)
                .mapToObj(i -> "" + extractMostCommonBit(bitsLines, i))
                .reduce(String::concat).orElseThrow();
        return Integer.parseInt(mostCommonBits, 2);
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
}
