package tira2014Testit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tira2014.HuffmanPakkaaja;
import tira2014.HuffmanPurkaja;
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

        String syote = kasittelija.lueTiedosto("src/tito.txt");
        int[] pakattu = pakkaaja.lZWPakkaa(syote);
        kasittelija.tallennaTavuittain("src/lzwpakattuengkalevalatavu.txt", pakattu);
        LZWPurkaja purkaja = new LZWPurkaja(kasittelija.lueTiedostoTavuittain("src/lzwpakattuengkalevalatavu.txt"));
        String purettu = purkaja.lZWPuraKoodi();
        kasittelija.tallennaTiedosto("src/lzwpakattujapurettuengkalevala.txt", purettu);
        for (int i = 0; i < pakkaaja.getKirjasto().length; i++) {
            System.out.print(pakkaaja.getKirjasto()[i] + ",");
        }
        System.out.println("-----");
        for (int i = 0; i < purkaja.getKirjasto().length; i++) {
            System.out.print(purkaja.getKirjasto()[i] + ",");
        }
        assertArrayEquals(pakkaaja.getKirjasto(), purkaja.getKirjasto());
        assertEquals(syote, purettu);
        
    }

    @Test
    public void lzwPlusHuffmanTest() throws Exception {

        String syote = kasittelija.lueTiedosto("src/kalevala.txt");
        int[] pakattu = pakkaaja.lZWPakkaa(syote);
        kasittelija.tallennaTavuittain("src/lzwpakattukalevalatavu2.txt", pakattu);
        HuffmanPakkaaja huffmanpakkaaja = new HuffmanPakkaaja(null);
        int[] huff = huffmanpakkaaja.huffmanPakkaa(kasittelija.lueTiedostoTavuittain("src/lzwpakattukalevalatavu2.txt"));
        kasittelija.tallennaTavuittain("src/huffmanpakattukalevalatavu.txt", huff);
        HuffmanPurkaja huffmanpurkaja = new HuffmanPurkaja(kasittelija.lueTiedostoTavuittain("src/huffmanpakattukalevalatavu.txt"), huffmanpakkaaja.getTree());
        String huffmanpurettu = huffmanpurkaja.huffmanPuraKoodi();
        LZWPurkaja lzwpurkaja = new LZWPurkaja(huffmanpurettu);
        String lzwpurettu = lzwpurkaja.lZWPuraKoodi();
        assertEquals(syote, lzwpurettu);
    }
}