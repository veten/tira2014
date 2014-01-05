package tira2014;

/**
 * Luokka luo linkitetty lista -tyyppisiä olioita. Linkitettyyn listaan voi
 * lisätä alkioita, sekä siitä voi poistaa ja hakea listan alkoita. Linkitettyä
 * listaa käytetään hajautustaulun aputietorakenteena.
 */
public class LinkitettyLista {

    private Solmu ensimmainen;

    public LinkitettyLista() {
        this.ensimmainen = null;
    }

    /**
     * Metodi lisää solmun linkitettyyn listaan.
     */
    public void lisaa(Solmu lisattava) {
        lisattava.setSeuraava(ensimmainen);
        lisattava.setEdellinen(null);
        if (ensimmainen != null) {
            Solmu seur = lisattava.getSeuraava();
            seur.setEdellinen(lisattava);
        }
        ensimmainen = lisattava;
    }

    /**
     * Metodi hakee parametrina annetun avaimen perusteella solmua listalta, ja
     * palauttaa sen. Jos avainta vastaavaa solmua ei löydy, palautetaan
     * null-arvo.
     */
    public Solmu hae(int avain) {
        Solmu kohta = ensimmainen;
        while (kohta != null && avain != kohta.getAvain()) {
            kohta = kohta.getSeuraava();
        }
        return kohta;
    }

    /**
     * Metodi poistaa parametrina annetun solmun listalta.
     */
    public void poista(Solmu solmu) {
        Solmu edell = solmu.getEdellinen();
        Solmu seur = solmu.getSeuraava();
        if (edell != null) {
            edell.setSeuraava(seur);

        } else {
            ensimmainen = seur;
        }
        if (seur != null) {
            seur.setEdellinen(edell);
        }
    }
}
