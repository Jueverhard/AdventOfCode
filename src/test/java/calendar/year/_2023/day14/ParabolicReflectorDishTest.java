package calendar.year._2023.day14;

import calendar.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.enums.Part;

import java.io.IOException;
import java.util.stream.Stream;

class ParabolicReflectorDishTest extends BaseTest {

    private final ParabolicReflectorDish exercise = initializeExercise(ParabolicReflectorDish.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 113456),
                Arguments.of(Part.PART_1, true, 136)//,
//                Arguments.of(Part.PART_2, false, 0),
//                Arguments.of(Part.PART_2, true, 0)
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
