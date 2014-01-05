package tira2014Testit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tira2014.Solmu;
import tira2014.Puu;

/**
 * Luokka testaa Puu-luokan metodeja.
 */
public class PuuTest {

    private Puu puu;

    public PuuTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Alustetaan Puu-luokan ilmentymä.
     */
    @Before
    public void setUp() {
        puu = new Puu(new Solmu(2, 'w'));

    }

    @After
    public void tearDown() {
    }

    /**
     * Metodi tarkistaa, että juuren vaihto onnistuu.
     */
    @Test
    public void juurenVaihtoTest() {
        Solmu uusiJuuri = new Solmu(33, '*');
        puu.setJuuri(uusiJuuri);
        assertEquals(uusiJuuri, puu.getJuuri());
        assertEquals(33, puu.getJuuri().getAvain());
    }
}