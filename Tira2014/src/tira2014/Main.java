package tira2014;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main-luokka, josta ohjelma ajetaan.
 */
public class Main {

    private static Scanner lukija = new Scanner(System.in);

    private static String kysyJotain(String viesti) {
        System.out.println(viesti);
        return lukija.nextLine();
    }

    private static String ensimmainenKysymys(TiedostonKasittelija kasittelija) {
        String syote = null;
        System.out.println("Valitse 1, jos haluat itse kirjoittaa pakattavan syötteen tai 2, jos"
                + "haluat valita pakattavan tiedoston");
        int vastaus = Integer.parseInt(lukija.nextLine());
        if (vastaus == 1) {
            System.out.println("Kirjoita pakattava syöte:");
            syote = lukija.nextLine();

        } else if (vastaus == 2) {
            System.out.println("Kirjoita pakattavan tiedoston hakemistopolku:");
            String polku = lukija.nextLine();
            syote = kasittelija.lueTiedosto(polku + ".txt");
        }
        return syote;
    }

    public static void main(String[] args) throws IOException { 

        // Tämä käyttöliittymä jäi vähän kesken, tuli tehtyä kiireellä, eli ei varmaankaan ihan toimi toivotusti.
        
        HuffmanPakkaaja tuntematonHuffmanPakkaaja = new HuffmanPakkaaja(null);
        TiedostonKasittelija kasittelija = new TiedostonKasittelija();
        System.out.println("Tervetuloa käsittelemään tiedostoja!");
        while (true) {
            System.out.println("Valitse toiminto: 1 = tiedoston pakkaus, 2 = pakkauksen purku, "
                    + "-1 = Ohjelman lopetus:");
            int vastaus = Integer.parseInt(lukija.nextLine());
            if (vastaus == -1) {
                break;
            } else if (vastaus == 1) {


                System.out.println("Valitse pakkastapa:");
                System.out.println("1 = Huffman pakkaus");
                System.out.println("2 = Lempel-Ziv-Welch pakkaus");
                int vastausPakkaukseen = Integer.parseInt(lukija.nextLine());
                if (vastausPakkaukseen == 1) {
                    String pakattavaSyote = ensimmainenKysymys(kasittelija);
                    String tallennusPolku = kysyJotain("Kirjoita hakemistopolku, johon pakattu "
                            + "tiedosto / kirjoitettu teksti tallennetaan");
                    int[] pakattu = tuntematonHuffmanPakkaaja.huffmanPakkaa(pakattavaSyote);
                    kasittelija.tallennaTavuittain(tallennusPolku + ".txt", pakattu);
                    System.out.println("Pakkaus suoritettu.");
                } else if (vastausPakkaukseen == 2) {
                    LZWPakkaaja lzw = new LZWPakkaaja();
                    String pakattavaSyote = ensimmainenKysymys(kasittelija);
                    String tallennusPolku = kysyJotain("Kirjoita hakemistopolku, johon pakattu "
                            + "tiedosto / kirjoitettu teksti tallennetaan");
                    int[] pakattu = lzw.lZWPakkaa(pakattavaSyote);
                    kasittelija.tallennaTavuittain(tallennusPolku + ".txt", pakattu);
                    System.out.println("Pakkaus suoritettu.");
                } else {
                    System.out.println("Tuntematon komento.");
                }
            } else if (vastaus == 2) {
                System.out.println("Valitse purkutapa (Täytyy olla sama kuin pakkaustapa, muutoin purku ei onnistu):");
                System.out.println("1 = Huffman purku");
                System.out.println("2 = Lempel-Ziv-Welch purku");
                int vastausPurkuun = Integer.parseInt(lukija.nextLine());
                String polku = kysyJotain("Kirjoita hakemistopolku, josta purettava tiedosto löytyy.");
                if (vastausPurkuun == 1) {
                    HuffmanPurkaja huffman;
                    String kieli = kysyJotain("Anna kieli, jolla pakkaus on suoritettu: "
                            + "0 = tuntematon, 1 = suomi, 2 = englanti");
                    if (kieli.equals("0")) {
                        huffman = new HuffmanPurkaja(kasittelija.lueTiedostoTavuittain(polku + ".txt"), tuntematonHuffmanPakkaaja.getTree());
                    } else if (kieli.equals("1")) {
                        huffman = new HuffmanPurkaja(kasittelija.lueTiedostoTavuittain(polku + ".txt"), null);//suomiPuu());
                    } else {
                        huffman = new HuffmanPurkaja(kasittelija.lueTiedostoTavuittain(polku + ".txt"), null);//englantiPuu());
                    }
                    String purettu = huffman.huffmanPuraKoodi();
                } else if (vastausPurkuun == 2) {
                    LZWPurkaja purkaja = new LZWPurkaja(kasittelija.lueTiedostoTavuittain(polku + ".txt"));
                    String purettu = purkaja.lZWPuraKoodi();
                    System.out.println(purettu);
                }
            }
        }
    }
}
