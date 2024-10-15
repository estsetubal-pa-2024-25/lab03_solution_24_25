package pt.pa;

import pt.pa.adts.Position;
import pt.pa.adts.Tree;
import pt.pa.adts.TreeLinked;

/**
 *
 * @author amfs
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tree<String> tree = new TreeLinked<>("Ecosystem");
        Position<String> root = tree.root();

        tree.insert(root, "Anchovy");

        Position<String> tuna = tree.insert(root, "Tuna");
        Position<String> mackerel = tree.insert(tuna, "Mackerel");
        Position<String> barracuda = tree.insert(tuna, "Barracuda");
        Position<String> sardine = tree.insert(barracuda, "Sardine");

        Position<String> shark = tree.insert(root, "Shark");
        Position<String> dolphin = tree.insert(shark, "Dolphin");

        Position<String> eagles = tree.insert(root, "Eagles");
        Position<String> snakes = tree.insert(eagles, "Snakes");
        Position<String> rabbits = tree.insert(eagles, "Rabbits");

        System.out.println(tree.toString());
    }
}
