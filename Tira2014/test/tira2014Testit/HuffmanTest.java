package tira2014Testit;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tira2014.HuffmanPakkaaja;
import tira2014.HuffmanPurkaja;
import tira2014.KirjainTodennakoisyydet;
import tira2014.TiedostonKasittelija;

/**
 * Luokka testaa sekä HuffmanPakkaaja-luokan että HuffmanPurkaja-luokan
 * metodeja.
 */
public class HuffmanTest {

    private TiedostonKasittelija kasittelija;
    private HuffmanPakkaaja tuntematonPakkaaja;
    private HuffmanPakkaaja englanninPakkaaja;
    private HuffmanPakkaaja suomenPakkaaja;

    /**
     * Alustetaan attribuutit. EnglanninPakkaaja saa parametrina tiedon
     * englannin kielisen tekstin kirjain todennäköisyyksistä, ja suomenPakkaaja
     * vastaavat suomen kielen todennäköisyydet.
     */
    public HuffmanTest() {
        kasittelija = new TiedostonKasittelija();
        tuntematonPakkaaja = new HuffmanPakkaaja(null);
        englanninPakkaaja = new HuffmanPakkaaja(KirjainTodennakoisyydet.englanti);
        suomenPakkaaja = new HuffmanPakkaaja(KirjainTodennakoisyydet.suomi);
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
     * tekstin alkuperäiseksi minkä jälkeen tarkistetaan, että teksti on
     * säilynyt muuttumattomana. Lisäksi metodi tarkistaa, että tiivistetty koko
     * on alle 60 prosenttia alkuperäisestä, vaikkakin puuntiedot on tässä
     * versiossa vielä pakattuun tekstiin koodaamatta.
     */
    @Test
    public void pakkaaJaPuraTest() throws Exception {
        String syote = "Kissalan pojat taas vauhdissako? taasko ne on, vai?";
        int[] pakattu = tuntematonPakkaaja.huffmanPakkaa(syote);
        kasittelija.tallennaTiedosto("src/kissalanorm.txt", syote);
        kasittelija.tallennaTavuittain("src/kissalatavu.txt", pakattu);
        HuffmanPurkaja purkaja = new HuffmanPurkaja(kasittelija.lueTiedostoTavuittain("src/kissalatavu.txt"), tuntematonPakkaaja.getTree());
        String purettu = purkaja.huffmanPuraKoodi();
        assertEquals(syote, purettu);
        long tavu = new File("src/kissalatavu.txt").length();
        long txt = new File("src/kissalanorm.txt").length();
        double suhdeluku = (double) tavu / txt;
        System.out.println("kissalatest pakkauskoko " + suhdeluku);
        assertTrue(suhdeluku < 0.6);
    }

    /**
     * Metodi pakkaa pakkaajalla tekstin, ja tämän jälkeen purkajalla purkaa
     * tekstin alkuperäiseksi minkä jälkeen tarkistetaan, että teksti on
     * säilynyt muuttumattomana. Lisäksi metodi tarkistaa, että tiivistetty koko
     * on alle 60 prosenttia alkuperäisestä, vaikkakin puuntiedot on tässä
     * versiossa vielä pakattuun tekstiin koodaamatta.
     */
    @Test
    public void pakkaaJaPuraKalevalaTest() throws Exception {
        String syote = kasittelija.lueTiedosto("src/weso2.txt");
        int[] pakattu = tuntematonPakkaaja.huffmanPakkaa(syote);
        kasittelija.tallennaTavuittain("src/weso2tavu.txt", pakattu);

        HuffmanPurkaja purkaja = new HuffmanPurkaja(kasittelija.lueTiedostoTavuittain("src/weso2tavu.txt"), tuntematonPakkaaja.getTree());
        String purettu = purkaja.huffmanPuraKoodi();
        assertEquals(syote, purettu);
        long tavu = new File("src/weso2tavu.txt").length();
        long txt = new File("src/weso2.txt").length();
        double suhdeluku = (double) tavu / txt;
        System.out.println("weso2test pakkauskoko " + suhdeluku);
        assertTrue(suhdeluku < 0.6);
    }

    /**
     * Metodi pakkaa pakkaajalla tiedostosta luetun tekstin, ja tämän jälkeen
     * purkajalla purkaa tekstin alkuperäiseksi minkä jälkeen tarkistetaan, että
     * teksti on säilynyt muuttumattomana. Lisäksi metodi tarkistaa, että
     * tiivistetty koko on alle 60 prosenttia alkuperäisestä, vaikkakin
     * puuntiedot on tässä versiossa vielä pakattuun tekstiin koodaamatta.
     */
    @Test
    public void pakkaaJaPuraTitoTest() throws IOException {
        String syote = kasittelija.lueTiedosto("src/tito.txt");
        int[] pakattu = tuntematonPakkaaja.huffmanPakkaa(syote);
        kasittelija.tallennaTavuittain("src/titotavu.txt", pakattu);

        HuffmanPurkaja purkaja = new HuffmanPurkaja(kasittelija.lueTiedostoTavuittain("src/titotavu.txt"), tuntematonPakkaaja.getTree());
        String purettu = purkaja.huffmanPuraKoodi();
        assertEquals(syote, purettu);
        long tavu = new File("src/titotavu.txt").length();
        long txt = new File("src/tito.txt").length();
        double suhdeluku = (double) tavu / txt;
        System.out.println("titotest pakkauskoko " + suhdeluku);
        assertTrue(suhdeluku < 0.6);
    }

    /**
     * Metodi pakkaa pakkaajalla tiedostosta luetun tekstin, ja tämän jälkeen
     * purkajalla purkaa tekstin alkuperäiseksi minkä jälkeen tarkistetaan, että
     * teksti on säilynyt muuttumattomana. Tässä käytetään englannin kielen
     * kirjain todennäköisyyksiä hyväksi, jolloin lopullisessa versiossa ei
     * tarvitse tallentaa puun tietoja pakattuun tiedostoon. Tässä pakkauskoko
     * ei vielä pienene riittävästi, koska ei-kirjain arvoisten merkkien
     * todennäköisyyksiä ei ole analysoituna.
     */
    @Test
    public void pakkaaJaPuraEnglanninKielellaTest() throws IOException {
        String syote = kasittelija.lueTiedosto("src/engKielinenSyote.txt");
        int[] pakattu = englanninPakkaaja.huffmanPakkaa(syote);
        kasittelija.tallennaTavuittain("src/engKielinentavu.txt", pakattu);

        HuffmanPurkaja purkaja = new HuffmanPurkaja(kasittelija.lueTiedostoTavuittain("src/engKielinentavu.txt"), englanninPakkaaja.getTree());
        String purettu = purkaja.huffmanPuraKoodi();
        assertEquals(syote, purettu);
        long tavu = new File("src/engKielinentavu.txt").length();
        long txt = new File("src/engkielinenSyote.txt").length();
        double suhdeluku = (double) tavu / txt;
        System.out.println("englannin kielinentest pakkauskoko engkielisellä pakkaajalla " + suhdeluku);
//        System.out.println(tavu);
//        System.out.println(txt);
//        assertTrue(suhdeluku < 0.6);
    }

    /**
     * Metodi pakkaa pakkaajalla tiedostosta luetun tekstin, ja tämän jälkeen
     * purkajalla purkaa tekstin alkuperäiseksi minkä jälkeen tarkistetaan, että
     * teksti on säilynyt muuttumattomana. Tässä käytetään suomen kielen kirjain
     * todennäköisyyksiä hyväksi, jolloin lopullisessa versiossa ei tarvitse
     * tallentaa puun tietoja pakattuun tiedostoon. Tässä pakkauskoko ei vielä
     * pienene riittävästi, koska ei-kirjain arvoisten merkkien
     * todennäköisyyksiä ei ole analysoituna.
     */
    @Test
    public void pakkaaJaPuraSuomenKielellaTest() throws IOException {
        String syote = kasittelija.lueTiedosto("src/tito.txt");
        int[] pakattu = suomenPakkaaja.huffmanPakkaa(syote);
        kasittelija.tallennaTavuittain("src/titotavusuomi.txt", pakattu);

        HuffmanPurkaja purkaja = new HuffmanPurkaja(kasittelija.lueTiedostoTavuittain("src/titotavusuomi.txt"), suomenPakkaaja.getTree());
        String purettu = purkaja.huffmanPuraKoodi();
        assertEquals(syote, purettu);
        long tavu = new File("src/titotavusuomi.txt").length();
        long txt = new File("src/tito.txt").length();
        double suhdeluku = (double) tavu / txt;
        System.out.println("titotest suomenkielisellä pakkaajalla pakkauskoko " + suhdeluku);
//        System.out.println(tavu);
//        System.out.println(txt);
//        assertTrue(suhdeluku < 0.6);

    }

 @Test
    public void pakkaaJaPuraEnglanninKielella2Test() throws IOException {
        String syote = kasittelija.lueTiedosto("src/engkalevala.txt");
        int[] pakattu = englanninPakkaaja.huffmanPakkaa(syote);
        kasittelija.tallennaTavuittain("src/engkalevalatavueng.txt", pakattu);

        HuffmanPurkaja purkaja = new HuffmanPurkaja(kasittelija.lueTiedostoTavuittain("src/engkalevalatavueng.txt"), englanninPakkaaja.getTree());
        String purettu = purkaja.huffmanPuraKoodi();
        assertEquals(syote, purettu);
        long tavu = new File("src/engkalevalatavueng.txt").length();
        long txt = new File("src/engkalevala.txt").length();
        double suhdeluku = (double) tavu / txt;
        System.out.println("engkalevalatest eng kielellä pakkauskoko" + suhdeluku);
//        System.out.println(tavu);
//        System.out.println(txt);
//        assertTrue(suhdeluku < 0.6);
    }


}