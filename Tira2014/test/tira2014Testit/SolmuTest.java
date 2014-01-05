package tira2014Testit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tira2014.Solmu;

/**
 * Luokka testaa Solmu-luokan metodeja.
 */
public class SolmuTest {

    private Solmu solmu;

    public SolmuTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Alustetaan attribuutti.
     */
    @Before
    public void setUp() {
        solmu = new Solmu(12, 'A');
    }

    @After
    public void tearDown() {
    }

    /**
     * Metodi tarkistaa, että solmulle on luonnin yhteydessä lisätty oikea
     * ascii-koodi.
     */
    @Test
    public void asciiCodeLisattyTest() {
        assertEquals(65, solmu.getKirjaimenASciiKoodi());
    }

    /**
     * Metodi tarkistaa solmun tietojen oikeellisuuden vertaamalla sitä
     * toString-metodin arvoon.
     */
    @Test
    public void solmuStringiksiTest() {
        assertEquals("Solmu[avain: 12, arvo: A, ascii: 65]", solmu.toString());
    }
}