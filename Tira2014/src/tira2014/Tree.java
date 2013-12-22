package tira2014;

/**
 * Tree-luokka luo puu-olioita, jolla on juurisolmu.
 * 
 */
public class Tree {

    private Node root;

    public Tree(Node root) {
        this.root = root;
    }

    public Tree() {
    }
    
    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public String toString() {
        return "Tree["+root+"]";
    }

}
