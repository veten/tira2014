package tira2014;

/**
 * Purkaja-luokka luo purkaja olioita, jotka saavat syötteenään purettavan
 * koodin, sekä puun, johon on tallennettu koodin purkuun tarvittava
 * puurakennelma.
 */
public class Purkaja {

    private String koodi;
    private Tree tree;

    /**
     * Konstruktrissa luokalle annetaan purettava koodi sekä purkuun käytettävä puu
     *
     * @param koodi purettava koodi
     * @param tree puurakennelma, josta haetaan binäärikoodin pätkää vastaava merkki
     */
    public Purkaja(String koodi, Tree tree) {
        this.koodi = koodi;
        this.tree = tree;
    }

    /**
     * Metodi purkaa koodin takaisin merkkijonoksi, jonka se palauttaa.
     *
     * @return Purettu koodi merkkojonona.
     */
    public String puraKoodi() {
        String pal = "";
        Node solmussa = tree.getRoot();
        for (int i = 0; i < koodi.length(); i++) {
            if (solmussa.getLeft() != null || solmussa.getRight() != null) {
                if (koodi.charAt(i) == '1') {
                    solmussa = solmussa.getRight();
                } else {
                    solmussa = solmussa.getLeft();
                }
            } else {
                pal += solmussa.getValue();
                solmussa = tree.getRoot();
                i--;
            }
        }
        pal += solmussa.getValue();
        return pal;
    }
}
