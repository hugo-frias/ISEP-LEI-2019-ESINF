/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BSTBase;

import ProjectClasses.Country;
import ProjectClasses.Pair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Vera Pinto
 */
public class TreeAlgorithmsTest {
    
    public TreeAlgorithmsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of loadFicheiros method, of class TreeAlgorithms.
     */
    @Test
    public void testLoadFicheiros() throws Exception {
        System.out.println("loadFicheiros");
        String fileNameCountry = "paisesTeste1";
        String fileNameBorders = "fronteirasTeste1";
        TreeAlgorithms instance = new TreeAlgorithms();
        BST aux = new BST();
        Country arg = new Country("argentina", "americasul", 41.67, "buenosaires", -34.6131500, -58.3772300);
        Country bol = new Country("bolivia", "americasul", 9.70, "lapaz", -16.5000000, -68.1500000);
        Country chi = new Country("chile", "americasul", 16.80, "santiago", -33.4569400, -70.6482700);
        Country para = new Country("paraguai", "americasul", 6.24, "assuncao", -25.3006600, -57.6359100);
        Country uru = new Country("uruguai", "americasul", 3.35, "montevideu", -34.9032800, -56.1881600);
        
        Pair p1 = new Pair(arg);
        Pair p2 = new Pair(bol);
        Pair p3 = new Pair(chi);
        Pair p4 = new Pair(para);
        Pair p5 = new Pair(uru);
        
        p1.addFronteira(uru);
        p1.addFronteira(bol);
        p1.addFronteira(chi);
        p1.addFronteira(para);
        
        p2.addFronteira(arg);
        p2.addFronteira(chi);
        p2.addFronteira(para);
        
        p3.addFronteira(arg);
        p3.addFronteira(bol);
        
        p4.addFronteira(arg);
        p4.addFronteira(bol);
        
        p5.addFronteira(arg);
        
        aux.insert(p5);
        aux.insert(p1);
        aux.insert(p2);
        aux.insert(p3);
        aux.insert(p4);
        
        Iterable expResult = aux.inOrder();
        Iterable result = instance.loadFicheiros(fileNameCountry, fileNameBorders).inOrder();
        assertEquals(expResult, result);
    }

    /**
     * Test of OrderedCountries method, of class TreeAlgorithms.
     */
    @Test
    public void testOrderedCountries() throws IOException {
        System.out.println("OrderedCountries");
        String continent = "americasul";
        TreeAlgorithms instance = new TreeAlgorithms();
        ArrayList expResult = new ArrayList<>();
        String fileNameCountry = "paisesTeste1";
        String fileNameBorders = "fronteirasTeste1";
        instance.loadFicheiros(fileNameCountry, fileNameBorders);
        
        Country arg = new Country("argentina", continent, 41.67, "buenosaires", -34.6131500, -58.3772300);
        Country bol = new Country("bolivia", continent, 9.70, "lapaz", -16.5000000, -68.1500000);
        Country chi = new Country("chile", continent, 16.80, "santiago", -33.4569400, -70.6482700);
        Country para = new Country("paraguai", continent, 6.24, "assuncao", -25.3006600, -57.6359100);
        Country uru = new Country("uruguai", continent, 3.35, "montevideu", -34.9032800, -56.1881600);
        
        Pair p1 = new Pair(arg);
        Pair p2 = new Pair(bol);
        Pair p3 = new Pair(chi);
        Pair p4 = new Pair(para);
        Pair p5 = new Pair(uru);
        
        p1.addFronteira(uru);
        p1.addFronteira(bol);
        p1.addFronteira(chi);
        p1.addFronteira(para);
        
        p2.addFronteira(arg);
        p2.addFronteira(chi);
        p2.addFronteira(para);
        
        p3.addFronteira(arg);
        p3.addFronteira(bol);
        
        p4.addFronteira(arg);
        p4.addFronteira(bol);
        
        p5.addFronteira(arg);
        
        expResult.add(p1);
        expResult.add(p2);
        expResult.add(p3);
        expResult.add(p4);
        expResult.add(p5);
        
        ArrayList result = (ArrayList) instance.OrderedCountries(continent);
        assertEquals(expResult, result);
    }

