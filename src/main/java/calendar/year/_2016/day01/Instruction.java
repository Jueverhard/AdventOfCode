package calendar.year._2016.day01;

import lombok.Data;

@Data
public class Instruction {

    private Turn turn;

    private int distance;

    public Instruction(String instruction) {
        this.turn = Turn.valueOf(instruction.substring(0, 1));
        this.distance = Integer.parseInt(instruction.substring(1));
    }
}
