package calendar.year._2015.day07;

import java.util.function.BinaryOperator;

public enum LogicGate {
    VALUE((a, b) -> a),
    AND((a, b) -> a & b),
    OR((a, b) -> a | b),
    LSHIFT((a, b) -> a << b),
    RSHIFT((a, b) -> a >> b),
    NOT((a, b) -> ~a);

    private final BinaryOperator<Integer> logicOperation;

    LogicGate(BinaryOperator<Integer> logicOperation) {
        this.logicOperation = logicOperation;
    }

    public int compute(int a, Integer b) {
        return this.logicOperation.apply(a, b);
    }
}
