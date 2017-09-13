package tests.e;

import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DisabledMethodTest {

    @Disabled
    @Test
    void testWillBeSkipped() {
    }

    @Ignore
    @Test
    void testWillBeExecuted() {
    }
}
