package tira2014;

/**
 * Luokka luo purkaja olioita, jotka saavat syötteenään purettavan koodin, sekä
 * puun, johon on tallennettu koodin purkuun tarvittava puurakennelma.
 */
public class HuffmanPurkaja {

    private String koodi;
    private Puu puu;
    private Minimikeko keko;
    private MerkkienKasittelija ascii;

    /**
     * Konstruktrissa luokalle annetaan purettava koodi sekä purkuun käytettävä
     * puu
     *
     * @param koodi purettava koodi
     * @param puu puurakennelma, josta haetaan binäärikoodin pätkää vastaava
     * merkki
     */
    public HuffmanPurkaja(String koodi, Puu puu) {
        this.koodi = koodi;
        this.ascii = new MerkkienKasittelija();
        this.puu = puu; //new Puu();
//        erotteleKeko();
        
    }

    private void erotteleKeko() {
        System.out.println(koodi);
        System.out.println(koodi.length());
        Solmu[] puuTaulukossa = new Solmu[200];
        int i = 0;
        int indeksi = 0;
        int avain = 0;
        while (true) {
            int luku = ascii.muunnaBittijonoKokonaisluvuksi(koodi.substring(i, i + 8));
            System.out.println(luku);
            if (luku == 0) { // ei pitäisi olla tiheys nollia eikä asciikoodi nollia
                break;
            } else if (i % 16 != 0) {
                puuTaulukossa[indeksi] = new Solmu(avain, ascii.asciiKoodiMerkiksi(luku));
                System.out.println(puuTaulukossa[indeksi]);
                indeksi++;
            } else {
                avain = luku;
            }
            i += 8;
        }
        keko = new Minimikeko(puuTaulukossa, indeksi - 1);

        koodi = koodi.substring(i + 8);
        taytaPuu();
    }

    /**
     * Metodissa täytetään puu rakenne minimikekoa hyväksi käyttäen.
     *
     */
    public void taytaPuu() {
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
                puu.setJuuri(parentti);
                break;
            }
        }
        lisaaSolmuilleKoodit(puu.getJuuri(), "", null);
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
     * Metodi purkaa koodin takaisin merkkijonoksi, jonka se palauttaa.
     *
     * @return Purettu koodi merkkojonona.
     */
    public String huffmanPuraKoodi() {
        String palautettava = "";
        Solmu solmussa = puu.getJuuri();
        for (int i = 0; i < koodi.length(); i++) {
            if (solmussa.getVasen() != null || solmussa.getOikea() != null) {
                if (koodi.charAt(i) == '1') {
                    solmussa = solmussa.getOikea();
                } else {
                    solmussa = solmussa.getVasen();
                }
            } else {
                palautettava += solmussa.getKirjain();
                solmussa = puu.getJuuri();
                i--;
            }
        }
        palautettava += solmussa.getKirjain();
        return palautettava;
    }
}
