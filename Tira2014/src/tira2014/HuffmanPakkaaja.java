package tira2014;

/**
 * Luokka luo pakkaaja-olioita, jotka pakkaavat syötteitä käyttäen Huffman
 * algoritmia.
 */
public class HuffmanPakkaaja {

    private int[] tiheys;
    private char[] kirjain;
    private Solmu[] solmut;
    private Minimikeko keko;
    private Puu tree;
    private boolean tiheydetTarvitaan;
    private Ascii ascii;

    /**
     * Konstruktorissa pakkaaja-oliolle annetaan merkkien
     * todennäköisyystaulukko, mikäli se on tiedossa.
     *
     * @param todennakoisyydet jos eri merkkien todennäköisyydet on tiedossa,
     * annetaan ne pakkaajalle tässä, jolloin merkkien esiintymistiheyksiä ei
     * syötteestä erikseen lasketa. Jos tätä tietoa ei ole, merkitään
     * parametriksi null.
     *
     * Ei vielä toimi oikein todennäköisyyksillä, vaan ainoastaan itse tiheydet
     * laskien.
     */
    public HuffmanPakkaaja(int[] todennakoisyydet) {
        this.ascii = new Ascii();
        if (todennakoisyydet != null) {
            this.tiheys = todennakoisyydet;
            this.kirjain = KirjainTodennakoisyydet.kirjaimet();
            tiheydetTarvitaan = false;
        } else {
            this.tiheys = new int[101]; // 101 = eri merkkien määrä.. 
            this.kirjain = new char[101]; // kohtien mukaan
            tiheydetTarvitaan = true;
        }
        this.solmut = new Solmu[101];
        this.keko = new Minimikeko(200);
        this.tree = new Puu();
    }

    /**
     * Metodi laskee syotteesta eri merkkien tiheydet.
     *
     * @param syote merkkijono, josta merkkien tiheydet lasketaan
     */
    private void laskeTiheydet(String syote) {
        String merkit = new Ascii().getMerkisto();
        for (int i = 0; i < syote.length(); i++) {
            int j = 0;
            while (j < merkit.length()) {
                if (syote.charAt(i) == merkit.charAt(j)) {
                    tiheys[j]++;
                    kirjain[j] = merkit.charAt(j);
                    break;
                }
                j++;
            }
        }
    }

    public int[] getTiheydet() {
        return tiheys;
    }

    public char[] getKirjain() {
        return kirjain;
    }

    /**
     * Metodi luo solmut ja lisää minimikekoon. Lisäksi solmut lisätään
     * erilliseen solmulistaan, johon siis jokaiselle merkille muodostetaan oma
     * solmu.
     *
     */
    private void taytaKeko(String syote) {
        if (tiheydetTarvitaan) {
            laskeTiheydet(syote);
        }
        for (int i = 0; i < tiheys.length; i++) {
            Solmu solmussa = new Solmu(tiheys[i], kirjain[i]);
            if (tiheys[i] > 0) {
                keko.lisaaKekoon(solmussa);
            }
            solmut[i] = solmussa;
        }
        if (tiheydetTarvitaan) {
            tallennaKekoStringiksi();
        }
    }

    /**
     * Metodi tallentaa täytetyn keon taulukon solmuista niiden avaimet ja
     * kirjaimet merkkijonoksi, joka liitetään tiedostoon tallennettavaan
     * merkkijonoon, jos tiheydet on tarvittu. Tästä saadaan konstruoitua
     * Huffman purkajassa puu koodin purkua varten.
     *
     * @return keon taulukko Stringinä
     */
    public String tallennaKekoStringiksi() {
        String palautettava = "";
        int indeksi = 0;
        while (indeksi <= keko.getKeonKoko()) {
            palautettava += keko.getTaulukko()[indeksi].getAvain();
            palautettava += keko.getTaulukko()[indeksi].getKirjain();
            indeksi++;
        }
        return palautettava;
    }

    /**
     * Metodissa täytetään puu rakenne minimikekoa hyväksi käyttäen.
     *
     */
    private void taytaPuu() {
        while (true) {
            Solmu eka = keko.palautaJaPoistaPienin();
            Solmu toka = keko.palautaJaPoistaPienin();
            Solmu parentti = new Solmu(eka.getAvain() + toka.getAvain(), '*');
            parentti.setVasen(eka);
            parentti.setOikea(toka);
            eka.setVanhempi(parentti);
            toka.setVanhempi(parentti);
            if (keko.getKeonKoko() >= 0) {
                keko.lisaaKekoon(parentti);
            } else {
                tree.setJuuri(parentti);
                break;
            }
        }
        lisaaSolmuilleKoodit(tree.getJuuri(), "", null);
    }

    /**
     * Metodi lisää jokaiselle solmuille niiden merkkiä vastaavat uudet
     * binäärikoodit.
     *
     * @param solmu solmu, jolle koodi lisätään
     * @param koodi koodi, joka solmuun lisätään
     * @param vanhempi solmun vanhempi
     *
     */
    private void lisaaSolmuilleKoodit(Solmu solmu, String koodi, Solmu vanhempi) {
        if (solmu != null) {
            lisaaSolmuilleKoodit(solmu.getVasen(), koodi + "0", solmu);
            lisaaSolmuilleKoodit(solmu.getOikea(), koodi + "1", solmu);
        } else {
            vanhempi.setHuffmanKoodi(koodi.substring(0, koodi.length() - 1));
        }
    }

    /**
     * Metodi tulostaa rekursiivisesti kaikkien merkkien koodit, kun tätä kutsuu
     * juurisolmulle. Tätä metodia on käytetty vain koodin testaamiseen.
     *
     * @param solmu solmu, jonka koodaus halutaan tulostaa
     */
    public void tulostaKoodit(Solmu solmu) {
        if (solmu != null) {
            if (solmu.getKirjain() != '*') {
                System.out.println(solmu.getKirjain() + " : " + solmu.getHuffmanKoodi());
            }
            tulostaKoodit(solmu.getVasen());
            tulostaKoodit(solmu.getOikea());
        }
    }

    /**
     * Metodi tekee varsinaisen syötteen pakkaamisen Huffmanin algoritmilla, ja
     * palauttaa Stringinä koodauksen mukaisen binäärijonon. Metodi ensin
     * täyttää minimikeon ja sen jälkeen puun. Ja vasta tämän jälkeen lukee
     * merkkien koodit.
     *
     * @return palauttaa syötteen pakattuna binäärimuotoon
     */
    public int[] huffmanPakkaa(String syote) {
        taytaKeko(syote);
        taytaPuu();
        String bitit = "";

        for (int i = 0; i < syote.length(); i++) {
            int j = 0;
            while (j < solmut.length) {
                if (syote.charAt(i) == solmut[j].getKirjain()) {
                    bitit += solmut[j].getHuffmanKoodi();
                    break;
                }
                j++;
            }
        }
        return ascii.muunnaBittiJonoKokonaislukutaulukoksi(bitit);
    }



    public Puu getTree() {
        return tree;
    }
}
