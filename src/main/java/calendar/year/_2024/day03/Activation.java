package calendar.year._2024.day03;

import lombok.Getter;

@Getter
public final class Activation extends Operation {
    
    private final boolean doesActivate;

    public Activation(String input) {
        super();
        this.doesActivate = "do()".equals(input);
    }
}
