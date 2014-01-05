package tira2014;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Luokka luo tiedostonKasittelija-olioita, jotka lukevat tiedostoja
 * Stringeiksi, sekä tallentavat String-muotoisia olioita tiedostoiksi.
 */
public class TiedostonKasittelija {

    /**
     * Metodi lukee tiedoston, ja palauttaa sen sisällön.
     *
     * @param polku luettavan tiedoston hakemistopolku
     *
     * @return tiedoston sisältö
     */
    public String lueTiedosto(String polku) {
        File tiedosto = new File(polku);
        String palautus = "";
        Scanner lukija;
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
     * @param polku hakemistopolku, johon tallennus tehdään
     * @param teksti tallennettava teksti
     */
    public void tallennaTiedosto(String polku, String teksti) throws Exception {
        FileWriter kirjoittaja = new FileWriter(polku);
        kirjoittaja.write(teksti);
        kirjoittaja.close();
    }
}
