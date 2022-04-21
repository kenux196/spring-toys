import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SampleTest {

    @Test
    @DisplayName("AssertJ 테스트")
    void sampleTest() {

        String givenName = "A";

        givenName = "B";

        assertThat(givenName).isEqualTo("B");
    }
}