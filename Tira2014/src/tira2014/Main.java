package tira2014;

import java.io.IOException;


/**
 * Main-luokka, josta ohjelma ajetaan.
 */
public class Main {

    // puun tallennus.. enter.. lzw ongelma
    
    public static void main(String[] args) throws IOException {

        LZWPakkaaja pak = new LZWPakkaaja();
        System.out.println(pak.muunnaBiteiksi(45));
    }
}
