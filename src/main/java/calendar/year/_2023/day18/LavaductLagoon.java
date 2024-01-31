package calendar.year._2023.day18;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.LongStream;

public class LavaductLagoon extends Exercise {

    public LavaductLagoon(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        // Initialize data from the input
        List<DigInstruction> digInstructions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            Pattern pattern = Pattern.compile("(?<direction>\\w) (?<volume>\\d+) \\(#(?<hexa>\\w+)");
            String line;
            while (null != (line = br.readLine())) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String hexa = matcher.group("hexa");
                    Direction direction;
                    int volume;
                    if (Part.PART_1 == part) {
                        direction = Direction.valueOf(matcher.group("direction"));
                        volume = Integer.parseInt(matcher.group("volume"));
                    } else {
                        direction = switch (hexa.charAt(5)) {
                            case '0' -> Direction.R;
                            case '1' -> Direction.D;
                            case '2' -> Direction.L;
                            case '3' -> Direction.U;
                            default -> throw new IllegalArgumentException();
                        };
                        volume = Integer.parseInt(hexa.substring(0, 5), 16);
                    }
                    digInstructions.add(new DigInstruction(direction, volume, hexa));
                }
            }
        }

        // Use the instructions to dug up the field
        List<InstructionPosition> instructionPositions = new ArrayList<>();
        Position currentPosition = new Position(0, 0);
        Extremities extremities = new Extremities(0L, 0L, 0L, 0L);
        long nbDugUpPositions = 0L;
        for (DigInstruction digInstruction : digInstructions) {
            instructionPositions.add(new InstructionPosition(digInstruction, currentPosition));
            currentPosition = currentPosition.computeDestination(
                    digInstruction.direction(),
                    digInstruction.volume()
            );
            nbDugUpPositions += digInstruction.volume();
            extremities.updateSelf(currentPosition);
        }

        long nbPositionsToDugUp = LongStream.range(extremities.getXMin(), extremities.getXMax()).parallel()
                .map(x -> LongStream.range(extremities.getYMin(), extremities.getYMax()).parallel()
                        .mapToObj(y -> new Position(x, y))
                        .filter(position -> position.isToBeDugUp(instructionPositions))
                        .count()
                )
                .sum();

        long result = nbDugUpPositions + nbPositionsToDugUp;
        return print(result);
    }
}
