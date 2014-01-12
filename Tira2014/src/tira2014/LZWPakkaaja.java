package tira2014;

/**
 * LZWPakkaaja-luokka luo pakkaaja-olioita, jotka pakkaavat syötteen käyttäen
 * LZW algoritmia.
 */
public class LZWPakkaaja {

    private String[] kirjasto;
    private int seuraavaKirjastonPaikka;
    private MerkkienKasittelija ascii;
    private int bittienMaara;

    public LZWPakkaaja() {
        this.kirjasto = new String[128];
        this.seuraavaKirjastonPaikka = 0;
        this.ascii = new MerkkienKasittelija();
        this.bittienMaara = 7;
        lisaaYhdenMittaiset();
    }

    /**
     * Metodi lisää kirjastoon kaikki yhden merkin mittaiset merkkijonot.
     */
    private void lisaaYhdenMittaiset() {
        String merkit = new MerkkienKasittelija().getMerkisto();
        for (int i = 0; i < merkit.length(); i++) {
            kirjasto[i] = merkit.charAt(i) + "";
            seuraavaKirjastonPaikka++;
        }
    }

    public String[] getKirjasto() {
        return kirjasto;
    }

    /**
     * Metodi etsii kirjastosta merkkijonoa ja palauttaa kirjaston indeksin.
     *
     */
    private int etsiKirjastonIndeksi(String testaus) {
        for (int i = 0; i < kirjasto.length; i++) {
            if (testaus.equals(kirjasto[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metodi tuplaa kirjaston koon.
     *
     */
    private void kasvataKirjastoa() {
        String[] talteen = kirjasto;
        kirjasto = new String[2 * talteen.length];
        System.arraycopy(talteen, 0, kirjasto, 0, talteen.length);
    }

    /**
     * Metodi muuntaa luvun biteiksi.
     *
     * @param luku lukuarvo, joka muunnetaan biteiksi.
     *
     * @return palauttaa bitit String muodossa
     */
    public String muunnaBiteiksi(int luku) {
        String bitit = "";
        for (int i = bittienMaara - 1; i >= 0; i--) {
            long vertailtava = ascii.positiivinenPotenssi(2, i);
            if (luku >= vertailtava) {
                bitit += "1";
                luku -= vertailtava;
            } else {
                bitit += "0";
            }
        }
        return bitit;
    }

    /**
     * Metodi pakkaa syötteen käyttäen LZW algoritmia. Algoritmissa etsitään
     * kirjastosta pisin vastaava merkkijono ja otetaan talteen tämä taulukon
     * kohta. Sitten syötteestä poistetaan kyseinen syöteen osa ja lisätään
     * kirjastoon poistettu osa plus seuraava merkkijonon merkki. Näitä vaiheita
     * toistetaan kunnes syöte on käyty läpi.
     *
     * @param syote merkkijono, joka pakataan
     *
     * @return palauttaa listan kirjaston kohtia
     */
    public int[] lZWPakkaa(String syote) {
        String bitit = "";
        String testaus = "";
        int i = 0;
        while (i < syote.length()) {
            testaus += syote.charAt(i);
            if (etsiKirjastonIndeksi(testaus) == -1) {
                int kirjastonIndeksi = etsiKirjastonIndeksi(testaus.substring(0, testaus.length() - 1));

                bitit += muunnaBiteiksi(kirjastonIndeksi);
                kirjasto[seuraavaKirjastonPaikka] = testaus;
                seuraavaKirjastonPaikka++;
                if (seuraavaKirjastonPaikka >= ascii.positiivinenPotenssi(2, bittienMaara)) {
                    bittienMaara++;
                }
                if (seuraavaKirjastonPaikka >= kirjasto.length - 1) {
                    kasvataKirjastoa();
                }
                testaus = "";
                i--;
            }
            i++;
        }
        int kirjastonIndeksi = etsiKirjastonIndeksi(testaus);
        bitit += muunnaBiteiksi(kirjastonIndeksi);
        return ascii.muunnaBittiJonoKokonaislukutaulukoksi(bitit);
    }
}
