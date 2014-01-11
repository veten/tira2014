package tira2014Testit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tira2014.Minimikeko;
import tira2014.Solmu;

/**
 * Luokka testaa Minimikeko-luokan metodeja.
 */
public class MinimikekoTest {

    private Minimikeko keko;

    /**
     * Alustetaan minimikeko.
     */
    public MinimikekoTest() {
        keko = new Minimikeko(10);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Metodi täyttää kekoon muutamia solmuja, ja lopuksi tarkistaa, että pienin
     * avain on päällimmäisenä.
     */
    @Test
    public void keonTayttoTest() {
        keko.lisaaKekoon(new Solmu(7, 'x'));
        keko.lisaaKekoon(new Solmu(1, 'r'));
        keko.lisaaKekoon(new Solmu(5, 'k'));
        keko.lisaaKekoon(new Solmu(3, 'a'));
        keko.lisaaKekoon(new Solmu(5, 'w'));
        assertEquals(1, keko.palautaPienin().getAvain());
    }

    /**
     * Metodi tyhjentää keosta alkioita, lisää sinne alkioita, ja lopuksi
     * tyhjentää sen kokonaan tarkistaen, että alkiot poistetaan keosta oikeassa
     * järjestyksessä.
     */
    @Test
    public void keonTyhjennysTest() {
        keonTayttoTest();
        assertEquals(1, keko.palautaPienin().getAvain());
        assertEquals(1, keko.palautaJaPoistaPienin().getAvain());
        assertEquals(3, keko.palautaPienin().getAvain());
        assertEquals(3, keko.palautaJaPoistaPienin().getAvain());

        keko.lisaaKekoon(new Solmu(11, 'g'));
        keko.lisaaKekoon(new Solmu(2, 'p'));

        assertEquals(2, keko.palautaPienin().getAvain());
        assertEquals(2, keko.palautaJaPoistaPienin().getAvain());
        assertEquals(5, keko.palautaPienin().getAvain());
        assertEquals(5, keko.palautaJaPoistaPienin().getAvain());
        assertEquals(5, keko.palautaJaPoistaPienin().getAvain());
        assertEquals(7, keko.palautaJaPoistaPienin().getAvain());
        assertEquals(11, keko.palautaJaPoistaPienin().getAvain());
        assertEquals(null, keko.palautaPienin());
    }
}