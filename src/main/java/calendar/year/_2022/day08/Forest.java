package calendar.year._2022.day08;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Forest {

    private List<Tree> trees = new ArrayList<>();

    public void addTree(Tree tree) {
        trees.add(tree);
    }

    public boolean isTreeSeeable(Tree tree) {
        return isSeeableFromLeft(tree) ||
                isSeeableFromRight(tree) ||
                isSeeableFromTop(tree) ||
                isSeeableFromBottom(tree);
    }

    private boolean isSeeableFromLeft(Tree tree) {
        return trees.stream()
                // Keeps only trees that are on the same Y axis
                .filter(forestTree -> forestTree.getPositionY() == tree.getPositionY())
                // Keeps only trees that are on the left
                .filter(forestTree -> forestTree.getPositionX() < tree.getPositionX())
                // Checks whether there is a tree or equal or greater size
                .noneMatch(forestTree -> forestTree.getSize() >= tree.getSize());
    }

    private boolean isSeeableFromRight(Tree tree) {
        return trees.stream()
                // Keeps only trees that are on the same Y axis
                .filter(forestTree -> forestTree.getPositionY() == tree.getPositionY())
                // Keeps only trees that are on the right
                .filter(forestTree -> forestTree.getPositionX() > tree.getPositionX())
                // Checks whether there is a tree or equal or greater size
                .noneMatch(forestTree -> forestTree.getSize() >= tree.getSize());
    }

    private boolean isSeeableFromTop(Tree tree) {
        return trees.stream()
                // Keeps only trees that are on the same X axis
                .filter(forestTree -> forestTree.getPositionX() == tree.getPositionX())
                // Keeps only trees that are above
                .filter(forestTree -> forestTree.getPositionY() < tree.getPositionY())
                // Checks whether there is a tree or equal or greater size
                .noneMatch(forestTree -> forestTree.getSize() >= tree.getSize());
    }

    private boolean isSeeableFromBottom(Tree tree) {
        return trees.stream()
                // Keeps only trees that are on the same X axis
                .filter(forestTree -> forestTree.getPositionX() == tree.getPositionX())
                // Keeps only trees that are below
                .filter(forestTree -> forestTree.getPositionY() > tree.getPositionY())
                // Checks whether there is a tree or equal or greater size
                .noneMatch(forestTree -> forestTree.getSize() >= tree.getSize());
    }

    public long computeScenicScore(Tree tree) {
        return computeScenicScoreFromLeft(tree) *
                computeScenicScoreFromRight(tree) *
                computeScenicScoreFromTop(tree) *
                computeScenicScoreFromBottom(tree);
    }

    private long computeScenicScoreFromLeft(Tree tree) {
        List<Tree> blockingTrees = trees.stream()
                // Keeps only trees that are on the same Y axis
                .filter(forestTree -> forestTree.getPositionY() == tree.getPositionY())
                // Keeps only trees that are on the left
                .filter(forestTree -> forestTree.getPositionX() < tree.getPositionX())
                // Keeps only trees that are taller
                .filter(forestTree -> forestTree.getSize() >= tree.getSize())
                .collect(Collectors.toList());

        if (blockingTrees.isEmpty()) {
            return tree.getPositionX();
        } else {
            return blockingTrees.stream()
                    // Computes the distance beetween the current tree and its obstacle
                    .mapToInt(forestTree -> forestTree.computeDistance(tree))
                    // Keep only the shortest distance
                    .min().orElseThrow();
        }
    }

    private long computeScenicScoreFromRight(Tree tree) {
        long forestWidth = (long) Math.sqrt(trees.size());

        List<Tree> blockingTrees = trees.stream()
                // Keeps only trees that are on the same Y axis
                .filter(forestTree -> forestTree.getPositionY() == tree.getPositionY())
                // Keeps only trees that are on the right
                .filter(forestTree -> forestTree.getPositionX() > tree.getPositionX())
                // Keeps only trees that are taller
                .filter(forestTree -> forestTree.getSize() >= tree.getSize())
                .collect(Collectors.toList());

        if (blockingTrees.isEmpty()) {
            return forestWidth - tree.getPositionX() - 1;
        } else {
            return blockingTrees.stream()
                    // Computes the distance beetween the current tree and its obstacle
                    .mapToInt(forestTree -> forestTree.computeDistance(tree))
                    // Keep only the shortest distance
                    .min().orElseThrow();
        }
    }

    private long computeScenicScoreFromTop(Tree tree) {
        List<Tree> blockingTrees = trees.stream()
                // Keeps only trees that are on the same X axis
                .filter(forestTree -> forestTree.getPositionX() == tree.getPositionX())
                // Keeps only trees that are above
                .filter(forestTree -> forestTree.getPositionY() < tree.getPositionY())
                // Keeps only trees that are taller
                .filter(forestTree -> forestTree.getSize() >= tree.getSize())
                .collect(Collectors.toList());

        if (blockingTrees.isEmpty()) {
            return tree.getPositionY();
        } else {
            return blockingTrees.stream()
                    // Computes the distance beetween the current tree and its obstacle
                    .mapToInt(forestTree -> forestTree.computeDistance(tree))
                    // Keep only the shortest distance
                    .min().orElseThrow();
        }
    }

    private long computeScenicScoreFromBottom(Tree tree) {
        long forestWidth = (long) Math.sqrt(trees.size());

        List<Tree> blockingTrees = trees.stream()
                // Keeps only trees that are on the same X axis
                .filter(forestTree -> forestTree.getPositionX() == tree.getPositionX())
                // Keeps only trees that are below
                .filter(forestTree -> forestTree.getPositionY() > tree.getPositionY())
                // Keeps only trees that are taller
                .filter(forestTree -> forestTree.getSize() >= tree.getSize())
                .collect(Collectors.toList());

        if (blockingTrees.isEmpty()) {
            return forestWidth - tree.getPositionY() - 1;
        } else {
            return blockingTrees.stream()
                    // Computes the distance beetween the current tree and its obstacle
                    .mapToInt(forestTree -> forestTree.computeDistance(tree))
                    // Keep only the shortest distance
                    .min().orElseThrow();
        }
    }
}
