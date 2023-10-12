package calendar.year._2017.day06;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Bank {

    private List<Integer> blocks;

    public Bank(List<Integer> blocks) {
        this.blocks = new ArrayList<>(blocks);
    }

    public Bank copyOf() {
        return new Bank(this.blocks);
    }

    public void rebalanceBlocks() {
        int maxBlocksQuantity = Collections.max(this.blocks);
        int index = this.blocks.indexOf(maxBlocksQuantity);
        this.blocks.set(index, 0);

        int nbBlocks = this.blocks.size();
        for (int i = 1; i <= maxBlocksQuantity; i++) {
            int currentIndex = (index + i) % nbBlocks;
            this.blocks.set(currentIndex, this.blocks.get(currentIndex) + 1);
        }
    }
}
