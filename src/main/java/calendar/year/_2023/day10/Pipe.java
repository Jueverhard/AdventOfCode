package calendar.year._2023.day10;

public record Pipe(PipeKind pipeKind, Position position) {

    public boolean canConnectWith(Pipe pipe) {
        boolean result = false;
        if (this.pipeKind.canGo(Direction.DOWN) && pipe.pipeKind().canGo(Direction.UP)) {
            result |= this.position.x() == pipe.position().x() && -1 == this.position.y() - pipe.position().y();
        }
        if (this.pipeKind.canGo(Direction.UP) && pipe.pipeKind().canGo(Direction.DOWN)) {
            result |= this.position.x() == pipe.position().x() && 1 == this.position.y() - pipe.position().y();
        }
        if (this.pipeKind.canGo(Direction.LEFT) && pipe.pipeKind().canGo(Direction.RIGHT)) {
            result |= this.position.y() == pipe.position().y() && 1 == this.position.x() - pipe.position().x();
        }
        if (this.pipeKind.canGo(Direction.RIGHT) && pipe.pipeKind().canGo(Direction.LEFT)) {
            result |= this.position.y() == pipe.position().y() && -1 == this.position.x() - pipe.position().x();
        }
        return result;
    }
}
