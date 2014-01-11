package tira2014;

/**
 * Minimikeko-luokka luo minimikeon, johon voidaan lisätä alkioita ja josta
 * voidaan ottaa pois alkioita koko järjestyksessä siten, että aina saadaan
 * pienin alkio poistettua joukosta.
 */
public class Minimikeko {

    private Solmu[] taulukko;
    private int keonKoko;

    /**
     * Konstruktorissa keolle annetaan keon maksimi koko ja alustetaan
     * viimeisimmän alkion kohta. Keko toteutetaan taulukolla, josta vain osa
     * kuuluu kulloinkin itse kekoon. KeonKoko:lla määritetään viimeisin
     * taulukon kohta, joka kuuluu kekoon. Jos taulukko tulee täyteen, täytyy
     * luoda uusi isompi keko ja kopioida kaikki arvot siihen ennen keon käytön
     * jatkamista.
     *
     * @param koko taulukon koko, joka määrittää keon maksimikoon, mutta jota
     * tarvittaessa kasvatetaan tilan loppuessa
     */
    public Minimikeko(int koko) {
        this.taulukko = new Solmu[koko];
        this.keonKoko = -1;
    }

    /**
     * Vaihtoehtoisessa konstruktorissa keolle annetaan valmis Solmu-taulukko.
     * Tätä vaihtoehtoa käytetään Huffman-purkajassa, jolle annetussa koodissa
     * on mukana keon tiedot.
     *
     * @param taulukko valmiiksi luotu keon Solmu-taulukko
     */
    public Minimikeko(Solmu[] taulukko) {
        this.taulukko = taulukko;
        this.keonKoko = taulukko.length - 1;
    }

    public Solmu[] getTaulukko() {
        return taulukko;
    }

    /**
     * Metodi lisää kekoon uuden solmun ja asettaa sen keossa oikealle
     * paikalleen.
     *
     * @param solmu kekoon vietävä solmu
     */
    public void lisaaKekoon(Solmu solmu) {
        keonKoko++;
        if (keonKoko > taulukko.length) {
            Solmu[] kopio = taulukko;
            taulukko = new Solmu[keonKoko * 2];
            taulukko = kopio;
        }
        int i = keonKoko;
        int vanhempi = (i - 1) / 2;
        while (i > 0 && taulukko[vanhempi].getAvain() > solmu.getAvain()) {
            taulukko[i] = taulukko[vanhempi];
            i = vanhempi;
            vanhempi = (i - 1) / 2;
        }
        taulukko[i] = solmu;
    }

    /**
     * Metodi palauttaa minimikeon päällimmäisen, eli pienimmän avaimen omaavan
     * solmun keosta.
     *
     * @return solmu, jolla pienin avain
     */
    public Solmu palautaPienin() {
        if (keonKoko >= 0) {
            return taulukko[0];
        } else {
            return null;
        }
    }

    /**
     * Metodi poistaa koesta solmun, jolla pienin avainarvo sekä palauttaa tämän
     * solmun. Lisäksi metodi korjaa keon rakenteen takaisin minimikeoksi.
     *
     * @return palauttaa poistamansa solmun, jolla pienen avain
     */
    public Solmu palautaJaPoistaPienin() {
        if (keonKoko >= 0) {
            Solmu min = taulukko[0];
            taulukko[0] = taulukko[keonKoko];
            keonKoko--;
            korjaaKeko(0);
            return min;
        } else {
            return null;
        }
    }

    /**
     * Metodi palauttaa kekoon kuuluvan taulukon viimeisimmän alkion
     * indeksikohdan.
     *
     * @return -1, jos keko on tyhjä ja muulloin keossa olevien alkioiden määrä
     * miinus yksi, eli keon alimmaisen alkion indeksikohta taulukossa
     */
    public int getKeonKoko() {
        return keonKoko;
    }

    /**
     * Metodi korjaa keon keko-ominaisuuden, eli järjestää alkiot niin, että
     * solmun lasten avainten arvot on aina suuremmat kuin vanhemmallansa.
     * Aikavaativuus: O(log n).
     *
     * @param kohta korjaa keon rekursiivisesti tästä solmusta alaspäin.
     */
    private void korjaaKeko(int kohta) {
        int vasen = 2 * (kohta + 1) - 1;
        int oikea = 2 * (kohta + 1);
        int pienempi = oikea;
        if (oikea <= keonKoko) {
            if (taulukko[vasen].getAvain() < taulukko[oikea].getAvain()) {
                pienempi = vasen;
            }
            if (taulukko[kohta].getAvain() > taulukko[pienempi].getAvain()) {
                Solmu talteen = taulukko[kohta];
                taulukko[kohta] = taulukko[pienempi];
                taulukko[pienempi] = talteen;
                korjaaKeko(pienempi);
            }
        } else if (vasen == keonKoko && taulukko[kohta].getAvain() > taulukko[vasen].getAvain()) {
            Solmu talteen = taulukko[kohta];
            taulukko[kohta] = taulukko[vasen];
            taulukko[vasen] = talteen;
        }
    }
}
