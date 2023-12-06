package calendar.year._2023.day05;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class IfYouGiveASeedAFertilizer extends Exercise {

    public IfYouGiveASeedAFertilizer(LocalDate date) {
        super(date);
    }

    private static final Pattern DIGIT_PATTERN = Pattern.compile("\\d+");

    @Override
    @SuppressWarnings("java:S2677") // Mute the warnings about unused method returns
    public String run(Part part, boolean testMode) throws IOException {
        Set<Long> seeds = Collections.emptySet();
        List<Set<SourceToTargetMapper>> stepMappers = new ArrayList<>();

        record SeedPart2(long start, long range) {}
        Set<SeedPart2> seedPart2s = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line = br.readLine();

            // Initialize the seeds
            if (Part.PART_1 == part) {
                seeds = DIGIT_PATTERN.matcher(line).results()
                        .map(MatchResult::group)
                        .map(Long::parseLong)
                        .collect(Collectors.toSet());
            } else {
                Pattern numbersPairPattern = Pattern.compile("(\\d+ \\d+)");
                seedPart2s = numbersPairPattern.matcher(line).results()
                        .map(MatchResult::group)
                        .map(numbersPairString -> numbersPairString.split(" "))
                        .map(numbers -> new SeedPart2(Long.parseLong(numbers[0]), Long.parseLong(numbers[1])))
                        .collect(Collectors.toSet());
            }

            // Skip a separating line
            br.readLine();

            // Initialize the mappers
            final Set<SourceToTargetMapper> currentStepMappers = new HashSet<>();
            while (null != (line = br.readLine())) {
                if (line.endsWith("map:")) {
                    // Do nothing, this defines the start of a new kind of mappers
                } else if (line.isBlank()) {
                    // Register every found mappers
                    Set<SourceToTargetMapper> mappers = currentStepMappers.stream()
                            .map(SourceToTargetMapper::copyOf)
                            .collect(Collectors.toSet());
                    stepMappers.add(mappers);
                    currentStepMappers.clear();
                } else {
                    // Extract the mapper from the input dans add it to the current set
                    currentStepMappers.add(extractSourceToTargetMapper(line));
                }
            }
        }

        long result;
        if (Part.PART_1 == part) {
            result = findSmallestLocation(seeds, stepMappers);
        } else {
            result = seedPart2s.parallelStream()
                    .flatMapToLong(seedPart2 -> LongStream.range(seedPart2.start(), seedPart2.start() + seedPart2.range()))
                    .map(seed -> computeFinalLocation(seed, stepMappers))
                    .min().orElseThrow();
        }

        return print(result);
    }

    private SourceToTargetMapper extractSourceToTargetMapper(String input) {
        List<Long> numbers = DIGIT_PATTERN.matcher(input).results()
                .map(MatchResult::group)
                .map(Long::parseLong)
                .toList();
        return new SourceToTargetMapper(numbers.get(0), numbers.get(1), numbers.get(2));
    }

    private long computeMappedValue(long value, Set<SourceToTargetMapper> mappers) {
        return mappers.stream()
                .map(mapper -> mapper.mapsValue(value))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElse(value);
    }

    private long findSmallestLocation(final Set<Long> seeds, final List<Set<SourceToTargetMapper>> stepMappers) {
        Set<Long> previousValues;
        Set<Long> currentValues = new HashSet<>(seeds);
        for (Set<SourceToTargetMapper> mappers : stepMappers) {
            previousValues = Set.copyOf(currentValues);
            currentValues = previousValues.stream()
                    .map(previousValue -> computeMappedValue(previousValue, mappers))
                    .collect(Collectors.toSet());
        }
        return Collections.min(currentValues);
    }

    private long computeFinalLocation(long seed, final List<Set<SourceToTargetMapper>> stepMappers) {
        long value = seed;
        for (Set<SourceToTargetMapper> mappers : stepMappers) {
            value = computeMappedValue(value, mappers);
        }
        return value;
    }
}
