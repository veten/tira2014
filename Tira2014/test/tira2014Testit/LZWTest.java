package tira2014Testit;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tira2014.LZWPakkaaja;
import tira2014.LZWPurkaja;
import tira2014.TiedostonKasittelija;

/**
 * Luokka testaa sekä LZWPurkaja-luokan että LZWPakkaaja-luokan metodeja.
 */
public class LZWTest {

    private LZWPakkaaja pakkaaja;
    private TiedostonKasittelija kasittelija;

    /**
     * Alustetaan attribuutit.
     */
    public LZWTest() {
        pakkaaja = new LZWPakkaaja();
        kasittelija = new TiedostonKasittelija();
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
     * Metodi pakkaa pakkaajalla tekstin, ja tämän jälkeen purkajalla purkaa
     * tekstin alkuperäiseksi.
     */
    @Test
    public void pakkaaJaPuraTest() {
        ArrayList<Integer> pakattu = pakkaaja.lZWPakkaa("Kissalan pojat!");
        LZWPurkaja purkaja = new LZWPurkaja(pakattu);
        String purettu = purkaja.lZWPuraKoodi();
        assertEquals("Kissalan pojat!", purettu);
    }

    /**
     * Metodi tarkistaa, että pakkaajan ja purkajan muodostamat kirjastot ovat
     * identtiset. Pakkaaja muodostaa oman kirjastonsa pakkauksen yhteydessä ja
     * purkaja puolestaan omansa purkamisen yhteydessä.
     */
    @Test
    public void vertaaKirjastojaTest() {
        ArrayList<Integer> pakattu = pakkaaja.lZWPakkaa("Tässä jotain tekstiä ihan vaan testimielessä..");
        LZWPurkaja purkaja = new LZWPurkaja(pakattu);
        String purettu = purkaja.lZWPuraKoodi();
        assertEquals(pakkaaja.getArrayKirjasto(), purkaja.getArrayKirjasto());
    }

    /**
     * Metodi tastaa isomman syötteen tapauksen.
     */
    @Test
    public void pakkaaJaPuraTiedostoTest() {
        String syote = kasittelija.lueTiedosto("src/kalevala.txt");
        ArrayList<Integer> pakattu = pakkaaja.lZWPakkaa(syote);
        LZWPurkaja purkaja = new LZWPurkaja(pakattu);
        String purettu = purkaja.lZWPuraKoodi();
        assertEquals(syote, purettu);
        System.out.println(pakattu.size()); // => 2623
        System.out.println(syote.length()); // => 7006
    }
}