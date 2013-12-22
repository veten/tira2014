package tira2014;

/**
 * Minimikeko-luokka luo minimikeon, johon voidaan lisätä alkioita ja josta voidaan ottaa pois alkioita koko
 * järjestyksessä siten, että aina saadaan pienin alkio poistettua joukosta.
 */
public class Minimikeko {

    private Node[] taulukko;
    private int heapsize;

    /**
     * Konstruktorissa keolle annetaan keon maksimi koko ja alustetaan viimeisimmän alkion kohta.
     * Keko toteutetaan taulukolla, josta vain osa kuuluu kulloinkin itse kekoon. Heapsizella määritetään
     * viimeisin taulukon kohta, joka kuuluu kekoon. Jos taulukko tulee täyteen, täytyy luoda uusi isompi 
     * keko ja kopioida kaikki arvot siihen ennen keon käytön jatkamista.
     *
     * @param size taulukon koko, joka määrittää keon maksimikoon, mutta jota tarvittaessa kasvatetaan tilan 
     * loppuessa
     */
    public Minimikeko(int size) {
        this.taulukko = new Node[size];
        this.heapsize = -1;
    }

    /**
     * Metodi lisää kekoon uuden solmun ja asettaa sen keossa oikealle paikalleen.
     * Aikavaativuus: O(log n).
     *
     * @param solmu kekoon vietävä solmu
     */
    public void HeapInsert(Node solmu) { 
        heapsize++;
        int i = heapsize;
        int parent = (i - 1) / 2;
        while (i > 0 && taulukko[parent].getKey() > solmu.getKey()) {
            //Node talteen = taulukko[i];
            taulukko[i] = taulukko[parent];
            i = parent;
            parent = (i - 1) / 2;
        }
        taulukko[i] = solmu;
    }

    /**
     * Metodi palauttaa minimikeon päällimmäisen, eli pienimmän avaimen omaavan solmun
     * keosta. Aikavaativuus: O(1).
     *
     * @return solmu, jolla pienin avain
     */
    public Node HeapMin() {  
        if (heapsize >= 0) {
            return taulukko[0];
        } else {
            return null;
        }
    }

    /**
     * Metodi poistaa koesta solmun, jolla pienin avainarvo sekä palauttaa tämän solmun.
     * Lisäksi metodi korjaa keon rakenteen takaisin minimikeoksi. Aikavaativuus: O(log n).
     * 
     * @return palauttaa poistamansa solmun, jolla pienen avain
     */
    public Node HeapDelMin() {  
        if (heapsize >= 0) {
            Node min = taulukko[0];
            taulukko[0] = taulukko[heapsize];
            heapsize--;
            Heapify(0);
            return min;
        } else {
            return null;
        }
    }

    /**
     * Metodi palauttaa kekoon kuuluvan taulukon viimeisimmän alkion indeksikohdan.
     *
     * @return -1, jos keko on tyhjä ja muulloin keossa olevien alkioiden määrä miinus yksi,
     * eli keon alimmaisen alkion indeksikohta taulukossa
     */
    public int getHeapsize() {
        return heapsize;
    }

    /**
     * Metodi korjaa keon keko-ominaisuuden, eli järjestää alkiot niin, että solmun lasten avainten arvot 
     * on aina suuremmat kuin vanhemmallansa. Aikavaativuus: O(log n).
     *
     * @param kohta korjaa keon rekursiivisesti tästä solmusta alaspäin.
     */
    private void Heapify(int kohta) { 
        int left = 2 * (kohta + 1) - 1;
        int right = 2 * (kohta + 1);
        int smaller = right;
        if (right <= heapsize) {
            if (taulukko[left].getKey() < taulukko[right].getKey()) {
                smaller = left;
            }
            if (taulukko[kohta].getKey() > taulukko[smaller].getKey()) {
                Node talteen = taulukko[kohta];
                taulukko[kohta] = taulukko[smaller];
                taulukko[smaller] = talteen;
                Heapify(smaller);
            }
        } else if (left == heapsize && taulukko[kohta].getKey() > taulukko[left].getKey()) {
            Node talteen = taulukko[kohta];
            taulukko[kohta] = taulukko[left];
            taulukko[left] = talteen;
        }

    }
}
