package tira2014Testit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tira2014.LinkitettyLista;
import tira2014.Solmu;

/**
 * Luokka testaa LinkitettyLista-luokan metodeja.
 */
public class LinkitettyListaTest {

    private LinkitettyLista lista;

    /**
     * Alustetaan attribuutti.
     */
    public LinkitettyListaTest() {
        lista = new LinkitettyLista();
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
     * Metodi lisää linkitettyyn listaan ensin solmuja ja tämän jälkeen etsii
     * listalta tietyn avaimen mukaisen solmun.
     */
    @Test
    public void listaanLisaysJaHakuTest() {
        lista.lisaa(new Solmu(11, 'q'));
        lista.lisaa(new Solmu(1, 'y'));
        Solmu solmu = new Solmu(78, 'p');
        lista.lisaa(solmu);
        lista.lisaa(new Solmu(34, 'm'));
        lista.lisaa(new Solmu(111, 'x'));
        assertEquals(lista.hae(78), solmu);
    }

    /**
     * Metodi lisää linkitettyyn listaan kaksi solmua, ja tarkistaa tämän
     * jälkeen, että ne on linkitetty keskenään.
     */
    @Test
    public void listanLinkitysTest() {
        Solmu solmu = new Solmu(78, 'p');
        lista.lisaa(solmu);
        Solmu solmu2 = new Solmu(8, 'o');
        lista.lisaa(solmu2);
        assertEquals(solmu2.getSeuraava(), solmu);
        assertEquals(solmu.getEdellinen(), solmu2);
    }

    /**
     * Metodi lisää linkitettyyn listaan ensin solmun ja tämän jälkeen poistaa
     * sen. Lopuksi tarkistetaan, että solmu ei löydy listalta.
     */
    @Test
    public void listastaPoistoTest() {
        lista.lisaa(new Solmu(78, 'p'));
        lista.poista(lista.hae(78));
        assertTrue(lista.hae(78) == null);
    }
}