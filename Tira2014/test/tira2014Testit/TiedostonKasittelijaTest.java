package tira2014Testit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tira2014.TiedostonKasittelija;

/**
 * Luokka testaa Tiedostonkasittelija-luokan metodeja.
 */
public class TiedostonKasittelijaTest {

    private TiedostonKasittelija kasittelija;
    private String syote;
    private String polku;

    /**
     * Alustetaan attribuutit.
     */
    public TiedostonKasittelijaTest() {
        this.kasittelija = new TiedostonKasittelija();
        this.syote = "testausta varten laitettu tekstinpätkä.";
        this.polku = "src/testiluokanTestisyote.txt";
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
     * Tallennetaan attribuutin 'syote' sisältö tiedostoon polussa 'polku', ja
     * tämän jälkeen luetaan kyseisen tiedoston sisältö ja tarkistetaan, että
     * saadaan alkuperäinen 'syote'.
     */
    @Test
    public void tiedostonTallennusJaSieltaLukuTest() throws Exception {
        kasittelija.tallennaTiedosto(polku, syote);
        String luettu = kasittelija.lueTiedosto(polku);
        assertEquals(syote, luettu);
    }
}