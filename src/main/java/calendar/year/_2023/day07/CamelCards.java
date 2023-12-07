package calendar.year._2023.day07;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CamelCards extends Exercise {

    public CamelCards(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<Hand> hands = new ArrayList<>();
        boolean useJokers = Part.PART_1 != part;
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while (null != (line = br.readLine())) {
                String[] inputData = line.split("\\s+");

                List<Kind> cards = Arrays.stream(inputData[0].split(""))
                        .map(Kind::fromValue)
                        .toList();

                hands.add(new Hand(cards, Integer.parseInt(inputData[1]), useJokers));
            }
        }

        hands.sort(Comparator.reverseOrder());

        int result = 0;
        for (int i = 1; i <= hands.size(); i++) {
            result += i * hands.get(i - 1).bid();
        }

        return print(result);
    }
}
