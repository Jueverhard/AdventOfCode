package calendar.year._2020.day05;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryBoarding extends Exercise {
    public BinaryBoarding(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<Seat> seats = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while (null != (line = br.readLine())) {
                seats.add(new Seat(line));
            }
        }

        int result = Part.PART_1 == part ?
                findHighestSeatId(seats) :
                findMissingSeatId(seats);
        return print(result);
    }

    private int findHighestSeatId(List<Seat> seats) {
        return seats.stream()
                .map(Seat::computePlacement)
                .map(Placement::computeSeatId)
                .max(Integer::compareTo)
                .orElseThrow();
    }

    private int findMissingSeatId(List<Seat> seats) {
        List<Integer> sortedSeatIds = seats.stream()
                .map(Seat::computePlacement)
                .map(Placement::computeSeatId)
                .sorted()
                .collect(Collectors.toList());

        int previous = sortedSeatIds.remove(0);
        for (int current : sortedSeatIds) {
            if (previous != current - 1) {
                return previous + 1;
            }
            previous = current;
        }

        throw new IllegalArgumentException("No missing seat was found");
    }
}
