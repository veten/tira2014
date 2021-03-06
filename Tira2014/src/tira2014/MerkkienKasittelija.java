package tira2014;

/**
 * Luokasta voi luoda merkistöä käsittelevän olion, joka muuntaa merkkejä ja
 * merkkijonoja asciikoodeiksi. Lisäksi luokassa määritellään kaikkien luokkien
 * käyttämä merkistö.
 */
public class MerkkienKasittelija {

    private String merkisto;

    /**
     * Konstruktorissa alustetaan kaikkien luokkien käyttämä merkistö.
     * Normaaleista ascii koodeista poiketen koodeille 26-31 määritellään
     * merkit: "ÅÄÖåäö"
     */
    public MerkkienKasittelija() {
        merkisto = "ÅÄÖåäö !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_´abcdefghijklmnopqrstuvwxyz{|}~";
    }

    public String getMerkisto() {
        return merkisto;
    }

    /**
     * Metodi laskee potenssi laskutoimituksen, kun potenssi on ei-negatiivinen.
     *
     * @param luku luku, joka potenssia lasketaan
     * @param potenssi ei-negatiivinen potenssi, johon luku korotetaan
     *
     * @return laskutoimituksen vastaus
     */
    public long positiivinenPotenssi(int luku, int potenssi) {
        if (potenssi > 0) {
            long palautettava = luku;
            for (int i = 2; i <= potenssi; i++) {
                palautettava *= luku;
            }
            return palautettava;
        }
        return 1;
    }

    /**
     * Metodi muuntaa merkkijonon merkkien asciikoodien yhdisteeksi.
     *
     * @param sana asciikoodiyhdisteeksi muunnettava merkkijono
     *
     * @return palauttaa merkkijonon muunnoksen lukuna
     */
    public long merkkijonoAsciiKoodiksi(String sana) {
        long arvo = 0;
        for (int i = 0; i < sana.length(); i++) {
            arvo += positiivinenPotenssi(128, i) * merkkiAsciiKoodiksi(sana.charAt(i));
        }
        return arvo;
    }

    /**
     * Metodi muuntaa merkin asciikoodiksi.
     *
     * @param kirjain asciikoodiksi muunnettava kirjain
     *
     * @return palauttaa merkin asciikoodin
     */
    public int merkkiAsciiKoodiksi(char kirjain) {
        for (int i = 26; i < 127; i++) {
            if (kirjain == merkisto.charAt(i - 26)) {
                return i;
            }
        }
        return -1;
    }

    public char asciiKoodiMerkiksi(int koodi) {
        return merkisto.charAt(koodi - 26);
    }

    public int muunnaBittijonoKokonaisluvuksi(String bitit) {
        int summa = 0;
        for (int i = 0; i < bitit.length(); i++) {
            summa += (positiivinenPotenssi(2, i) * Integer.parseInt(bitit.charAt(bitit.length() - 1 - i) + ""));
        }
        return summa;
    }

    public int[] muunnaBittiJonoKokonaislukutaulukoksi(String bitit) {
        int viimeisenPituus = bitit.length() % 8;
        int[] palautettava;
        if (viimeisenPituus != 0) {
            palautettava = new int[bitit.length() / 8 + 2];
        } else {
            palautettava = new int[bitit.length() / 8 + 1];
        }
        int indeksi = 0;

        for (int i = 0; i <= bitit.length() - 8; i += 8) {
            palautettava[indeksi] = muunnaBittijonoKokonaisluvuksi(bitit.substring(i, i + 8));
            indeksi++;
        }
        if (viimeisenPituus != 0) {
            palautettava[indeksi] = muunnaBittijonoKokonaisluvuksi(bitit.substring(bitit.length() - viimeisenPituus));
            indeksi++;
        }
        palautettava[indeksi] = viimeisenPituus; // viimeisenä on vajaan tavun pituus..        
        return palautettava;
    }
}
