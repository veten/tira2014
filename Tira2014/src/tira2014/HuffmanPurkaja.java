package tira2014;

/**
 * Luokka luo purkaja olioita, jotka saavat syötteenään purettavan koodin, sekä
 * puun, johon on tallennettu koodin purkuun tarvittava puurakennelma.
 */
public class HuffmanPurkaja {

    private String koodi;
    private Puu puu;

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
        this.puu = puu;
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
