package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import tree.EmptyTree;
import tree.NonEmptyTree;
import tree.PlaceKeysValuesInArrayLists;
import tree.PolymorphicBST;
import tree.TreeIsEmptyException;
import junit.framework.TestCase;
	
public class StudentTests extends TestCase {
	@Test
    public void PolymorphicBSTTest() {
        PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
        assertEquals(0, ptree.size());
        ptree.put(2, "Two");
        ptree.put(1, "One");
        ptree.put(3, "Three");
        ptree.put(1, "OneSecondTime");
        assertEquals(3, ptree.size());
        assertEquals("OneSecondTime", ptree.get(1));
        assertEquals("Two", ptree.get(2));
        assertEquals("Three", ptree.get(3));    
        assertEquals(null, ptree.get(8));
    }
    @Test
    public void rightRootLeftTraversalTest(){
        PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
        ptree.put(2, "Two");
        ptree.put(1, "One");
        ptree.put(3, "Three");
        ptree.put(4, "Four");
        PlaceKeysValuesInArrayLists<Integer,String> p = new PlaceKeysValuesInArrayLists<Integer,String>();
        ptree.rightRootLeftTraversal(p);
        assertEquals("[4, 3, 2, 1]", p.getKeys().toString());
    }
    @Test
    public void removeTest(){
        PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
        ptree.put(2, "Two");
        ptree.put(1, "One");
        ptree.put(3, "Three");
        ptree.put(4, "Four");
        ptree.remove(2);
        assertEquals(3, ptree.size());
        assertEquals("One", ptree.get(1));
        assertEquals("Three", ptree.get(3));
        assertEquals("Four", ptree.get(4));
        assertEquals(null, ptree.get(2));
    }
    @Test
    public void inorderTraversalTest(){
        PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
        ptree.put(2, "Two");
        ptree.put(1, "One");
        ptree.put(3, "Three");
        ptree.put(4, "Four");
        PlaceKeysValuesInArrayLists<Integer,String> p = new PlaceKeysValuesInArrayLists<Integer,String>();
        ptree.inorderTraversal(p);
        assertEquals("[1, 2, 3, 4]", p.getKeys().toString());
    }
    @Test
    public void subTreeTest(){
        PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
        ptree.put(2, "Two");
        ptree.put(1, "One");
        ptree.put(3, "Three");
        ptree.put(4, "Four");
        PolymorphicBST<Integer,String> subTree = ptree.subMap(1, 3);
        assertEquals(2, subTree.size());
        assertEquals("One", subTree.get(1));
        assertEquals("Two", subTree.get(2));
        assertEquals(null, subTree.get(3));
        assertEquals(null, subTree.get(4));
       
    }
    

   
	
}