    /**
     * Test of loadTDTree method, of class TreeAlgorithms.
     */
    @Test
    public void testLoadTDTree() throws IOException {
        System.out.println("loadTDTree");
        TreeAlgorithms instance = new TreeAlgorithms();
        String continent = "americasul";
        TwoDTree aux = new TwoDTree();
        BST bst1 = instance.loadFicheiros("paisesTeste1", "fronteirasTeste1");
        
        Country arg = new Country("argentina", continent, 41.67, "buenosaires", -34.6131500, -58.3772300);
        Country bol = new Country("bolivia", continent, 9.70, "lapaz", -16.5000000, -68.1500000);
        Country chi = new Country("chile", continent, 16.80, "santiago", -33.4569400, -70.6482700);
        Country para = new Country("paraguai", continent, 6.24, "assuncao", -25.3006600, -57.6359100);
        Country uru = new Country("uruguai", continent, 3.35, "montevideu", -34.9032800, -56.1881600);
        
        Pair p1 = new Pair(arg);
        Pair p2 = new Pair(bol);
        Pair p3 = new Pair(chi);
        Pair p4 = new Pair(para);
        Pair p5 = new Pair(uru);
        
        p1.addFronteira(uru);
        p1.addFronteira(bol);
        p1.addFronteira(chi);
        p1.addFronteira(para);
        
        p2.addFronteira(arg);
        p2.addFronteira(chi);
        p2.addFronteira(para);
        
        p3.addFronteira(arg);
        p3.addFronteira(bol);
        
        p4.addFronteira(arg);
        p4.addFronteira(bol);
        
        p5.addFronteira(arg);
        
        aux.insert(p1);
        aux.insert(p2);
        aux.insert(p3);
        aux.insert(p4);
        aux.insert(p5);
        
        Iterable expResult = aux.inOrder();
        Iterable result = instance.loadTDTree().inOrder();
        assertEquals(expResult, result);
    }

