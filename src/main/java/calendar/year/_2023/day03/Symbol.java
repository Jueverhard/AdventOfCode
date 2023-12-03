package calendar.year._2023.day03;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public record Symbol(Position position, String value) {

    /**
     * A gear is any * symbol that is adjacent to exactly two part numbers
     *
     * @param numbers The part numbers
     * @return The optional part numbers involved in the gear
     */
    public Optional<Pair<Integer, Integer>> isGearMaterial(Set<Number> numbers) {
        if (!"*".equals(value)) {
            return Optional.empty();
        }

        List<Number> gearNumbers = numbers.stream()
                .filter(number -> number.isNearAnySymbol(Set.of(this)))
                .toList();

        return 2 != gearNumbers.size() ?
                Optional.empty() :
                Optional.of(Pair.of(gearNumbers.get(0).value(), gearNumbers.get(1).value()));
    }
}
