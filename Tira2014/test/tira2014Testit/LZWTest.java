package tira2014Testit;

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
    public void pakkaaJaPuraTest() throws Exception {
        String syote = "kissa kissa kissa kissa kakak kkakakkhkjhjhkjhjkhlhhlhldxtrffuyy  7ty7t67 6ws6"
                + "dytgdfytf u ftudtu  y yt y ";
        kasittelija.tallennaTiedosto("src/lzwkissa.txt", syote);
        int[] pakattu = pakkaaja.lZWPakkaa(syote);
        kasittelija.tallennaTavuittain("src/lzwkissatavu.txt", pakattu);
        LZWPurkaja purkaja = new LZWPurkaja(kasittelija.lueTiedostoTavuittain("src/lzwkissatavu.txt"));
        String purettu = purkaja.lZWPuraKoodi();
        System.out.println(purkaja.getKirjasto().length);
        assertEquals(syote, purettu);
    }

    /**
     * Metodi tastaa isomman syötteen tapauksen.
     */
    @Test
    public void pakkaaJaPuraTiedostoTest() throws Exception {

        String syote = kasittelija.lueTiedosto("src/tiraht.txt");
        int[] pakattu = pakkaaja.lZWPakkaa(syote);
        kasittelija.tallennaTavuittain("src/lzwpakattutiraht.txt", pakattu);
        LZWPurkaja purkaja = new LZWPurkaja(kasittelija.lueTiedostoTavuittain("src/lzwpakattutiraht.txt"));
        String purettu = purkaja.lZWPuraKoodi();
        kasittelija.tallennaTiedosto("src/lzwpakattujapurettutiraht.txt", purettu);
        
        assertArrayEquals(pakkaaja.getKirjasto(), purkaja.getKirjasto());
        assertEquals(syote, purettu);
        
    }

        @Test
    public void pakkaaJaPuraTiedosto2Test() throws Exception {

        String syote = kasittelija.lueTiedosto("src/engkalevala.txt");
        int[] pakattu = pakkaaja.lZWPakkaa(syote);
        kasittelija.tallennaTavuittain("src/lzwpakattuengkalevala.txt", pakattu);
        LZWPurkaja purkaja = new LZWPurkaja(kasittelija.lueTiedostoTavuittain("src/lzwpakattuengkalevala.txt"));
        String purettu = purkaja.lZWPuraKoodi();
        kasittelija.tallennaTiedosto("src/lzwpakattujapurettuengkalevala.txt", purettu);
            System.out.println(purkaja.getKirjasto().length);
            System.out.println(syote.length());
        assertArrayEquals(pakkaaja.getKirjasto(), purkaja.getKirjasto());
        assertEquals(syote, purettu);
        
    }
    
}