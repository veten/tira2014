package tira2014;

/**
 * Pakkaaja-luokka luo pakkaaja-olioita, jotka saavat syötteenään pakattavan
 * tekstin Stringinä, sekä taulukkona merkkien todennäköisyydet, jos ne ovat
 * tiedossa.
 *
 */
public class Pakkaaja {

    private String syote;
    private int[] tiheys;
    private char[] kirjain;
    private Node[] solmut;
    private Minimikeko keko;
    private Tree tree;
    private int koodinPituus;
    private int merkkienMaara;
    private boolean tiheydetTarvitaan;

    /**
     * Konstruktorissa pakkaaja-oliolle annetaan pakattava syöte sekä merkkien todennäköisyystaulukko,
     * mikäli se on tiedossa. 
     *
     * @param syote pakkaajalle pakattavaksi annettava syöte
     * @param todennakoisyydet jos eri merkkien todennäköisyydet on tiedossa, annetaan ne pakkaajalle tässä, 
     * jolloin merkkien esiintymistiheyksiä ei syötteestä erikseen lasketa. Jos tätä tietoa ei ole, merkitään 
     * parametriksi null. Todennäköisyydet tulee jossain välissä muuttaa kokonaisluvuiksi esimerkiksi 
     * kertomalla jokainen tuhannella.
     *
     * Ei vielä toimi todennäköisyyksillä, vaan ainoastaan itse tiheydet laskien.
     */
    public Pakkaaja(String syote, int[] todennakoisyydet) { 
        this.syote = syote;
        if (todennakoisyydet != null) {
            this.tiheys = todennakoisyydet;
            tiheydetTarvitaan = false;
        } else {
            this.tiheys = new int[33]; // 33 = eri merkkien määrä.. tulee muuttumaan..
            tiheydetTarvitaan = true;
        }
        this.kirjain = new char[33]; // kohtien mukaan
        this.solmut = new Node[33];
        this.keko = new Minimikeko(100);
        this.tree = new Tree();
        this.merkkienMaara = syote.length();
    }

    public int getMerkkienMaara() {
        return merkkienMaara;
    }

    /**
     * Metodi laskee syotteesta eri merkkien tiheydet.
     *
     */
    public void laskeTiheydet() {
        String merkit = "abcdefghijklmnopqrstuvwxyzåäö ,.!?"; // testi merkistö..
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

    /**
     * Metodi luo solmut ja lisää minimikekoon. Lisäksi solmut lisätään erilliseen solmulistaan,
     * johon siis jokaiselle merkille muodostetaan oma solmu.
     *
     */
    public void taytaKeko() {
        if (tiheydetTarvitaan) {
            laskeTiheydet();
        }
        for (int i = 0; i < tiheys.length; i++) {
            Node solmussa = new Node(tiheys[i], kirjain[i]);
            if (tiheys[i] > 0) {
                keko.HeapInsert(solmussa);
            }
            solmut[i] = solmussa;
        }
    }

    /**
     * Metodissa täytetään puu rakenne minimikekoa hyväksi käyttäen. 
     *
     */
    public void taytaPuu() {
        while (true) {
            Node eka = keko.HeapDelMin();
            Node toka = keko.HeapDelMin();
            Node parentti = new Node(eka.getKey() + toka.getKey(), '*');
            parentti.setLeft(eka);
            parentti.setRight(toka);
            eka.setParent(parentti);
            toka.setParent(parentti);
            if (keko.getHeapsize() >= 0) {
                keko.HeapInsert(parentti);
            } else {
                tree.setRoot(parentti);
                break;
            }
        }
        lisaaSolmuilleKoodit(tree.getRoot(), "", null);
    }

    /**
     * Metodi lisää jokaiselle solmuille niiden merkkiä vastaavat uudet binäärikoodit.
     *
     * @param solmu solmu, jolle koodi lisätään
     * @param code koodi, joka solmuun lisätään
     * @param parent solmun vanhempi
     *
     */
    public void lisaaSolmuilleKoodit(Node solmu, String code, Node parent) {
        if (solmu != null) {
            lisaaSolmuilleKoodit(solmu.getLeft(), code + "0", solmu);
            lisaaSolmuilleKoodit(solmu.getRight(), code + "1", solmu);
        } else {
            parent.setCode(code.substring(0, code.length() - 1));
        }
    }

    /**
     * Metodi tulostaa rekursiivisesti kaikkien merkkien koodit, kun tätä kutsuu juurisolmulle. 
     * Tätä metodia on käytetty vain koodin testaamiseen.
     *
     * @param solmu solmu, jonka koodaus halutaan tulostaa
     */
    public void tulostaKoodit(Node solmu) {
        if (solmu != null) {
            if (solmu.getValue() != '*') {
                System.out.println(solmu.getValue() + " : " + solmu.getCode());
            }
            tulostaKoodit(solmu.getLeft());
            tulostaKoodit(solmu.getRight());
        }
    }

    /**
     * Metodi tekee varsinaisen syötteen pakkaamisen, ja palauttaa Stringinä koodauksen mukaisen
     * binäärijonon. Metodi ensin täyttää minimikeon ja sen jälkeen puun. Ja vasta tämän jälkeen
     * lukee merkkien koodit.
     *
     * @return palauttaa syötteen pakattuna binäärimuotoon
     */
    public String pakkaa() {
        taytaKeko();
        taytaPuu();
        String palautettava = "";
        for (int i = 0; i < syote.length(); i++) {
            int j = 0;
            while (j < solmut.length) {
                if (syote.charAt(i) == solmut[j].getValue()) {
                    palautettava += solmut[j].getCode();
                    break;
                }
                j++;
            }
        }
        koodinPituus = palautettava.length();
        return palautettava;
    }

    public Tree getTree() {
        return tree;
    }

    public int getKoodinPituus() {
        return koodinPituus;
    }
}
