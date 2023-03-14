package calendar.year._2022.day10;

import calendar.BaseTest;
import utils.enums.Part;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

class CathodeRayTubeTest extends BaseTest {

    private final CathodeRayTube exercise = initializeExercise(CathodeRayTube.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false),
                Arguments.of(Part.PART_1, true),
                Arguments.of(Part.PART_2, false),
                Arguments.of(Part.PART_2, true)
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void run_test(Part part, boolean testMode) throws IOException {
        // ARRANGE
        // ACT
        String res = exercise.run(part, testMode);

        // ASSERT
        if (Part.PART_1.equals(part)) {
            int expectedResult = testMode ? 13140 : 14720;
            assertEquals(expectedResult, res);
        } else {
            String expectedResult = testMode ?
                    "##..##..##..##..##..##..##..##..##..##..\n" +
                    "###...###...###...###...###...###...###.\n" +
                    "####....####....####....####....####....\n" +
                    "#####.....#####.....#####.....#####.....\n" +
                    "######......######......######......####\n" +
                    "#######.......#######.......#######.....\n"
                    :
                    "####.####.###..###..###..####.####.####.\n" +
                    "#.......#.#..#.#..#.#..#.#.......#.#....\n" +
                    "###....#..###..#..#.###..###....#..###..\n" +
                    "#.....#...#..#.###..#..#.#.....#...#....\n" +
                    "#....#....#..#.#....#..#.#....#....#....\n" +
                    "#....####.###..#....###..#....####.#....\n";
            assertEquals(expectedResult, res);
        }
    }

}
