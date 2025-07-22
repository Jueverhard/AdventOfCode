package calendar.year._2024.day09;

import calendar.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.enums.Part;

import java.io.IOException;
import java.util.stream.Stream;

class DiskFragmenterTest extends BaseTest {

    private final DiskFragmenter exercise = initializeExercise(DiskFragmenter.class);

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(Part.PART_1, false, 6401092019345L),
                Arguments.of(Part.PART_1, true, 1928)//,
//                Arguments.of(Part.PART_2, false, 1072),
//                Arguments.of(Part.PART_2, true, 2)
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
