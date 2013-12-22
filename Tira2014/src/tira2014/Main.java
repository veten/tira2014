package tira2014;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Main-luokka, josta ohjelma ajetaan.
 */
public class Main {

    /**
     * Tällä metodilla on vain testattu keon toimivuutta, koska testiluokkia ei 
     * ole vielä toteutettu.. tulee siis poistumaan myöhemmin.
     *
     */
    public static void kekoTestaus() {
        Minimikeko keko = new Minimikeko(10);
        keko.HeapInsert(new Node(7, 'x'));
        keko.HeapInsert(new Node(1, 'r'));
        keko.HeapInsert(new Node(5, 'k'));
        keko.HeapInsert(new Node(3, 'a'));
        keko.HeapInsert(new Node(5, 'w'));

        System.out.println(keko.HeapMin());
        System.out.println(keko.HeapDelMin());

        System.out.println(keko.HeapMin());
        System.out.println(keko.HeapDelMin());

        keko.HeapInsert(new Node(11, 'g'));
        keko.HeapInsert(new Node(2, 'p'));


        System.out.println(keko.HeapMin());
        System.out.println(keko.HeapDelMin());
        System.out.println(keko.HeapMin());
        System.out.println(keko.HeapDelMin());
        System.out.println(keko.HeapMin());
        System.out.println(keko.HeapDelMin());
        System.out.println(keko.HeapMin());
        System.out.println(keko.HeapDelMin());
        System.out.println(keko.HeapMin());
        System.out.println(keko.HeapDelMin());
        System.out.println(keko.HeapMin());
        System.out.println(keko.HeapDelMin());
    }

    /**
     * Metodi lukee tiedoston, ja palauttaa sen sisällön.
     *
     * @param tiedosto luettava tiedosto
     *
     * @return tiedoston sisältö
     */
    public static String lueTiedosto(File tiedosto) {
        String palautus = "";
        Scanner lukija = null;
        try {
            lukija = new Scanner(tiedosto);
        } catch (Exception e) {
            return "Tiedoston lukeminen epäonnistui. Virhe: " + e.getMessage();
        }
        while (lukija.hasNextLine()) {
            palautus += lukija.nextLine();
        }
        lukija.close();
        return palautus;
    }

    /**
     * Metodi tallentaa sille annetun tekstin haluttuun tiedostoon.
     *
     * @param tiedostonNimi tiedoston nimi, johon tallennus tehdään
     * @param teksti tallennettava teksti
     */
    public static void tallennaTiedosto(String tiedostonNimi, String teksti) throws Exception {
        FileWriter kirjoittaja = new FileWriter(tiedostonNimi);
        kirjoittaja.write(teksti);
        kirjoittaja.close();
    }

    /**
     * Toistaiseksi koodeja on testattu vain täällä main:ssa, koska testiluokat vielä toteuttamatta.
     */
    public static void main(String[] args) throws Exception {

        
        String syote = "testaus syöte. tällä vain kokeilen alustavaa pakkaajaa. kirjoitellaan nyt hieman "
                + "enemmänkin tekstiä, että saadaan hieman enemmän koodattavaa! vieläkin enemmän voisi "
                + "kirjoittaa, että saisi kuvaa tuleeko selviä eroja kirjainten välille.";
        System.out.println(syote);
        tallennaTiedosto("src/alkuperainenSyote.txt", syote);
        File alkuperainen = new File("src/alkuperainenSyote.txt");
        String pakkaajalle = lueTiedosto(alkuperainen);

        Pakkaaja pakkaaja = new Pakkaaja(pakkaajalle, null);
        String koodattu = pakkaaja.pakkaa();
        System.out.println(koodattu);
//        pakkaaja.tulostaKoodit(pakkaaja.getTree().getRoot());
        tallennaTiedosto("src/koodattu.txt", koodattu);
//        System.out.println("Koodin pituus: " + pakkaaja.getKoodinPituus());
//        System.out.println("Koodin pituus, jos jokainen merkki olisi 8 bittinen, olisi merkkien määrä x 8 eli: " + pakkaaja.getMerkkienMaara() * 8);
//        System.out.println("Eli koodi lyheni: " + (double) pakkaaja.getKoodinPituus() / (pakkaaja.getMerkkienMaara() * 8) + " prosenttiseksi");
        File purettavaksi = new File("src/koodattu.txt");
        String purettava = lueTiedosto(purettavaksi);
        Purkaja purkaja = new Purkaja(purettava, pakkaaja.getTree());
        String purettu = purkaja.puraKoodi();
        System.out.println(purettu);
        Pakkaaja uusiPakkaaja = new Pakkaaja(purettu, null);
        String koodattu2 = uusiPakkaaja.pakkaa();
        tallennaTiedosto("src/koodattu2.txt", koodattu2);
        File tiedosto2 = new File("src/koodattu2.txt");
        String purettava2 = lueTiedosto(tiedosto2);
        System.out.println(purettava2);
    }
}
