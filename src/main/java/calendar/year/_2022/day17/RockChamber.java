package calendar.year._2022.day17;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RockChamber {

    private final Limits limits;

    private final List<Rock> existingRocks;

    private final List<GasJet> gasJets;

    @Getter
    private final Set<Rock> fallenRocks;

    private long gasJetsCount;

    public RockChamber(List<Rock> existingRocks, String[] gasJets) {
        this.limits = new Limits(Long.MAX_VALUE, 6L, 0L, 0L);
        this.fallenRocks = new HashSet<>();
        this.existingRocks = existingRocks;
        this.gasJets = Arrays.stream(gasJets)
                .map(GasJet::fromRepresentation)
                .collect(Collectors.toList());
        this.gasJetsCount = 0L;
    }

    public void dropRock() {
        // Generates a new rock to fall
        Rock fallingRock = existingRocks.get(fallenRocks.size() % existingRocks.size()).copyOf();
        fallingRock.getBottomLeft().setX(2);
        fallingRock.getBottomLeft().setY(computeHeight() + 3);

        // Lets the rock fall
        boolean isBlocked = false;
        while (!isBlocked) {
            applyGasJet(fallingRock, gasJets.get((int) (gasJetsCount++ % gasJets.size())));

            try {
                fall(fallingRock);
            } catch (RockCantMoveDownException e) {
                isBlocked = true;
            }
        }

        // The rock has landed
        fallenRocks.add(fallingRock);
    }

    public void applyGasJet(Rock fallingRock, GasJet gasJet) {
        // Determines the positions that would be occupied by the rock if it were to be pushed
        Position nextReferalPosition = fallingRock.getBottomLeft().copyOf();
        if (GasJet.RIGHT.equals(gasJet)) {
            nextReferalPosition.setX(nextReferalPosition.getX() + 1);
        } else {
            nextReferalPosition.setX(nextReferalPosition.getX() - 1);
        }
        Set<Position> nextPositions = fallingRock.getRelativePositions().stream()
                .map(relativePosition -> relativePosition.add(nextReferalPosition))
                .collect(Collectors.toSet());

        // If the rock were to be pushed through the chamber limits, then nothing happens
        if (nextPositions.stream().anyMatch(pos -> pos.getX() < limits.getLeft() || pos.getX() > limits.getRight())) {
            return;
        }

        // If the rock were to be pushed into another rock, then nothing happens
        boolean rockIsAgainstAnotherRock = fallenRocks.stream()
                .map(rock -> rock.getRelativePositions().stream()
                        .map(relativePosition -> relativePosition.add(rock.getBottomLeft()))
                        .collect(Collectors.toSet())
                ).flatMap(Set::stream)
                .anyMatch(nextPositions::contains);
        if (rockIsAgainstAnotherRock) {
            return;
        }

        try {
            fallingRock.getBottomLeft().move(Direction.valueOf(gasJet.name()), limits);
        } catch (RockCantMoveDownException e) {
            throw new IllegalArgumentException("A gas jet shouldn't be able to move a rock to the bottom");
        }
    }

    public void fall(Rock fallingRock) throws RockCantMoveDownException {
        // Determines the positions that would be occupied by the rock if it were to fall
        Position nextReferalPosition = fallingRock.getBottomLeft().copyOf();
        nextReferalPosition.move(Direction.BOTTOM, limits);
        Set<Position> nextPositions = fallingRock.getRelativePositions().stream()
                .map(relativePosition -> relativePosition.add(nextReferalPosition))
                .collect(Collectors.toSet());

        // Checks whether at least one of those positions isn't free
        boolean rockHasLanded = fallenRocks.stream()
                .map(rock -> rock.getRelativePositions().stream()
                        .map(relativePosition -> relativePosition.add(rock.getBottomLeft()))
                        .collect(Collectors.toSet())
                ).flatMap(Set::stream)
                .anyMatch(nextPositions::contains);

        if (rockHasLanded) {
            throw new RockCantMoveDownException();
        } else {
            // Moves the rock down
            fallingRock.getBottomLeft().move(Direction.BOTTOM, limits);
        }
    }

    public long computeHeight() {
        return this.fallenRocks.stream()
                .mapToLong(rock -> rock.getBottomLeft().getY() + rock.getLimits().getTop() + 1)
                .max().orElse(0);
    }
}
