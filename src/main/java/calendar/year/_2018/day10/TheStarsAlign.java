package calendar.year._2018.day10;

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

public class TheStarsAlign extends Exercise {
    public TheStarsAlign(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        // Data initialization
        List<Light> lights = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            Pattern pattern = Pattern.compile("position=<\\s*(?<positionX>-?\\d+),\\s*(?<positionY>-?\\d+)> velocity=<\\s*(?<velocityX>-?\\d+),\\s*(?<velocityY>-?\\d+)>");
            String line;
            while (null != (line = br.readLine())) {
                Matcher matcher = pattern.matcher(line);
                if (!matcher.find()) {
                    throw new IllegalArgumentException();
                }

                lights.add(new Light(
                        Integer.parseInt(matcher.group("positionX")),
                        Integer.parseInt(matcher.group("positionY")),
                        Integer.parseInt(matcher.group("velocityX")),
                        Integer.parseInt(matcher.group("velocityY"))
                ));
            }
        }

        // Processes the lights movements until they align
        Area currentArea = computeArea(lights);
        Area previousArea = new Area(currentArea.xMin(), currentArea.xMax() + 1, currentArea.yMin(), currentArea.yMax());
        int nbIterations = 0;
        while (currentArea.computeSurface() < previousArea.computeSurface()) {
            lights.forEach(Light::move);
            previousArea = currentArea;
            currentArea = computeArea(lights);
            nbIterations++;
        }

        // Moves the lights back to their previous positions, because we had to go one iteration too far to identify the end
        lights.forEach(Light::moveBack);

        if (Part.PART_1 == part) {
            return printLights(lights, previousArea);
        } else {
            return print(nbIterations - 1);
        }
    }

    /**
     * @param lights Lights.
     * @return The surface area of the lights.
     */
    private Area computeArea(List<Light> lights) {
        int minX = lights.stream()
                .map(Light::getXPos)
                .min(Integer::compareTo).orElseThrow();
        int maxX = lights.stream()
                .map(Light::getXPos)
                .max(Integer::compareTo).orElseThrow();
        int minY = lights.stream()
                .map(Light::getYPos)
                .min(Integer::compareTo).orElseThrow();
        int maxY = lights.stream()
                .map(Light::getYPos)
                .max(Integer::compareTo).orElseThrow();

        return new Area(minX, maxX, minY, maxY);
    }

    /**
     * Prints the lights on the screen.
     *
     * @param lights Lights to print.
     * @param area   Global area of the lights.
     * @return The lights printed pattern.
     */
    private String printLights(List<Light> lights, Area area) {
        StringBuilder builder = new StringBuilder();
        for (int y = area.yMin(); y <= area.yMax(); y++) {
            for (int x = area.xMin(); x <= area.xMax(); x++) {
                int finalX = x;
                int finalY = y;
                boolean isLightPresent = lights.stream()
                        .anyMatch(light -> finalX == light.getXPos() && finalY == light.getYPos());
                builder.append(isLightPresent ? '#' : '.');
            }
            builder.append("\n");
        }

        return print(builder);
    }
}
