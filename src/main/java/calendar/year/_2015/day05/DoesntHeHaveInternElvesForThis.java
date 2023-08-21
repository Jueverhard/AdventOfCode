package calendar.year._2015.day05;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DoesntHeHaveInternElvesForThis extends Exercise {
    public DoesntHeHaveInternElvesForThis(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<NiceStringCandidate> candidates = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while (null != (line = br.readLine())) {
                NiceStringCandidate candidate = Part.PART_1 == part ?
                        new NiceStringCandidate(line) :
                        new NiceStringCandidatePart2(line);
                candidates.add(candidate);
            }
        }

        long nbNiceStrings = candidates.stream()
                .filter(NiceStringCandidate::isNice)
                .count();
        return print(nbNiceStrings);
    }
}
