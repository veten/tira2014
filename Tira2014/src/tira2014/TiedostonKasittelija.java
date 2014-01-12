package tira2014;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
//            palautus += "\n";
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

    public String lueTiedostoTavuittain(String polku) throws IOException {
        String palautettava = "";
        FileInputStream in = null;
        try {
            in = new FileInputStream(polku);
            int c;
            int vikanPituus = 0;
            while ((c = in.read()) != -1) {
                palautettava += muunnaBiteiksi(c, 128);
                vikanPituus = c;
            }
            String alku = palautettava.substring(0, palautettava.length() - 16);
            String vikatavu = palautettava.substring(palautettava.length() - 16, palautettava.length() - 8);
            if (vikanPituus != 0) {
                vikatavu = vikatavu.substring(8 - vikanPituus);
            }
            palautettava = alku + vikatavu;
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return palautettava;
    }

    public static String muunnaBiteiksi(int c, int raja) {
        String palautettava = "";
//        int raja = 128;
        while (raja >= 2) {
            if (c >= raja) {
                palautettava += "1";
                c -= raja;
            } else {
                palautettava += "0";
            }
            raja /= 2;
        }
        if (raja <= c) {
            palautettava += "1";
        } else {
            palautettava += "0";
        }
        return palautettava;
    }

    public void tallennaTavuittain(String polku, int[] tavut) throws IOException {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(polku);
            for (int i = 0; i < tavut.length; i++) {
                out.write(tavut[i]);
            }
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
