package calendar.year._2022.day11;

import utils.enums.Part;
import lombok.Data;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Monkey {

    private int num;

    private Deque<Item> items;

    private Operation operation;

    private int testFunctionCoefficient;

    private int targetIfTrue;

    private int targetIfFalse;

    private int inspectionCount;

    private boolean feelingRelief;

    private static int MODULO = 0;

    public Monkey(List<String> monkeyInputLines, Part part) {
        this.num = defineNumber(monkeyInputLines.get(0));
        this.items = defineItems(monkeyInputLines.get(1));
        this.operation = defineOperation(monkeyInputLines.get(2));
        this.testFunctionCoefficient = defineTestFunctionCoefficient(monkeyInputLines.get(3));
        this.targetIfTrue = defineTargetIfTrue(monkeyInputLines.get(4));
        this.targetIfFalse = defineTargetIfFalse(monkeyInputLines.get(5));
        this.inspectionCount = 0;
        this.feelingRelief = Part.PART_1.equals(part);
    }

    public boolean inspect(Item item) {
        this.inspectionCount++;
        this.operation.apply(item);
        if (this.feelingRelief) {
            item.setWorryLevel(item.getWorryLevel() / 3);
        }
        return item.getWorryLevel() % this.testFunctionCoefficient == 0;
    }

    public void throwItem(List<Monkey> monkeys) {
        Item item = this.items.remove();
        int targetNumber = this.inspect(item) ? targetIfTrue : targetIfFalse;
        item.setWorryLevel(item.getWorryLevel() % MODULO);
        Monkey target = this.defineTarget(monkeys, targetNumber);
        target.getItems().add(item);
    }

    private Monkey defineTarget(List<Monkey> monkeys, int targetNumber) {
        return monkeys.stream()
                .filter(monkey -> monkey.getNum() == targetNumber)
                .findFirst().orElseThrow();
    }

    private int defineNumber(String numberLine) {
        return Integer.parseInt(numberLine.substring(7, 8));
    }

    private Deque<Item> defineItems(String itemsLine) {
        String worryLevels = itemsLine.split(":")[1];
        return Arrays.stream(worryLevels.split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .mapToObj(Item::new)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    private Operation defineOperation(String operationLine) {
        String calculation = operationLine.split("= ")[1];
        String[] members = calculation.split(" ");
        switch (members[1]) {
            case "+":
                return new Operation(OperationType.ADD, members[2]);
            case "*":
                return new Operation(OperationType.MUL, members[2]);
            default:
                throw new IllegalArgumentException("Unexpected operator: " + members[1]);
        }
    }

    private int defineTestFunctionCoefficient(String testFunctionLine) {
        return Integer.parseInt(testFunctionLine.split("by ")[1]);
    }

    private int defineTargetIfTrue(String targetIfTrueLine) {
        return Integer.parseInt(targetIfTrueLine.substring(29, 30));
    }

    private int defineTargetIfFalse(String targetIfFalseLine) {
        return Integer.parseInt(targetIfFalseLine.substring(30, 31));
    }

    public static void setModulo(int modulo) {
        MODULO = modulo;
    }
}
