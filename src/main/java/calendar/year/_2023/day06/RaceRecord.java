package calendar.year._2023.day06;

import java.util.stream.LongStream;

public record RaceRecord(long time, long bestDistance) {

    public long computeAllRecordBreakingPossibilities() {
        return LongStream.range(1, time)
                .map(chargingTime -> (time - chargingTime) * chargingTime)
                .filter(distance -> distance > bestDistance)
                .count();
    }
}
