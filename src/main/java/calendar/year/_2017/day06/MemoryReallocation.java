package calendar.year._2017.day06;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class MemoryReallocation extends Exercise {
    public MemoryReallocation(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        Bank currentBank;
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            List<Integer> bankBlocks = Pattern.compile("\\d+").matcher(br.readLine()).results()
                    .map(MatchResult::group)
                    .map(Integer::parseInt)
                    .toList();
            currentBank = new Bank(bankBlocks);
        }
        Set<Bank> seenBanks = new HashSet<>();
        while (seenBanks.add(currentBank.copyOf())) {
            currentBank.rebalanceBlocks();
        }

        if (Part.PART_1 == part) {
            return print(seenBanks.size());
        }

        Bank repeatedBank = currentBank.copyOf();
        int cycleSize = 0;
        do {
            currentBank.rebalanceBlocks();
            cycleSize++;
        } while (!currentBank.equals(repeatedBank));

        return print(cycleSize);
    }
}
