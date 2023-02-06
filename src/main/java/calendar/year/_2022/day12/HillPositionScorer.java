package calendar.year._2022.day12;

import utils.pathfinding.Scorer;

public class HillPositionScorer implements Scorer<HillPosition>  {

    @Override
    public int computeCost(HillPosition from, HillPosition to) {
        return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY());
    }
}
