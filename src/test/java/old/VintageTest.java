package old;

import netapsys.bzh.Personne;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VintageTest {

    private static Personne personne;

    @Before
    public void setUp() throws Exception {
        personne = Personne.builder().firstName("John").lastName("Doe").build();
    }

    /**
     * Test sur le versement
     */
    @Test
    public void test() throws Exception {
        Assert.assertNotNull(personne);
    }

}