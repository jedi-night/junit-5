package tests.e;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DisabledMethodTest {

    @Disabled
    @Test
    void testWillBeSkipped() {
    }

    @Test
    void testWillBeExecuted() {
    }
}
