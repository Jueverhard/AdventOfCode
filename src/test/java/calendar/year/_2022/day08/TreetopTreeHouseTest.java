package calendar.year._2022.day08;

import calendar.BaseTest;
import calendar.year.enums.Part;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

class TreetopTreeHouseTest extends BaseTest {

    private final TreetopTreeHouse exercise = initializeExercise(TreetopTreeHouse.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false),
                Arguments.of(Part.PART_1, true),
                Arguments.of(Part.PART_2, false)
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void run_test(Part part, boolean testMode) throws IOException {
        // ARRANGE
        // ACT
        String res = exercise.run(part, testMode);

        // ASSERT
        int expectedResult;
        if (Part.PART_1.equals(part)) {
            expectedResult = testMode ? 21 : 1669;
        } else {
            expectedResult = testMode ? 8 : 331344;
        }
        assertEquals(expectedResult, res);
    }

}
