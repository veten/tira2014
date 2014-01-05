package tira2014Testit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tira2014.Ascii;

/**
 * Luokka testaa Ascii-luokan metodeja.
 */
public class AsciiTest {

    private Ascii ascii;

    /**
     * Alustetaan attribuutti.
     */
    public AsciiTest() {
        ascii = new Ascii();
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
     * Metodi tarkistaa muutaman yksittäisen merkin ascii-koodin oikeellisuuden.
     */
    @Test
    public void kirjainAsciiksiTest() {
        assertEquals(32, ascii.merkkiAsciiKoodiksi(' '));
        assertEquals(58, ascii.merkkiAsciiKoodiksi(':'));
        assertEquals(94, ascii.merkkiAsciiKoodiksi('^'));
        assertEquals(94, ascii.merkkiAsciiKoodiksi('^'));
        assertEquals(30, ascii.merkkiAsciiKoodiksi('ä'));
        assertEquals(26, ascii.merkkiAsciiKoodiksi('Å'));
    }

    /**
     * Metodi laskee esimerkkisanan ascii-koodin yhdisteenä yksittäisten
     * merkkien koodeista ja vertaa sitä metodilla laskettuun arvoon.
     */
    @Test
    public void sanaAsciiksiTest() {
        long luku = ascii.merkkiAsciiKoodiksi('K') + 128 * ascii.merkkiAsciiKoodiksi('i') + 128 * 128
                * ascii.merkkiAsciiKoodiksi('s') + 128 * 128 * 128 * ascii.merkkiAsciiKoodiksi('s');
        assertEquals(luku, ascii.merkkijonoAsciiKoodiksi("Kiss"));
    }
}