package calendar.year._2017.day07;

import java.util.Map;
import java.util.Set;

public record Program(String name, int weight, Set<String> subPrograms) {

    /**
     * @param connexions All the tree connexions
     * @return The total weight of the program
     */
    public int computeWeight(Map<String, Set<Program>> connexions) {
        return weight + connexions.get(name).stream()
                .map(program -> program.computeWeight(connexions))
                .reduce(0, Integer::sum);
    }
}
