package tira2014;

import java.util.ArrayList;

/**
 * Luokka luo LZWpurkaja-olioita, jotka purkavat saamansa LZWPakatun syötteen
 * käyttäen LZW algoritmia.
 */
public class LZWPurkaja {

    private String[] kirjasto;
    private ArrayList<String> arrayKirjasto; // alustavan version kirjasto..
    private ArrayList<Integer> syotelista;

    public LZWPurkaja(ArrayList<Integer> syotelista) {
        this.kirjasto = new String[2 * syotelista.size()];  // koko vaatii vielä tarkennusta..
        this.arrayKirjasto = new ArrayList<String>();
        this.syotelista = syotelista;
        lisaaYhdenMittaiset();
    }

    /**
     * Metodi lisää kirjastoon kaikki yhden merkin mittaiset merkkijonot.
     */
    private void lisaaYhdenMittaiset() {
        String merkit = new Ascii().getMerkisto();
        for (int i = 0; i < merkit.length(); i++) {
//            dictionary[i] = merkit.charAt(i) + "";
            arrayKirjasto.add(merkit.charAt(i) + "");
        }
    }

    public ArrayList<String> getArrayKirjasto() {
        return arrayKirjasto;
    }

    /**
     * Metodi purkaa koodin takaisin merkkijonoksi, jonka se palauttaa.
     * Purkamisen yhteydessä metodi lisää kirjastoon uusia merkkijonoja,
     * muodostaen näin vastaavan kirjaston kuin LZWPakkaaja muodosti koodia
     * pakatessaan.
     *
     * @return Purettu koodi merkkojonona.
     */
    public String lZWPuraKoodi() {
        String palautettava = "";
        int i = 0;
        while (i < syotelista.size() - 1) {
            palautettava += arrayKirjasto.get(syotelista.get(i));
            if (arrayKirjasto.size() <= syotelista.get(i + 1)) {
                arrayKirjasto.add(arrayKirjasto.get(syotelista.get(i)).charAt(0) + "");
            } else {
                arrayKirjasto.add(arrayKirjasto.get(syotelista.get(i))
                        + arrayKirjasto.get(syotelista.get(i + 1)).charAt(0));
            }
            i++;
        }
        palautettava += arrayKirjasto.get(syotelista.get(i));
        return palautettava;
    }
}