    /**
     * Test of findCountryByCoordenates method, of class TreeAlgorithms.
     */
    @Test
    public void testFindCountryByCoordenates1() throws IOException {
        System.out.println("findCountryByCoordenates");
        double lat = -34.6131500;
        double log = -58.3772300;
        TreeAlgorithms instance = new TreeAlgorithms();
        BST bst1 = instance.loadFicheiros("paises", "fronteiras");
        instance.loadTDTree();
        Country expResult = new Country("argentina", "americasul", 41.67, "buenosaires", -34.6131500, -58.3772300);
        Country result = instance.findCountryByCoordenates(lat, log);
        assertEquals(expResult, result);
    }
    /**
     * Test of findCountryByCoordenates method, of class TreeAlgorithms.
     */
    @Test
    public void testFindCountryByCoordenates2() throws IOException {
        System.out.println("findCountryByCoordenates");
        double lat = 38.7071631;
        double log = -9.135517;
        TreeAlgorithms instance = new TreeAlgorithms();
        BST bst1 = instance.loadFicheiros("paises", "fronteiras");
        instance.loadTDTree();
        Country expResult = new Country("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        Country result = instance.findCountryByCoordenates(lat, log);
        assertEquals(expResult, result);
    }
    /**
     * Test of findNearestCountry method, of class TreeAlgorithms.
     */
    @Test
    public void testFindNearestCountry1() throws IOException {
        System.out.println("findNearestCountry");
        double lat = -33.6131500;
        double log = -59.3772300;
        TreeAlgorithms instance = new TreeAlgorithms();
        BST bst1 = instance.loadFicheiros("paises", "fronteiras");
        instance.loadTDTree();
        Country expResult = new Country("argentina", "americasul", 41.67, "buenosaires", -34.6131500, -58.3772300);
        Country result = instance.findNearestCountry(lat, log);
        assertEquals(expResult, result);
    }
    /**
     * Test of findNearestCountry method, of class TreeAlgorithms.
     */
    @Test
    public void testFindNearestCountry2() throws IOException {
        System.out.println("findNearestCountry");
        double lat = 40.7071631;
        double log = -3.135517;
        TreeAlgorithms instance = new TreeAlgorithms();
        BST bst1 = instance.loadFicheiros("paises", "fronteiras");
        instance.loadTDTree();
        Country expResult = new Country("espanha", "europa", 46.53, "madrid", 40.4166909, -3.7003454);
        Country result = instance.findNearestCountry(lat, log);
        assertEquals(expResult, result);
    }

    /**
     * Test of findCountriesByArea method, of class TreeAlgorithms.
     */
    @Test
    public void testFindCountriesByArea() throws IOException {
        System.out.println("findCountriesByArea");
        double latMax = -15.00;
        double latMin = -35.00;
        double logMax = -55.00;
        double logMin = -59.00;
        TreeAlgorithms instance = new TreeAlgorithms();
        BST bst1 = instance.loadFicheiros("paisesTeste1", "fronteirasTeste1");
        instance.loadTDTree();
        LinkedList<Pair> expResult = new LinkedList<Pair>();
        Country arg = new Country("argentina", "americasul", 41.67, "buenosaires", -34.6131500, -58.3772300);
        Country bol = new Country("bolivia", "americasul", 9.70, "lapaz", -16.5000000, -68.1500000);
        Country chi = new Country("chile", "americasul", 16.80, "santiago", -33.4569400, -70.6482700);
        Country para = new Country("paraguai", "americasul", 6.24, "assuncao", -25.3006600, -57.6359100);
        Country uru = new Country("uruguai", "americasul", 3.35, "montevideu", -34.9032800, -56.1881600);
        
           Pair p1 = new Pair(arg);
        Pair p2 = new Pair(bol);
        Pair p3 = new Pair(chi);
        Pair p4 = new Pair(para);
        Pair p5 = new Pair(uru);
        
        p1.addFronteira(uru);
        p1.addFronteira(bol);
        p1.addFronteira(chi);
        p1.addFronteira(para);
        
        p2.addFronteira(arg);
        p2.addFronteira(chi);
        p2.addFronteira(para);
        
        p3.addFronteira(arg);
        p3.addFronteira(bol);
        
        p4.addFronteira(arg);
        p4.addFronteira(bol);
        
        p5.addFronteira(arg);
        
        expResult.add(p1);
        expResult.add(p4);
        expResult.add(p5);
        
        LinkedList<Country> result = instance.findCountriesByArea(latMax, latMin, logMax, logMin);
        assertEquals(expResult, result);
        
    }


    /**
     * Test of listOfBorders method, of class TreeAlgorithms.
     */
    @Test
    public void testListOfBorders() throws IOException {
        System.out.println("listOfBorders");
        String nome = "";
        TreeAlgorithms instance = new TreeAlgorithms();
        BST bst1 = instance.loadFicheiros("paisesTeste1", "fronteirasTeste1");
        instance.loadTDTree();
        LinkedList expResult = new LinkedList<>();
        
        Country bol = new Country("bolivia", "americasul", 9.70, "lapaz", -16.5000000, -68.1500000);
        Country chi = new Country("chile", "americasul", 16.80, "santiago", -33.4569400, -70.6482700);
        Country para = new Country("paraguai", "americasul", 6.24, "assuncao", -25.3006600, -57.6359100);
        Country uru = new Country("uruguai", "americasul", 3.35, "montevideu", -34.9032800, -56.1881600);
        expResult.add(uru);
        expResult.add(bol);
        expResult.add(chi);
        expResult.add(para);
       
        
        LinkedList result = instance.listOfBorders("argentina");
        
        assertEquals(expResult, result);
    }

  
    
}
