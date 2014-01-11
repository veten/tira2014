package tira2014;

/**
 * Luokka toteuttaa solmu-olioita, jotka voivat tuntea vanhempansa, sekä
 * vasemman ja oikean lapsensa. Lisäksi linkitettyä listaa varten solmu voi
 * tuntea edellisen ja seuraavan solmun. Solmuun tallennetaan tieto kirjaimesta,
 * kirjaimen asciikoodista, sen Huffman-koodauksesta ja avain arvo.
 */
public class Solmu {

    private Solmu vasen;
    private Solmu oikea;
    private Solmu vanhempi;
    private int avain;
    private char kirjain;
    private int kirjaimenAsciiKoodi;
    private String huffmanKoodi;
    private Solmu seuraava;
    private Solmu edellinen;

    public Solmu(int avain, Solmu vasen, Solmu oikea, Solmu vanhempi, char kirjain) {
        this.avain = avain;
        this.vasen = vasen;
        this.oikea = oikea;
        this.vanhempi = vanhempi;
        this.kirjain = kirjain;
        this.kirjaimenAsciiKoodi = kirjainASciiKoodiksi();
    }

    public Solmu(int avain, char kirjain) {
        this.avain = avain;
        this.kirjain = kirjain;
        this.kirjaimenAsciiKoodi = kirjainASciiKoodiksi();
    }

    public Solmu getSeuraava() {
        return seuraava;
    }

    public void setSeuraava(Solmu seuraava) {
        this.seuraava = seuraava;
    }

    public Solmu getEdellinen() {
        return edellinen;
    }

    public void setEdellinen(Solmu edellinen) {
        this.edellinen = edellinen;
    }

    public void setHuffmanKoodi(String huffmanKoodi) {
        this.huffmanKoodi = huffmanKoodi;
    }

    public String getHuffmanKoodi() {
        return huffmanKoodi;
    }

    public Solmu getVasen() {
        return vasen;
    }

    public Solmu getOikea() {
        return oikea;
    }

    public int getAvain() {
        return avain;
    }

    public void setVasen(Solmu vasen) {
        this.vasen = vasen;
    }

    public void setOikea(Solmu oikea) {
        this.oikea = oikea;
    }

    public char getKirjain() {
        return kirjain;
    }

    public void setAvain(int avain) {
        this.avain = avain;
    }

    public void setVanhempi(Solmu vanhempi) {
        this.vanhempi = vanhempi;
    }

    public Solmu getVanhempi() {
        return vanhempi;
    }

    public int getKirjaimenASciiKoodi() {
        return kirjaimenAsciiKoodi;
    }

    public void setKirjain(char kirjain) {
        this.kirjain = kirjain;
    }

    private int kirjainASciiKoodiksi() {
        return new Ascii().merkkiAsciiKoodiksi(kirjain);
    }

    @Override
    public String toString() {
        return "Solmu[avain: " + avain + ", kirjain: " + kirjain + ", ascii: " + kirjaimenAsciiKoodi + "]";
    }
}