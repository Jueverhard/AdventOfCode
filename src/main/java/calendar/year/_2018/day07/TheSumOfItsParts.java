package calendar.year._2018.day07;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TheSumOfItsParts extends Exercise {
    public TheSumOfItsParts(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        // Data initialization
        Set<Step> steps = initializeSteps(testMode);

        // Initial steps extraction, ordered
        TreeSet<Step> currentSteps = steps.stream()
                .filter(step -> step.getPreviousSteps().isEmpty())
                .collect(Collectors.toCollection(TreeSet::new));

        LinkedHashSet<String> treatedStepNames = new LinkedHashSet<>();
        while (!currentSteps.isEmpty()) {
            Step currentStep = currentSteps.pollFirst();
            Objects.requireNonNull(currentStep);
            currentSteps.remove(currentStep);
            String currentStepName = currentStep.getName();
            treatedStepNames.add(currentStepName);

            steps.stream()
                    // Keep the steps following the current one
                    .filter(step -> step.getPreviousSteps().contains(currentStepName))
                    // Keep the steps having all previous steps already completed
                    .filter(step -> treatedStepNames.containsAll(step.getPreviousSteps()))
                    // Add the resulting steps to the to-be-treated ones
                    .forEach(currentSteps::add);
        }

        String result = String.join("", treatedStepNames);
        return print(result);
    }

    /**
     * @param testMode Whether the steps are to be extracted from test input
     * @return The extracted steps
     */
    private Set<Step> initializeSteps(boolean testMode) throws IOException {
        record StepDirectConnexion(String stepName, String nextStepName) {}

        Set<StepDirectConnexion> stepDirectConnexions = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            Pattern stepPattern = Pattern.compile("Step (?<name>\\w+) must be finished before step (?<nextStep>\\w+) can begin");
            String input;
            while (null != (input = br.readLine())) {
                Matcher matcher = stepPattern.matcher(input);
                if (!matcher.find()) {
                    throw new IllegalArgumentException();
                }

                String stepName = matcher.group("name");
                String nextStep = matcher.group("nextStep");
                stepDirectConnexions.add(new StepDirectConnexion(stepName, nextStep));
            }
        }

        // Compute previous steps for each step
        Map<String, List<String>> previousStepsForStep = stepDirectConnexions.stream()
                .collect(Collectors.groupingBy(
                        StepDirectConnexion::nextStepName,
                        Collectors.mapping(StepDirectConnexion::stepName, Collectors.toList())
                ));

        // Initialize all steps having requirements
        Set<Step> steps = previousStepsForStep
                .entrySet().stream()
                .map(entry -> new Step(entry.getKey(), entry.getValue()))
                .collect(Collectors.toSet());

        // Add steps that doesn't rely on any step
        previousStepsForStep.values().stream()
                .flatMap(List::stream)
                .distinct()
                .filter(previousStep -> !previousStepsForStep.containsKey(previousStep))
                .forEach(noRequirementStepName -> steps.add(new Step(noRequirementStepName)));

        return steps;
    }
}
