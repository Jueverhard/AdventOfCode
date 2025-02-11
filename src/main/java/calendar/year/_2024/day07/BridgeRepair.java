package calendar.year._2024.day07;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class BridgeRepair extends Exercise {

    public BridgeRepair(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<Operation> operations = new ArrayList<>();

        // Data initialization
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            Pattern numberPattern = Pattern.compile("\\d+");
            while (null != (line = br.readLine())) {
                List<Long> operationData = numberPattern.matcher(line).results()
                        .map(MatchResult::group)
                        .map(Long::parseLong)
                        .toList();

                operations.add(new Operation(operationData.get(0), operationData.subList(1, operationData.size())));
            }
        }

        long result = operations.stream()
                .filter(operation -> operation.mayComputesInto(Part.PART_2 == part))
                .map(Operation::result)
                .reduce(Long::sum)
                .orElseThrow();

        return print(result);
    }
}
