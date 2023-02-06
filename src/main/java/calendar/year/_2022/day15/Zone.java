package calendar.year._2022.day15;

import lombok.Data;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Data
public class Zone {

    /**
     * Description of the zone
     */
    private Map<Position, Spot> spots;

    /**
     * Zone limits
     */
    private long xMin;
    private long xMax;
    private long yMin;
    private long yMax;

    public Zone(List<String> lines) {
        spots = new HashMap<>();

        // Initializes the zone description
        Pattern pattern = Pattern.compile("-?\\d+");
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            List<Long> coordinates = new ArrayList<>();
            while (matcher.find()) {
                coordinates.add(Long.parseLong(matcher.group()));
            }
            spots.put(new Position(coordinates.get(0), coordinates.get(1)), Spot.SENSOR);
            spots.put(new Position(coordinates.get(2), coordinates.get(3)), Spot.BEACON);
        }

        // Defines the zone limits
        xMin = spots.keySet().stream()
                .map(Position::getX)
                .min(Comparator.naturalOrder()).orElseThrow();
        xMax = spots.keySet().stream()
                .map(Position::getX)
                .max(Comparator.naturalOrder()).orElseThrow();
        yMin = spots.keySet().stream()
                .map(Position::getY)
                .min(Comparator.naturalOrder()).orElseThrow();
        yMax = spots.keySet().stream()
                .map(Position::getY)
                .max(Comparator.naturalOrder()).orElseThrow();
    }

    public List<Position> defineNoBeaconSpots(long y) {
        List<Position> sensorPositions = spots.entrySet().stream()
                .filter(entry -> Spot.SENSOR.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        List<Position> beaconPositions = spots.entrySet().stream()
                .filter(entry -> Spot.BEACON.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // Computes the distance between each sensor and its closest beacon
        Map<Position, Long> distanceToClosestBeacon = sensorPositions.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        sensorPosition -> beaconPositions.stream()
                                .mapToLong(sensorPosition::computeManhattanDistance)
                                .min().orElse(1L)
                ));

        long deltaMax = Math.max(yMax - yMin, xMax - xMin);
        // Defines the spots detected as couldn't be holding a beacon
        return LongStream.range(xMin - deltaMax, xMax + deltaMax)
                .mapToObj(x -> new Position(x, y))
                .filter(position -> distanceToClosestBeacon.entrySet().stream()
                        .anyMatch(entry -> entry.getKey().computeManhattanDistance(position) <= entry.getValue())
                ).filter(position -> !spots.containsKey(position))
                .collect(Collectors.toList());
    }

    public Position defineDistressBeaconSpot(long minCoordinate, long maxCoordinate) {
        List<Long> longs = LongStream.range(minCoordinate, maxCoordinate + 1)
                .boxed()
                .collect(Collectors.toList());
        for (long y = minCoordinate; y <= maxCoordinate; y++) {
            List<Position> impossiblePositions = defineNoBeaconSpots(y);
            impossiblePositions.addAll(spots.keySet());
            List<Long> impossibleXs = impossiblePositions.stream()
                    .map(Position::getX)
                    .collect(Collectors.toList());

            Optional<Long> x = longs.stream()
                    .filter(Predicate.not(impossibleXs::contains))
                    .findFirst();
            if (x.isPresent()) {
                return new Position(x.get(), y);
            }
        }

        // FIXME : No position available ???
        throw new IllegalStateException("No position available for the distress beacon");
    }
}
