package tests.j;

import commons.TestInterfaceDynamicTestsDemo;
import commons.TestLifecycleLogger;
import commons.TimeExecutionLogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterfaceTest implements TestLifecycleLogger, TimeExecutionLogger, TestInterfaceDynamicTestsDemo {

    @Test
    void isEqualValue() {
        assertEquals(1, 1, "is always equal");
    }
}