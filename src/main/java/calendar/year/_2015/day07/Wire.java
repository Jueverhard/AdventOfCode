package calendar.year._2015.day07;

import lombok.Getter;

import java.util.Map;
import java.util.Set;

public class Wire {

    private final LogicGate logicGate;

    @Getter
    private final String target;

    private String firstWire;

    private String secondWire;

    private Integer firstValue;

    private Integer secondValue;

    public Wire(LogicGate logicGate, String target, String firstWire, String secondWire) {
        this.logicGate = logicGate;
        this.target = target;
        this.firstWire = firstWire;
        this.secondWire = secondWire;
        try {
            this.firstValue = Integer.parseInt(firstWire);
        } catch (NumberFormatException ignored) {}
        try {
            this.secondValue = Integer.parseInt(secondWire);
        } catch (NumberFormatException ignored) {}
    }

    public int compute(Map<String, Wire> wirePerTarget) {
        if (null == this.firstValue) {
            this.firstValue = wirePerTarget.get(firstWire).compute(wirePerTarget);
        }

        if (null == secondValue && !Set.of(LogicGate.VALUE, LogicGate.NOT).contains(this.logicGate)) {
            this.secondValue = wirePerTarget.get(secondWire).compute(wirePerTarget);
        }

        return this.logicGate.compute(firstValue, secondValue);
    }

    public Wire copyOf() {
        return new Wire(logicGate, target, firstWire, secondWire);
    }
}
