package tira2014;

/**
 * Luokka luo puu-olioita, joilla on juurisolmu.
 */
public class Puu {

    private Solmu juuri;

    public Puu(Solmu juuri) {
        this.juuri = juuri;
    }

    public Puu() {
    }

    public Solmu getJuuri() {
        return juuri;
    }

    public void setJuuri(Solmu juuri) {
        this.juuri = juuri;
    }

    public String toString() {
        return "Puu[" + juuri + "]";
    }
}
