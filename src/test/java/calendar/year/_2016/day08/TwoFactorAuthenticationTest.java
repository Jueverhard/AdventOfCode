package calendar.year._2016.day08;

import calendar.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.enums.Part;

import java.io.IOException;
import java.util.stream.Stream;

class TwoFactorAuthenticationTest extends BaseTest {
    private final TwoFactorAuthentication exercise = initializeExercise(TwoFactorAuthentication.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 106),
                Arguments.of(Part.PART_1, true, 6),
                Arguments.of(Part.PART_2, false, """
                        .##..####.#....####.#.....##..#...#####..##...###
                        #..#.#....#....#....#....#..#.#...##....#..#.#...
                        #....###..#....###..#....#..#..#.#.###..#....#...
                        #....#....#....#....#....#..#...#..#....#.....##.
                        #..#.#....#....#....#....#..#...#..#....#..#....#
                        .##..#....####.####.####..##....#..#.....##..###.
                        """
                ),
                Arguments.of(Part.PART_2, true, """
                        ....#.#
                        #.#....
                        .#.....
                        .#.....
                        """
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
