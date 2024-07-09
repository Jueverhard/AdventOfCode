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
        final int wide = testMode ? 3 : 25;
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

        // Working list initialization
        Layer layer = layers.stream()
                .min(Comparator.comparing(Layer::countZeros))
                .orElseThrow();
        long result = layer.computeValue();
        return print(result);
    }
}
