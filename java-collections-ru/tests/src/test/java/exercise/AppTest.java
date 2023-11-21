package exercise;

import static exercise.App.take;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> testList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> testListZero = new ArrayList<>();

        assertThat(new ArrayList<>(Arrays.asList(1, 2, 3))).isEqualTo(take(testList, 3));

        assertThat(testList).isEqualTo(take(testList, 7));

        // END
    }
}
