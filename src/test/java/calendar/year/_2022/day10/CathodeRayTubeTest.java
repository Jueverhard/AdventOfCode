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
                Arguments.of(Part.PART_1, false, 14720),
                Arguments.of(Part.PART_1, true, 13140),
                Arguments.of(
                        Part.PART_2,
                        false,
                        "####.####.###..###..###..####.####.####.\n" +
                                "#.......#.#..#.#..#.#..#.#.......#.#....\n" +
                                "###....#..###..#..#.###..###....#..###..\n" +
                                "#.....#...#..#.###..#..#.#.....#...#....\n" +
                                "#....#....#..#.#....#..#.#....#....#....\n" +
                                "#....####.###..#....###..#....####.#....\n"
                ),
                Arguments.of(
                        Part.PART_2,
                        true,
                        "##..##..##..##..##..##..##..##..##..##..\n" +
                                "###...###...###...###...###...###...###.\n" +
                                "####....####....####....####....####....\n" +
                                "#####.....#####.....#####.....#####.....\n" +
                                "######......######......######......####\n" +
                                "#######.......#######.......#######.....\n"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void run_test(Part part, boolean testMode, Object expectedResult) throws IOException {
        // ARRANGE
        // ACT
        String res = exercise.run(part, testMode);

        // ASSERT
        assertEquals(expectedResult, res);
    }
}
