package calendar.year._2017.day07;

import org.apache.commons.lang3.tuple.Pair;
import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RecursiveCircus extends Exercise {

    public RecursiveCircus(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        // Initialize the data
        Set<Program> programs = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            Pattern pattern = Pattern.compile("(?<name>\\w+) \\((?<weight>\\d+)\\)( -> (?<subPrograms>.*))?");
            while (null != (line = br.readLine())) {
                Matcher matcher = pattern.matcher(line);
                if (!matcher.find()) {
                    throw new IllegalArgumentException();
                }

                String name = matcher.group("name");
                int weight = Integer.parseInt(matcher.group("weight"));
                String rawSubPrograms = matcher.group("subPrograms");
                Set<String> subPrograms = null == rawSubPrograms ?
                        Collections.emptySet() :
                        Arrays.stream(rawSubPrograms.split(", ")).collect(Collectors.toSet());
                programs.add(new Program(name, weight, subPrograms));
            }
        }

        // Compute the programs connexions
        Map<String, Program> programsPerName = programs.stream()
                .collect(Collectors.toMap(Program::name, Function.identity()));
        Map<String, Set<Program>> connexions = programsPerName.entrySet().stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> entry.getValue().subPrograms().stream()
                                .map(programsPerName::get)
                                .collect(Collectors.toSet())
                ));

        Object result;
        if (Part.PART_1 == part) {
            // Search the unique program that isn't target by another one
            Set<String> programNames = connexions.keySet();
            programNames.removeAll(connexions.values().stream()
                    .flatMap(Set::stream)
                    .map(Program::name)
                    .collect(Collectors.toSet())
            );

            result = programNames.iterator().next();
        } else {
            Program unbalancedProgram = programs.stream()
                    // Keep only the programs having distinct subprogram weights
                    .filter(program -> 1 < program.subPrograms().stream()
                            .map(programsPerName::get)
                            .map(subProgram -> subProgram.computeWeight(connexions))
                            .distinct()
                            .count()
                    )
                    // Keep the earliest unbalanced program
                    .min(Comparator.comparingInt(program -> program.computeWeight(connexions)))
                    .orElseThrow();

            int unbalancedWeight = connexions.get(unbalancedProgram.name()).stream()
                    .map(subProgram -> Pair.of(subProgram, subProgram.computeWeight(connexions)))
                    .collect(Collectors.groupingBy(Entry::getValue))
                    .values().stream()
                    .filter(pairs -> 1 == pairs.size())
                    .map(pairs -> pairs.get(0).getLeft())
                    .findAny().orElseThrow()
                    .weight();

            List<Integer> unbalancedWeights = connexions.get(unbalancedProgram.name()).stream()
                    .map(subProgram -> subProgram.computeWeight(connexions))
                    .distinct()
                    .sorted()
                    .toList();
            int delta = unbalancedWeights.get(1) - unbalancedWeights.get(0);

            result = unbalancedWeight - delta;
        }

        return print(result);
    }
}
