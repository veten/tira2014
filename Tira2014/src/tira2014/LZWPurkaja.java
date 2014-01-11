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
    private Ascii ascii;
//    private int[] syotteenIndeksit;

    public LZWPurkaja(String bitit) {
        this.ascii = new Ascii();
        this.bitit = bitit;
        this.bittienMaara = 7;
        this.seuraavaKirjastonPaikka = 0;
//        this.syotteenIndeksit = new int[syote.length() / 2];
        this.kirjasto = new String[200]; //new String[puraSyoteIndeksitTaulukkoon() + 1];  // tuplavastuulla..
        lisaaYhdenMittaiset();
    }

    /**
     * Metodi lisää kirjastoon kaikki yhden merkin mittaiset merkkijonot.
     */
    private void lisaaYhdenMittaiset() {
        String merkit = new Ascii().getMerkisto();
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

//    private int puraSyoteIndeksitTaulukkoon() {
//        String intti = "";
//        int indeksi = 0;
//        for (int i = 0; i < syote.length(); i++) {
//            if (syote.charAt(i) == '.') {
//                syotteenIndeksit[indeksi] = -1;
//                return Integer.parseInt(syote.substring(i + 1));
//            } else if (syote.charAt(i) != ',') {
//                intti += syote.charAt(i);
//            } else {
//                syotteenIndeksit[indeksi] = Integer.parseInt(intti);
//                intti = "";
//                indeksi++;
//            }
//        }
//        return -1;
//    }
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
//                i++;
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
