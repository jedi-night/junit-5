package tests.c;

import netapsys.bzh.Personne;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AssertJTest {

    private static Personne frodo;
    private static Personne sam;
    private static Personne sauron;

    private static List<Personne> fellowshipOfTheRing;

    @BeforeAll
    static void setup() {

        // bad guy
        sauron = buildCharacter("Sauron", "The evil", 2000, "Unknown");

        // minipouces
        frodo = buildCharacter("Frodo", "Bessac", 33, "Hobbit");
        sam = buildCharacter("Samsaget", "Gamgie", 38, "Hobbit");
        Personne pippin = buildCharacter("Peregrin", "Touc", 28, "Hobbit");
        Personne merry = buildCharacter("Meriadoc", "Brandibouc", 29, "Hobbit");

        // chimpanzés
        Personne aragorn = buildCharacter("Aragorn", "", 35, "Man");
        Personne boromir = buildCharacter("Boromir", "", 37, "Man");

        // oreilles pointues
        Personne legolas = buildCharacter("Legolas", "", 1000, "Elf");

        // trapeur
        Personne gimli = buildCharacter("Gimli", "", 45, "Dwarf");

        // Albus Dumbledore
        Personne gandalf = buildCharacter("Gandalf", "Le Blanc", 45, "Man");

        fellowshipOfTheRing = Arrays.asList(frodo, sam, pippin, merry, aragorn, boromir, legolas, gimli, gandalf);
    }

    private static Personne buildCharacter(String firstName, String lastName, int age, String race) {
        return Personne.builder().firstName(firstName).lastName(lastName).age(age).race(Personne.Race.builder().name(race).build()).build();
    }

    @Test
    @DisplayName("Bascis")
    void assertJBasic() {
        assertThat(Arrays.asList("1", "2", "3")).containsExactly("1", "2", "3");

        // basic assertions
        assertThat(frodo.getFirstName()).isEqualTo("Frodo");
        assertThat(frodo).isNotEqualTo(sauron);
    }

    @Test
    @DisplayName("Chaining asserts")
    void assertJChaining() {
        // chaining string specific assertions
        assertThat(frodo.getFirstName()).startsWith("Fro")
                .endsWith("do")
                .isEqualToIgnoringCase("frodo");
    }

    @Test
    @DisplayName("Collection asserts")
    void assertJCollection() {
        // collection specific assertions (there are plenty more)
        // in the examples below fellowshipOfTheRing is a List<TolkienCharacter>
        assertThat(fellowshipOfTheRing).hasSize(9)
                .contains(frodo, sam)
                .doesNotContain(sauron);
    }

    @Test
    @DisplayName("Describe asserts")
    void assertJDescribe() {
        // as() is used to describe the test and will be shown before the error message
        assertThat(frodo.getAge()).as("check %s's age", frodo.getFirstName()).isEqualTo(33);
    }

    @Test
    @DisplayName("Throable asserts")
    void assertJThrowable() {

        // Java 8 exception assertion, standard style ...

        assertThatThrownBy(() -> {
            throw new Exception("boom!");
        }).hasMessage("boom!");

        // ... or BDD style

        Throwable thrown = catchThrowable(() -> {
            throw new Exception("boom!");
        });
        assertThat(thrown).hasMessageContaining("boom");
    }

    @Test
    @DisplayName("Extract asserts")
    void assertJExtract() {
        // using the 'extracting' feature to check fellowshipOfTheRing character's names (Java 7)

        assertThat(fellowshipOfTheRing)
                .extracting("firstName")
                .contains("Boromir", "Gandalf", "Frodo", "Legolas");

        // same thing using a Java 8 method reference

        assertThat(fellowshipOfTheRing)
                .extracting(Personne::getFirstName)
                .doesNotContain("Sauron", "Elrond");

        // extracting multiple values at once grouped in tuples (Java 7)

        assertThat(fellowshipOfTheRing)
                .extracting("firstName", "age", "race.name")
                .contains(
                        tuple("Boromir", 37, "Man"),
                        tuple("Samsaget", 38, "Hobbit"),
                        tuple("Legolas", 1000, "Elf")
                );
    }
}