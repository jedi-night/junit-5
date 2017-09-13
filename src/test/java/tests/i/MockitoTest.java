package tests.i;

import commons.MockitoExtension;
import netapsys.bzh.Personne;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MockitoTest {

    @Mock Personne personGlobal;

    @BeforeEach
    void init() {
        when(personGlobal.getFirstName()).thenReturn("Legolas");
    }

    @Test
    void simpleTestWithGlobalMock() {
        assertEquals("Legolas", personGlobal.getFirstName());
    }

    @Test
    void simpleTestWithInjectedMock(@Mock Personne personne) {
        when(personne.getFirstName()).thenReturn("Gimli");

        assertEquals("Gimli", personne.getFirstName());
    }
}