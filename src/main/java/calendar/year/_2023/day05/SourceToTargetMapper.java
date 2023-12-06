package calendar.year._2023.day05;

import java.util.Optional;

public record SourceToTargetMapper(long destinationRangeStart, long sourceRangeStart, long rangeLength) {

    public Optional<Long> mapsValue(long value) {
        if (value < sourceRangeStart || value >= sourceRangeStart + rangeLength) {
            return Optional.empty();
        }

        return Optional.of(destinationRangeStart + (value - sourceRangeStart));
    }

    public SourceToTargetMapper copyOf() {
        return new SourceToTargetMapper(destinationRangeStart, sourceRangeStart, rangeLength);
    }
}
