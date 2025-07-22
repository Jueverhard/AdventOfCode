package calendar.year._2024.day09;

import utils.Exercise;
import utils.enums.Part;
import utils.fileparser.Parser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class DiskFragmenter extends Exercise {

    public DiskFragmenter(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        // Data initialization
        List<Integer> inputDigits = Arrays.stream(Parser.parseLines(this.getInputPath(testMode)).get(0).split(""))
                .map(Integer::parseInt)
                .toList();

        // Extracts the blocks
        List<ArrayDeque<Integer>> blocks = IntStream.range(0, inputDigits.size())
                .filter(i -> 0 == i % 2)
                .mapToObj(i -> new ArrayDeque<>(IntStream.range(0, inputDigits.get(i))
                        .mapToObj(j -> i / 2)
                        .toList())
                )
                .toList();

        // Extracts the free spaces
        List<ArrayDeque<Integer>> freeSpaces = IntStream.range(0, inputDigits.size())
                .filter(i -> 1 == i % 2)
                .mapToObj(i -> new ArrayDeque<>(IntStream.range(0, inputDigits.get(i))
                        .mapToObj(j -> 0)
                        .toList()))
                .toList();

        // Computes the compacted file blocks
        List<Integer> compactedBlocks = compactBlocks(blocks, freeSpaces.iterator());

        long result = IntStream.range(0, compactedBlocks.size())
                .mapToLong(i -> (long) i * compactedBlocks.get(i))
                .reduce(Long::sum)
                .orElseThrow();

        return print(result);
    }

    /**
     * Computes the compacted blocks by filling up free spaces with trailing blocks.
     *
     * @param blocks             The list of blocks to be compacted.
     * @param freeSpacesIterator An iterator over the free spaces.
     * @return The resulting compacted blocks.
     */
    private List<Integer> compactBlocks(List<ArrayDeque<Integer>> blocks, Iterator<ArrayDeque<Integer>> freeSpacesIterator) {
        List<Integer> compactedBlocks = new ArrayList<>();
        boolean isReadingBlocks = true;
        ArrayDeque<ArrayDeque<Integer>> blocksDeque = new ArrayDeque<>(blocks);

        while (freeSpacesIterator.hasNext() && !blocksDeque.isEmpty()) {
            if (isReadingBlocks) {
                // Adds the whole first block pack to the compacted blocks
                compactedBlocks.addAll(blocksDeque.removeFirst());
            } else {
                // Adds the last block unit to the compacted blocks
                ArrayDeque<Integer> currentFreeSpaces = freeSpacesIterator.next();
                while (null != currentFreeSpaces.pollLast()) {
                    ArrayDeque<Integer> lastBlocks = blocksDeque.getLast();
                    compactedBlocks.add(lastBlocks.removeLast());

                    // If the last block pack is exhausted, remove it
                    if (lastBlocks.isEmpty()) {
                        blocksDeque.removeLast();
                    }
                }
            }
            isReadingBlocks = !isReadingBlocks;
        }

        return compactedBlocks;
    }
}
