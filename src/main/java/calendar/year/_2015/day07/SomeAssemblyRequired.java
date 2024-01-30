package calendar.year._2015.day07;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SomeAssemblyRequired extends Exercise {
    public SomeAssemblyRequired(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        Set<Wire> wires = new HashSet<>();

        // Initialisation des données à partir de l'input
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            Pattern pattern = Pattern.compile("\\w+");
            while (null != (line = br.readLine())) {
                List<String> data = pattern.matcher(line).results()
                        .map(MatchResult::group)
                        .toList();

                Wire wire = switch (data.size()) {
                    case 2:
                        yield new Wire(LogicGate.VALUE, data.get(1), data.get(0), null);
                    case 3:
                        yield new Wire(LogicGate.NOT, data.get(2), data.get(1), null);
                    case 4:
                        yield new Wire(LogicGate.valueOf(data.get(1)), data.get(3), data.get(0), data.get(2));
                    default:
                        throw new IllegalArgumentException();
                };
                wires.add(wire);
            }
        }

        Map<String, Wire> wirePerTarget = wires.stream()
                .collect(Collectors.toMap(Wire::getTarget, Function.identity()));

        // Extraction du résultat
        int result = wires.stream()
                .filter(wire -> "a".equals(wire.getTarget()))
                .findFirst()
                .orElseThrow()
                .compute(wirePerTarget);

        return print(result);
    }
}
