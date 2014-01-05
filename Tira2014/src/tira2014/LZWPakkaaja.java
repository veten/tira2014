package tira2014;

import java.util.ArrayList;

/**
 * LZWPakkaaja-luokka luo pakkaaja-olioita, jotka pakkaavat syötteen käyttäen
 * LZW algoritmia.
 */
public class LZWPakkaaja {

//    private String[] kirjasto;
    private ArrayList<String> arrayKirjasto; // alustavan version kirjasto..

    public LZWPakkaaja() {
//        this.kirjasto = new String[200];  
        this.arrayKirjasto = new ArrayList<String>();
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
     * Metodi pakkaa syötteen käyttäen LZW algoritmia. Algoritmissa etsitään kirjastosta
     * pisin vastaava merkkijono ja otetaan talteen tämä taulukon kohta. Sitten syötteestä
     * poistetaan kyseinen syöteen osa ja lisätään kirjastoon poistettu osa plus seuraava 
     * merkkijonon merkki. Näitä vaiheita toistetaan kunnes syöte on käyty läpi.
     * 
     * @param syote merkkijono, joka pakataan
     *
     * @return palauttaa listan kirjaston kohtia
     */
    public ArrayList<Integer> lZWPakkaa(String syote) {
        ArrayList<Integer> palautettava = new ArrayList<Integer>();
        String testaus = "";
        int i = 0;
        while (i < syote.length()) {
            testaus += syote.charAt(i);
            if (!arrayKirjasto.contains(testaus)) {
                int kirjastonIndeksi = arrayKirjasto.indexOf(testaus.substring(0, testaus.length() - 1));
                palautettava.add(kirjastonIndeksi);
                arrayKirjasto.add(testaus);
                testaus = "";
                i--;
            }
            i++;
        }
        palautettava.add(arrayKirjasto.indexOf(testaus));
        return palautettava;
    }
}
