package tests.a;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Mon super test de la mort qui tue")
public class DiplayNames {

    @Test
    @DisplayName("  Custom test name containing spaces")
    void testWithDisplayNameContainingSpaces() {
    }

    @Test
    @DisplayName("╯°□°）╯")
    void testWithDisplayNameContainingSpecialCharacters() {
    }

    @Test
    @DisplayName("😱")
    void testWithDisplayNameContainingEmoji() {
    }

    @Test
    @DisplayName("Vacaciones 😎")
    void testWithDisplayNameContainingEmojiSmile() {
    }
}