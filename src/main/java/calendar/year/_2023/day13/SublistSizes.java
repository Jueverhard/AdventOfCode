package calendar.year._2023.day13;

import lombok.Getter;

public class SublistSizes {

    private int size;

    @Getter
    private int firstPartBeginning;

    @Getter
    private int secondPartBeginning;

    public SublistSizes(int listSize, int index) {
        this.size = Math.min(index, listSize - index);
        boolean indexIsLowerThanHalf = index < listSize - index;
        this.firstPartBeginning = indexIsLowerThanHalf ?
                0 :
                listSize - 2 * size;
        this.secondPartBeginning = firstPartBeginning + size;
    }

    public int getSecondPartEnding() {
        return secondPartBeginning + size;
    }
}
