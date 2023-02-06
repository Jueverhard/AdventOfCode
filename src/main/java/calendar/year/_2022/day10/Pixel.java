package calendar.year._2022.day10;

import lombok.Data;

@Data
public class Pixel {

    private PixelType type;

    private int position;

    private int line;

    public Pixel(int index, PixelType type) {
        this.position = index % 40;
        this.line = index / 40;
        this.type = type;
    }
}
