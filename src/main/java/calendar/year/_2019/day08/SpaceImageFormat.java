package calendar.year._2019.day08;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class SpaceImageFormat extends Exercise {
    public SpaceImageFormat(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        final int wide = testMode ? 2 : 25;
        final int tall = testMode ? 2 : 6;
        final int layerSize = wide * tall;
        List<Layer> layers;
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String input = br.readLine();
            List<Integer> digits = Arrays.stream(input.split(""))
                    .map(Integer::parseInt)
                    .toList();

            layers = IntStream.range(0, digits.size() / layerSize)
                    .mapToObj(i -> digits.subList(i * layerSize, (i + 1) * layerSize))
                    .map(Layer::new)
                    .toList();
        }

        // Exercise answering
        if (Part.PART_1 == part) {
            return print(exercise1(layers));
        } else {
            return print(exercise2(layers, layerSize));
        }
    }

    public long exercise1(List<Layer> layers) {
        return layers.stream()
                .min(Comparator.comparing(Layer::countZeros))
                .orElseThrow()
                .computeValue();
    }

    public String exercise2(List<Layer> layers, int layerSize) {
        return IntStream.range(0, layerSize)
                .map(i -> layers.stream()
                        .map(layer -> layer.findDigit(i))
                        .filter(digit -> 2 != digit)
                        .findFirst()
                        .orElse(2)
                )
                .mapToObj(String::valueOf)
                .reduce("", String::concat);
    }
}
