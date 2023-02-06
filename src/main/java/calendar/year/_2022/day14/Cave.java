package calendar.year._2022.day14;

import lombok.Data;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class Cave {

    private Position sandFloodingPoint;

    private Map<Position, BlockType> blocks = new HashMap<>();

    /**
     * Cave limits
     */
    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;

    public Cave(Position sandFloodingPoint) {
        this.sandFloodingPoint = sandFloodingPoint;
    }

    public void fillWithAir() {
        xMin = blocks.keySet().stream()
                .map(Position::getX)
                .min(Comparator.naturalOrder()).orElseThrow();
        xMax = blocks.keySet().stream()
                .map(Position::getX)
                .max(Comparator.naturalOrder()).orElseThrow();
        yMin = 0;
        yMax = blocks.keySet().stream()
                .map(Position::getY)
                .max(Comparator.naturalOrder()).orElseThrow();

        IntStream.range(xMin, xMax + 1)
                .mapToObj(x -> IntStream.range(yMin, yMax + 1)
                        .mapToObj(y -> new Position(x, y))
                        .collect(Collectors.toList())
                ).flatMap(List::stream)
                .forEach(position -> blocks.putIfAbsent(position, BlockType.AIR));
    }

    public void fillWithAirPart2() {
        xMin = blocks.keySet().stream()
                .map(Position::getX)
                .min(Comparator.naturalOrder()).orElseThrow();
        xMax = blocks.keySet().stream()
                .map(Position::getX)
                .max(Comparator.naturalOrder()).orElseThrow();
        yMin = 0;
        yMax = blocks.keySet().stream()
                .map(Position::getY)
                .max(Comparator.naturalOrder()).orElseThrow() + 2;

        xMin = Math.min(xMin, sandFloodingPoint.getX() - yMax);
        xMax = Math.max(xMax, sandFloodingPoint.getX() + yMax);

        // Create the "infinite" rock floor
        IntStream.range(xMin, xMax + 1)
                .mapToObj(x -> new Position(x, yMax))
                .forEach(position -> blocks.put(position, BlockType.ROCK));

        // Fill the cave with air
        IntStream.range(xMin, xMax + 1)
                .mapToObj(x -> IntStream.range(yMin, yMax)
                        .mapToObj(y -> new Position(x, y))
                        .collect(Collectors.toList())
                ).flatMap(List::stream)
                .forEach(position -> blocks.putIfAbsent(position, BlockType.AIR));
    }

    public void fillWithSand() {
        long nbSandBlocks = 0;
        long previousNbSandBlocks;
        do {
            previousNbSandBlocks = nbSandBlocks;

            try {
                blocks.replace(evaluateSandDropPoint(), BlockType.SAND);
            } catch (FellInTheAbyssException e) {
                return;
            }

            nbSandBlocks = getNbSandBlocks();
        } while (nbSandBlocks > previousNbSandBlocks);
    }

    public void fillWithSandPart2() {
        do {
            try {
                blocks.replace(evaluateSandDropPoint(), BlockType.SAND);
            } catch (FellInTheAbyssException e) {
                return;
            }
        } while (!blocks.get(sandFloodingPoint).equals(BlockType.SAND));
    }

    private Position evaluateSandDropPoint() throws FellInTheAbyssException {
        Position oldPos = sandFloodingPoint.copy();
        Position newPos = evaluateNewSandPosition(oldPos);

        while (!oldPos.equals(newPos)) {
            oldPos = newPos;
            newPos = evaluateNewSandPosition(oldPos);
        }

        return newPos;
    }

    private Position evaluateNewSandPosition(Position sandBlockPosition) throws FellInTheAbyssException {
        List<Position> positions = List.of(
                sandBlockPosition.getBottom(),
                sandBlockPosition.getBottomLeft(),
                sandBlockPosition.getBottomRight()
        );

        for (Position position : positions) {
            if (isOffLimits(position)) {
                throw new FellInTheAbyssException();
            }
            if (BlockType.AIR.equals(blocks.get(position))) {
                return position;
            }
        }

        return sandBlockPosition.copy();
    }

    private boolean isOffLimits(Position position) {
        return !blocks.containsKey(position);
    }

    public long getNbSandBlocks() {
        return blocks.values().stream()
                .filter(BlockType.SAND::equals)
                .count();
    }

    @Override
    public String toString() {
        String res = "";
        for (int y = yMin; y <= yMax; y++) {
            for (int x = xMin; x <= xMax; x++) {
                res = res.concat("" + blocks.get(new Position(x, y)).getRepresentation());
            }
            res = res.concat("\n");
        }
        return res;
    }
}
