package tira2014;

/**
 * Node-luokka toteuttaa solmu-olioita, jotka tuntevat vanhempansa, sekä vasemman ja oikean lapsensa. 
 * Solmuun tallennetaan tieto kirjaimesta, sen koodauksesta ja avain arvo.
 */
public class Node {

    private Node left;
    private Node right;
    private Node parent;
    private int key;
    private char value;
    private String code;

    public Node(int key, Node left, Node right, Node parent, char value) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.value = value;
    }

    public Node(int key, char value) {
        this.key = key;
        this.value = value;
    }

        public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    
    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int getKey() {
        return key;
    } 

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public char getValue() {
        return value;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }
     
    @Override
    public String toString() {
        String l, r;

        if (left == null)
            l = "null";
        else
            l = left.toString();

        if (right == null)
            r = "null";
        else
            r = right.toString();

        return "Node["+key+", " + value +"]";
    }

}