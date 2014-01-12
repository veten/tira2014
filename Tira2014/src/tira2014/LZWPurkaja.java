package tira2014;

/**
 * Luokka luo LZWpurkaja-olioita, jotka purkavat saamansa LZWPakatun syötteen
 * käyttäen LZW algoritmia.
 */
public class LZWPurkaja {

    private String[] kirjasto;
    private int seuraavaKirjastonPaikka;
    private String bitit;
    private int bittienMaara;
    private MerkkienKasittelija ascii;

    public LZWPurkaja(String bitit) {
        this.ascii = new MerkkienKasittelija();
        this.bitit = bitit;
        this.bittienMaara = 7;
        this.seuraavaKirjastonPaikka = 0;
        this.kirjasto = new String[128]; 
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

    private void kasvataKirjastoa() {
        String[] talteen = kirjasto;
        kirjasto = new String[2 * talteen.length];
        System.arraycopy(talteen, 0, kirjasto, 0, talteen.length);
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
        while (i < bitit.length() - (2 * bittienMaara)) {
            String sana = kirjasto[ascii.muunnaBittijonoKokonaisluvuksi(bitit.substring(i, i + bittienMaara))];
            palautettava += sana;
            int seuraavanIndeksi = ascii.muunnaBittijonoKokonaisluvuksi(bitit.substring(i + bittienMaara, i
                    + (bittienMaara * 2)));
            if (seuraavaKirjastonPaikka <= seuraavanIndeksi) {
                kirjasto[seuraavaKirjastonPaikka] = sana.charAt(0) + "";
            } else {
                char kirjain = kirjasto[seuraavanIndeksi].charAt(0);
                kirjasto[seuraavaKirjastonPaikka] = sana + kirjain;
            }
            seuraavaKirjastonPaikka++;
            i += bittienMaara;
            if (seuraavaKirjastonPaikka >= ascii.positiivinenPotenssi(2, bittienMaara)) {
                bittienMaara++;
            }
            if (seuraavaKirjastonPaikka >= kirjasto.length - 1) {
                kasvataKirjastoa();
            }
        }
        palautettava += kirjasto[ascii.muunnaBittijonoKokonaisluvuksi(bitit.substring(i, i + bittienMaara))];
        palautettava += kirjasto[ascii.muunnaBittijonoKokonaisluvuksi(bitit.substring(i + bittienMaara))];
        return palautettava;
    }
}
