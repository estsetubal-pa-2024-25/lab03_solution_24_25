package pt.pa.adts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TreeLinkedTest {
    private Tree<String> tree;
    private  Position<String> root, anchovy, tuna, mackerel, barracuda, sardine,
                              shark, dolphin, eagles, snakes, rabbits;

    @BeforeEach
    void setUp() {
        tree = new TreeLinked<>("Ecosystem");
        root = tree.root();

        anchovy= tree.insert(root, "Anchovy");

        tuna = tree.insert(root, "Tuna");
        mackerel = tree.insert(tuna, "Mackerel");
        barracuda = tree.insert(tuna, "Barracuda");
        sardine = tree.insert(barracuda, "Sardine");

        shark = tree.insert(root, "Shark");
        dolphin = tree.insert(shark, "Dolphin");

        eagles = tree.insert(root, "Eagles");
        snakes = tree.insert(eagles, "Snakes");
        rabbits = tree.insert(eagles, "Rabbits");
    }

    @Test
    void testIsEmpty()
    {
        Tree<String> empty_tree = new TreeLinked<>();
        assertTrue(empty_tree.isEmpty());

        assertFalse(tree.isEmpty());
    }

    @Test
    void testIsExternal()
    {
        assert(tree.isExternal(sardine));
        assert(tree.isExternal(mackerel));

        assertFalse(tree.isExternal(shark));
        assertFalse(tree.isExternal(root));
    }


    @Test
    void testIsRoot()
    {
        assert(tree.isRoot(tree.root()));
        assert(tree.isRoot(root));
    }


    @Test
    void testSize()
    {
        Tree<String> empty_tree = new TreeLinked<>();
        assertEquals(0,  empty_tree.size());
        assertEquals(11, tree.size());
    }

    @Test
    void testInsertShouldReturnCorrectPosition()
    {
        String item = "Catfish";
        Position<String> test = tree.insert(tuna, item);
        assertEquals(item, test.element());
    }

    @Test
    void testRemoveShouldReturnCorrectPosition()
    {
        String item = tree.remove(sardine);
        assertEquals(item, "Sardine");
    }

    @Test
    void testInsertThrowsInvalidPositionException()
    {
        Tree<String> empty_tree = new TreeLinked<>();
        assertThrows(InvalidPositionException.class, ()->empty_tree.insert(sardine, "carapau"));

        for(Position<String> item: tree.positions())
            tree.remove(item);
        assertThrows(InvalidPositionException.class, ()->tree.insert(sardine, "carapau"));
    }

    @Test
    void testDegreeIsOK()
    {
        assertEquals(4, tree.degree(root));
        assertEquals(2, tree.degree(tuna));
        assertEquals(0, tree.degree(sardine));
    }

    @Test
    void testElements()
    {
        List<String> elements = (ArrayList<String>) tree.elements();

        assertEquals(Arrays.asList(
                "Ecosystem", "Anchovy", "Tuna", "Mackerel", "Barracuda",
                "Sardine", "Shark", "Dolphin", "Eagles", "Snakes", "Rabbits"),
                elements);
    }

    @Test
    void testChildren() {
        List<Position<String>> children = (ArrayList<Position<String>>) tree.children(shark);
        assertEquals(dolphin, children.get(0));

        children = (ArrayList<Position<String>>) tree.children(tuna);
        assertEquals(mackerel, children.get(0));
        assertEquals(barracuda, children.get(1));
    }


    @Test
    void testPositions() {
        List<Position<String>> positions = (ArrayList<Position<String>>) tree.positions();
        assertTrue(positions.containsAll(
                Arrays.asList(root, anchovy, tuna, mackerel, barracuda, sardine, shark, dolphin, eagles, snakes, rabbits)));

    }

}
