package tests.l;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.provider.*;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;
import static org.junit.jupiter.params.provider.EnumSource.Mode.MATCH_ALL;

public class ParametrizeTest {

    @ParameterizedTest
    @ValueSource(strings = { "Hello", "World" })
    void testWithStringParameter(String argument) {
        assertNotNull(argument);
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    void testWithValueSource(int argument) {
        assertNotNull(argument);
    }

    @ParameterizedTest
    @EnumSource(TimeUnit.class)
    void testWithEnumSource(TimeUnit timeUnit) {
        assertNotNull(timeUnit);
    }

    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, names = { "DAYS", "HOURS" })
    void testWithEnumSourceInclude(TimeUnit timeUnit) {
        assertTrue(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
    }

    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, mode = EXCLUDE, names = { "DAYS", "HOURS" })
    void testWithEnumSourceExclude(TimeUnit timeUnit) {
        assertFalse(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
        assertTrue(timeUnit.name().length() > 5);
    }

    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, mode = MATCH_ALL, names = "^(M|N).+SECONDS$")
    void testWithEnumSourceRegex(TimeUnit timeUnit) {
        String name = timeUnit.name();
        assertTrue(name.startsWith("M") || name.startsWith("N"));
        assertTrue(name.endsWith("SECONDS"));
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithSimpleMethodSource(String argument) {
        assertNotNull(argument);
    }

    static Stream<String> stringProvider() {
        return Stream.of("foo", "bar");
    }

    @ParameterizedTest
    @MethodSource("stringAndIntProvider")
    void testWithMultiArgMethodSource(String first, int second) {
        assertNotNull(first);
        assertNotEquals(0, second);
    }

    static Stream<Arguments> stringAndIntProvider() {
        return Stream.of(Arguments.of("foo", 1), Arguments.of("bar", 2));
    }

    @ParameterizedTest
    @CsvSource({ "foo, 1", "bar, 2", "'baz, qux', 3" })
    void testWithCsvSource(String first, int second) {
        assertNotNull(first);
        assertNotEquals(0, second);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/two-column.csv")
    void testWithCsvFileSource(String first, int second) {
        assertNotNull(first);
        assertNotEquals(0, second);
    }

    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    void testWithArgumentsSource(String argument) {
        assertNotNull(argument);
    }

    static class MyArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of("foo", "bar").map(Arguments::of);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = { "01.01.2017", "31.12.2017" })
    void testWithExplicitJavaTimeConverter(@JavaTimeConversionPattern("dd.MM.yyyy") LocalDate argument) {
        assertEquals(2017, argument.getYear());
    }

    @DisplayName("Display name of container")
    @ParameterizedTest(name = "{index} ==> first=''{0}'', second={1}")
    @CsvSource({ "foo, 1", "bar, 2", "'baz, qux', 3" })
    void testWithCustomDisplayNames(String first, int second) {
    }

    @ParameterizedTest
    @ValueSource(strings = "foo")
    void testWithRegularParameterResolver(String argument, TestReporter testReporter) {
        testReporter.publishEntry("argument", argument);
    }
}
