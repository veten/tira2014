package tira2014Testit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tira2014.Hajautustaulu;
import tira2014.Solmu;

/**
 * Luokka testaa Hajautustaulu-luokan metodeja.
 */
public class HajautustauluTest {

    private Hajautustaulu taulu;

    /**
     * Alustetaan attribuutti.
     */
    public HajautustauluTest() {
        taulu = new Hajautustaulu(17);
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
     * Metodi tarkistaa, että alkioiden lisäys ja poisto taulukosta onnistuu.
     */
    @Test
    public void alkionPoistoTest() {
        Solmu solmu = new Solmu(99, 'a');
        taulu.lisays(solmu);
        Solmu solmu2 = new Solmu(199, 'i');
        taulu.lisays(solmu2);
        taulu.poisto(solmu);
        assertNotSame(solmu, taulu.etsinta(99));
    }

    /**
     * Metodi tarkistaa, että alkioiden lisäys ja haku taulukosta onnistuu.
     */
    @Test
    public void alkioidenHakuTest() {
        Solmu solmu = new Solmu(99, 'a');
        taulu.lisays(solmu);
        Solmu solmu2 = new Solmu(199, 'i');
        taulu.lisays(solmu2);
        assertEquals(solmu, taulu.etsinta(99));
        assertEquals(solmu2, taulu.etsinta(199));
    }
}