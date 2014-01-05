package tira2014;

/**
 * Luokasta voi luoda hajautustauluja, joihin voidaan lisätä alkioita ja josta
 * voi hakea tehokkaasti alkioita. Hajautustauluja käytetään pakkausalgoritmien
 * aputietorakenteina.
 */
public class Hajautustaulu {

    private LinkitettyLista[] taulukko;

    /**
     * Konstruktorissa alustetaan taulukko, joka sisältää linkitettyjä listoja.
     * Taulukon koko olisi hyvä olla alkuluku? Suhteessa avainten määrään?
     *
     * @param koko hajautustaulukon koko
     */
    public Hajautustaulu(int koko) {
        taulukko = new LinkitettyLista[koko];
    }

    /**
     * Metodi lisää hajautustauluun solmun. Hajautukseen on käytetty jakojäännös
     * menetelmää.
     *
     * @param solmu hajautustauluun lisättävä solmu
     */
    public void lisays(Solmu solmu) {
        int kohta = solmu.getAvain() % taulukko.length;
        if (taulukko[kohta] == null) {
            taulukko[kohta] = new LinkitettyLista();
        }
        taulukko[kohta].lisaa(solmu);
    }

    /**
     * Metodi etsii hajautustaulusta solmua avaimen perusteella.
     *
     * @param avain etsittävän solmun avain
     *
     * @return palauttaa etsityn solmun tai null arvon, jos kyseistä solmua ei
     * löydy
     */
    public Solmu etsinta(int avain) {
        int kohta = avain % taulukko.length;
        if (taulukko[kohta] != null) {
            return taulukko[kohta].hae(avain);
        }
        return null;
    }

    /**
     * Metodi poistaa hajautustaulusta solmun.
     *
     * @param solmu hajautustaulusta poistettava solmu
     */
    public void poisto(Solmu solmu) {
        int kohta = solmu.getAvain() % taulukko.length;
        if (taulukko[kohta] != null) {
            taulukko[kohta].poista(solmu);
        }
    }
}
