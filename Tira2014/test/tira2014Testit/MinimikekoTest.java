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
        keko.HeapInsert(new Solmu(7, 'x'));
        keko.HeapInsert(new Solmu(1, 'r'));
        keko.HeapInsert(new Solmu(5, 'k'));
        keko.HeapInsert(new Solmu(3, 'a'));
        keko.HeapInsert(new Solmu(5, 'w'));
        assertEquals(1, keko.HeapMin().getAvain());
    }

    /**
     * Metodi tyhjentää keosta alkioita, lisää sinne alkioita, ja lopuksi
     * tyhjentää sen kokonaan tarkistaen, että alkiot poistetaan keosta oikeassa
     * järjestyksessä.
     */
    @Test
    public void keonTyhjennysTest() {
        keonTayttoTest();
        assertEquals(1, keko.HeapMin().getAvain());
        assertEquals(1, keko.HeapDelMin().getAvain());
        assertEquals(3, keko.HeapMin().getAvain());
        assertEquals(3, keko.HeapDelMin().getAvain());

        keko.HeapInsert(new Solmu(11, 'g'));
        keko.HeapInsert(new Solmu(2, 'p'));

        assertEquals(2, keko.HeapMin().getAvain());
        assertEquals(2, keko.HeapDelMin().getAvain());
        assertEquals(5, keko.HeapMin().getAvain());
        assertEquals(5, keko.HeapDelMin().getAvain());
        assertEquals(5, keko.HeapDelMin().getAvain());
        assertEquals(7, keko.HeapDelMin().getAvain());
        assertEquals(11, keko.HeapDelMin().getAvain());
        assertEquals(null, keko.HeapMin());
    }
}