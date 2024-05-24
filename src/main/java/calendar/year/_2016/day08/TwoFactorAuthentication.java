package calendar.year._2016.day08;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TwoFactorAuthentication extends Exercise {
    public TwoFactorAuthentication(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        Pattern rectanglePattern = Pattern.compile("rect (?<x>\\d+)x(?<y>\\d+)");
        Pattern rotationPattern = Pattern.compile("rotate (?<direction>\\w+) \\w=(?<index>\\d+) by (?<range>\\d+)");
        Set<Position> littenPixels = new HashSet<>();
        Position.setScreenLimits(50, 6);

        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while (null != (line = br.readLine())) {
                Matcher rectangleMatcher = rectanglePattern.matcher(line);
                if (rectangleMatcher.find()) {
                    int xSup = Integer.parseInt(rectangleMatcher.group("x"));
                    int ySup = Integer.parseInt(rectangleMatcher.group("y"));
                    IntStream.range(0, xSup)
                            .mapToObj(x -> IntStream.range(0, ySup)
                                    .mapToObj(y -> new Position(x, y))
                                    .collect(Collectors.toSet())
                            )
                            .flatMap(Set::stream)
                            .forEach(littenPixels::add);
                    continue;
                }

                Matcher rotationMatcher = rotationPattern.matcher(line);
                if (rotationMatcher.find()) {
                    Direction direction = Direction.valueOf(rotationMatcher.group("direction").toUpperCase());
                    int index = Integer.parseInt(rotationMatcher.group("index"));
                    int range = Integer.parseInt(rotationMatcher.group("range"));
                    littenPixels.stream()
                            .filter(pos -> index == direction.getPositionGetter().apply(pos))
                            .forEach(pos -> pos.rotate(direction, range));
                }
            }
        }

        int res = littenPixels.size();
        return print(res);
    }
}
