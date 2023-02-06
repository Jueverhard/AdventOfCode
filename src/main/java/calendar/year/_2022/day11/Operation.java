package calendar.year._2022.day11;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Operation {

    private OperationType type;

    private String coefficient;

    public void apply(Item item) {
        long coeff = "old".equals(this.coefficient) ?
                item.getWorryLevel() :
                Long.parseLong(this.coefficient);
        if (OperationType.ADD.equals(type)) {
            item.setWorryLevel(item.getWorryLevel() + coeff);
        } else {
            item.setWorryLevel(item.getWorryLevel() * coeff);
        }
    }
}
