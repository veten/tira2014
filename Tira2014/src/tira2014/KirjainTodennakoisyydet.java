package tira2014;

/**
 * Luokkassa on määritelty eri kielten kirjain todennäköisyydet
 * taulukkomuodossa. Järjestys on sama kuin Ascii-luokassa määritellyn merkistön
 * järjestys. Myös tämä merkistö löytyy täältä taulukkomuodossa. Metodeissa on
 * taulukoitu eri kielten kirjain todennäköisyydet kerrottuna tuhannella, jotta
 * on päästy kokonaislukuihin. Tuntemattomat todennäköisyydet on taulukuitu
 * ykkösinä, mikä tällä hetkellä tekee Huffman-pakkauksesta tehotonta, koska
 * esim. välilyönnit, pisteet ja pilkut ovat merkittävästi yleisempiä.
 */
public class KirjainTodennakoisyydet {

    public static int[] englanti = {2, 2, 2, 2, 2, 2, 169, 4, 3, 1, 1, 1, 1, 1, 4, 4, 1, 1, 9, 4, 5, 1, 3, 3,
        3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 1, 1, 1, 4, 1, 8167, 1492, 2782, 4253, 12702, 2228, 2015, 6094, 6966,
        153, 772, 4025, 2406, 6749, 7507, 1929, 95, 5987, 6327, 9056, 2758, 978, 2360, 150, 1974, 74, 1, 1,
        1, 1, 1, 1, 8167, 1492, 2782, 4253, 12702, 2228, 2015, 6094, 6966, 153, 772, 4025, 2406, 6749, 7507,
        1929, 95, 5987, 6327, 9056, 2758, 978, 2360, 150, 1974, 74, 1, 1, 1, 1};
    public static int[] suomi = {3, 3577, 444, 3, 3577, 444, 130, 4, 3, 1, 1, 1, 1, 1, 4, 4, 1, 1, 9, 4, 5, 1,
        3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 1, 1, 1, 4, 1, 12217, 281, 281, 1043, 7968, 194, 392, 1851, 10817,
        2042, 4973, 5761, 3202, 8826, 5614, 1842, 13, 2872, 7862, 8750, 5008, 2250, 94, 31, 1745, 51, 1, 1, 1,
        1, 1, 1, 12217, 281, 281, 1043, 7968, 194, 392, 1851, 10817, 2042, 4973, 5761, 3202, 8826, 5614, 1842,
        13, 2872, 7862, 8750, 5008, 2250, 94, 31, 1745, 51, 1, 1, 1, 1};

    /**
     * Metodi luo Ascii-luokan käyttämän merkistön sisältämän taulukon.
     *
     */
    public static char[] kirjaimet() {
        char[] taulukko = new char[101];
        String merkit = new MerkkienKasittelija().getMerkisto();
        for (int i = 0; i < merkit.length(); i++) {
            taulukko[i] = merkit.charAt(i);
        }
        return taulukko;
    }
}
