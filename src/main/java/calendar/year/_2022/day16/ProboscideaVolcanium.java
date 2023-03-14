package calendar.year._2022.day16;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProboscideaVolcanium extends Exercise {

    public ProboscideaVolcanium(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        // Initializes the valves
        Set<Valve> closeValves = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while ((line = br.readLine()) != null) {
                closeValves.add(new Valve(line));
            }
        }
        Valve currentValve = closeValves.stream()
                .filter(valve -> "AA".equals(valve.getId()))
                .findFirst().orElseThrow();

        // Defines the connections between the valves
        Map<Valve, Map<Valve, Integer>> connectionsCost = closeValves.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        fromValve -> defineConnections(fromValve, closeValves)
                ));

        // Substract the valves with null flow from the closed ones, as their opening won't change a thing
        Set<Valve> valvesWithNullFlow = closeValves.stream()
                .filter(valve -> valve.getFlowRate() == 0)
                .collect(Collectors.toSet());
        closeValves.removeAll(valvesWithNullFlow);

        return Part.PART_1.equals(part) ?
                exercise1(currentValve, closeValves, connectionsCost) :
                exercise2(currentValve, closeValves, connectionsCost);
    }

    private String exercise1(
            Valve currentValve,
            Set<Valve> closeValves,
            Map<Valve, Map<Valve, Integer>> connectionsCost
    ) {
        List<ValveOpening> valveOpeningOptions = exploreValveOpeningOptions(
                currentValve,
                closeValves,
                connectionsCost,
                30
        );

        Set<Integer> releasablePressures = valveOpeningOptions.stream()
                .map(ValveOpening::computeReleasablePressures)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        return print(releasablePressures.stream().max(Integer::compareTo).orElseThrow());
    }

    private String exercise2(
            Valve currentValve,
            Set<Valve> closeValves,
            Map<Valve, Map<Valve, Integer>> connectionsCost
    ) {
        return "TODO JEV";
    }

    private Map<Valve, Integer> defineConnections(Valve from, Set<Valve> valves) {
        // Defines every first-step connections
        Map<Valve, Integer> nbStepsToReachValve = valves.stream()
                .filter(toValve -> from.getReachableValveIds().contains(toValve.getId()))
                .collect(Collectors.toMap(
                        Function.identity(),
                        // One time unit to move + one time unit to open the valve
                        valve -> 2
                ));

        // Define all further connections
        Set<Valve> remainingValves = new HashSet<>(valves);
        remainingValves.remove(from);
        remainingValves.removeAll(nbStepsToReachValve.keySet());
        int nbMovesCpt = 3;
        while (!remainingValves.isEmpty() && nbMovesCpt < 30) {
            int nbMoves = nbMovesCpt;
            Set<Valve> reachedValves = new HashSet<>(nbStepsToReachValve.keySet());
            reachedValves.add(from);
            Map<Valve, Integer> nextStepReachedValves = reachedValves.stream()
                    .map(Valve::getReachableValveIds)
                    .flatMap(Set::stream)
                    .map(valveId -> valves.stream()
                            .filter(valve -> valveId.equals(valve.getId()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("There is no valve with id: " + valveId))
                    )
                    .filter(Predicate.not(reachedValves::contains))
                    .distinct()
                    .collect(Collectors.toMap(
                            Function.identity(),
                            valve -> nbMoves
                    ));
            nbStepsToReachValve.putAll(nextStepReachedValves);

            remainingValves.removeAll(nbStepsToReachValve.keySet());
            nbMovesCpt++;
        }

        return nbStepsToReachValve;
    }

    private List<ValveOpening> exploreValveOpeningOptions(
            Valve currentValve,
            Set<Valve> closeValves,
            Map<Valve, Map<Valve, Integer>> connectionsCost,
            int timeLeft
    ) {
        if (closeValves.isEmpty()) {
            // All valves have already been opened
            return Collections.emptyList();
        }
        if (timeLeft <= 1) {
            // There is no time left to open some valves and release pressure
            return Collections.emptyList();
        }

        // Computes releasable pressure for each remaining valve to open
        List<ValveOpening> releasablePressurePerValve = connectionsCost.get(currentValve).entrySet().stream()
                .filter(entry -> closeValves.contains(entry.getKey()))
                .filter(entry -> entry.getValue() <= timeLeft)
                .map(entry -> new ValveOpening(
                        entry.getKey(),
                        entry.getKey().getFlowRate() * (timeLeft - entry.getValue())
                )).collect(Collectors.toList());

        // Computes the releasable pressure recursively
        for (ValveOpening valveOpening : releasablePressurePerValve) {
            Valve newCurrentValve = valveOpening.getValve();
            Set<Valve> newCloseValves = new HashSet<>(closeValves);
            newCloseValves.remove(newCurrentValve);
            int newTimeLeft = timeLeft - connectionsCost.get(currentValve).get(newCurrentValve);

            valveOpening.setOpenableValves(
                    exploreValveOpeningOptions(
                            newCurrentValve,
                            newCloseValves,
                            connectionsCost,
                            newTimeLeft
                    )
            );
        }

        return releasablePressurePerValve;
    }
}
