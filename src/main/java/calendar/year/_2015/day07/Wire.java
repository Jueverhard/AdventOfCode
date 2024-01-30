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
        try {
            this.firstValue = Integer.parseInt(firstWire);
        } catch (NumberFormatException e) {
            this.firstWire = firstWire;
        }
        try {
            this.secondValue = Integer.parseInt(secondWire);
        } catch (NumberFormatException e) {
            this.secondWire = secondWire;
        }
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
}
