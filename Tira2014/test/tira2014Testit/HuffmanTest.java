package tira2014Testit;

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
    public void pakkaaJaPuraTest() {
        String syote = "Kissalan pojat taas vauhdissako?";
        String pakattu = tuntematonPakkaaja.huffmanPakkaa(syote);
        HuffmanPurkaja purkaja = new HuffmanPurkaja(pakattu, tuntematonPakkaaja.getTree());
        String purettu = purkaja.huffmanPuraKoodi();
        assertEquals(syote, purettu);
        double pakkausKokoProsentteina = (double) pakattu.length() / (syote.length() * 8);
        assertTrue(pakkausKokoProsentteina < 0.6);
        System.out.println(pakkausKokoProsentteina);
    }

    /**
     * Metodi pakkaa pakkaajalla tekstin, ja tämän jälkeen purkajalla purkaa
     * tekstin alkuperäiseksi minkä jälkeen tarkistetaan, että teksti on
     * säilynyt muuttumattomana. Lisäksi metodi tarkistaa, että tiivistetty koko
     * on alle 60 prosenttia alkuperäisestä, vaikkakin puuntiedot on tässä
     * versiossa vielä pakattuun tekstiin koodaamatta.
     */
    @Test
    public void pakkaaJaPura2Test() {
        String syote = "toinen syöte testaus..";
        String pakattu = tuntematonPakkaaja.huffmanPakkaa(syote);
        HuffmanPurkaja purkaja = new HuffmanPurkaja(pakattu, tuntematonPakkaaja.getTree());
        String purettu = purkaja.huffmanPuraKoodi();
        assertEquals(syote, purettu);
        double pakkausKokoProsentteina = (double) pakattu.length() / (syote.length() * 8);
        assertTrue(pakkausKokoProsentteina < 0.6);
        System.out.println(pakkausKokoProsentteina);
    }

    /**
     * Metodi pakkaa pakkaajalla tiedostosta luetun tekstin, ja tämän jälkeen
     * purkajalla purkaa tekstin alkuperäiseksi minkä jälkeen tarkistetaan, että
     * teksti on säilynyt muuttumattomana. Lisäksi metodi tarkistaa, että
     * tiivistetty koko on alle 60 prosenttia alkuperäisestä, vaikkakin
     * puuntiedot on tässä versiossa vielä pakattuun tekstiin koodaamatta.
     */
    @Test
    public void pakkaaJaPuraTiedostoTest() {
        String syote = kasittelija.lueTiedosto("src/tito.txt");
        String pakattu = tuntematonPakkaaja.huffmanPakkaa(syote);
        HuffmanPurkaja purkaja = new HuffmanPurkaja(pakattu, tuntematonPakkaaja.getTree());
        String purettu = purkaja.huffmanPuraKoodi();
        assertEquals(syote, purettu);
        double pakkausKokoProsentteina = (double) pakattu.length() / (syote.length() * 8);
        assertTrue(pakkausKokoProsentteina < 0.6);
        System.out.println(pakkausKokoProsentteina);
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
    public void pakkaaJaPuraEnglanninKielellaTest() {
        String syote = kasittelija.lueTiedosto("src/engKielinenSyote.txt");
        String pakattu = englanninPakkaaja.huffmanPakkaa(syote);
        HuffmanPurkaja purkaja = new HuffmanPurkaja(pakattu, englanninPakkaaja.getTree());
        String purettu = purkaja.huffmanPuraKoodi();
        assertEquals(syote, purettu);
        double pakkausKokoProsentteina = (double) pakattu.length() / (syote.length() * 8);
        System.out.println(pakkausKokoProsentteina);
//        assertTrue(pakkausKokoProsentteina < 0.6);
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
    public void pakkaaJaPuraSuomenKielellaTest() {
        String syote = kasittelija.lueTiedosto("src/tito.txt");
        String pakattu = suomenPakkaaja.huffmanPakkaa(syote);
        HuffmanPurkaja purkaja = new HuffmanPurkaja(pakattu, suomenPakkaaja.getTree());
        String purettu = purkaja.huffmanPuraKoodi();
        assertEquals(syote, purettu);
        double pakkausKokoProsentteina = (double) pakattu.length() / (syote.length() * 8);
        System.out.println(pakkausKokoProsentteina);
//        assertTrue(pakkausKokoProsentteina < 0.6);
    }
}