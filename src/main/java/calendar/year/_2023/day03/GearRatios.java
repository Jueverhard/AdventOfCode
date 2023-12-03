package calendar.year._2023.day03;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class GearRatios extends Exercise {

    public GearRatios(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        Set<Number> numbers = new HashSet<>();
        Set<Symbol> symbols = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            Pattern digitPattern = Pattern.compile("\\d+");
            Pattern symbolPattern = Pattern.compile("[^\\d\\.]");

            int lineIndex = 0;
            while (null != (line = br.readLine())) {
                Set<Number> inputNumbers = extractNumbers(line, digitPattern, lineIndex);
                Set<Symbol> inputSymbols = extractSymbols(line, symbolPattern, lineIndex);
                numbers.addAll(inputNumbers);
                symbols.addAll(inputSymbols);
                lineIndex++;
            }
        }

        int res;
        if (Part.PART_1 == part) {
            res = numbers.stream()
                    .filter(number -> number.isNearAnySymbol(symbols))
                    .map(Number::value)
                    .reduce(0, Integer::sum);
        } else {
            res = symbols.stream()
                    .map(symbol -> symbol.isGearMaterial(numbers))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map(gearMaterial -> gearMaterial.getLeft() * gearMaterial.getRight())
                    .reduce(0, Integer::sum);
        }

        return print(res);
    }

    private Set<Number> extractNumbers(String input, Pattern pattern, int yPos) {
        List<String> numberStrings = pattern.matcher(input).results()
                .map(MatchResult::group)
                .toList();
        Set<Number> numbers = new HashSet<>();

        int index = 0;
        for (String numberString : numberStrings) {
            index = input.indexOf(numberString, index);
            Number number = new Number(
                    new Position(index, yPos),
                    new Position(index + numberString.length() - 1, yPos),
                    Integer.parseInt(numberString)
            );
            numbers.add(number);

            index += numberString.length();
        }

        return numbers;
    }

    private Set<Symbol> extractSymbols(final String input, Pattern pattern, int yPos) {
        List<String> symbolStrings = pattern.matcher(input).results()
                .map(MatchResult::group)
                .toList();
        Set<Symbol> symbols = new HashSet<>();

        int index = 0;
        for (String symbolString : symbolStrings) {
            index = input.indexOf(symbolString, index + 1);
            Symbol symbol = new Symbol(new Position(index, yPos), symbolString);
            symbols.add(symbol);
        }

        return symbols;
    }
}
