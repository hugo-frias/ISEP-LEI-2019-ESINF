/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esinf.de;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vera Pinto
 */
public class CountBordersTest {

    public CountBordersTest() {
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
     * Test of countBorders method, of class CountBorders.
     */
    @Test
    public void testCountBorders() throws IOException {
        System.out.println("countBorders");
        Map<Country, Set<Country>> m = new HashMap<>();
        LoadFiles.loadPaises(m);
        Map<Integer, Set<Country>> expResultAux = new TreeMap<>();

        Set<Country> sAux10 = new HashSet<>();
        sAux10.add(new Country("brasil", "americasul", 206.12, "brasilia", -15.7797200, -47.9297200));
        expResultAux.put(10, sAux10);

        Set<Country> sAux = new HashSet<>();
        expResultAux.put(9, sAux);
        expResultAux.put(8, sAux);
        expResultAux.put(7, sAux);
        expResultAux.put(6, sAux);
        
        Set<Country> sAux5 = new HashSet<>();
        sAux5.add(new Country("argentina", "americasul", 41.67, "buenosaires", -34.6131500, -58.3772300));
        sAux5.add(new Country("peru", "americasul", 28.22, "lima", -12.0431800, -77.0282400));
        sAux5.add(new Country("bolivia", "americasul", 9.70, "lapaz", -16.5000000, -68.1500000));

        expResultAux.put(5, sAux5);

        Set<Country> sAux4 = new HashSet<>();
        sAux4.add(new Country("colombia", "americasul", 46.86, "bogota", 4.6097100, -74.0817500));
        expResultAux.put(4, sAux4);

        Set<Country> sAux3 = new HashSet<>();
        sAux3.add(new Country("venezuela", "americasul", 31.02, "caracas", 10.4880100, -66.8791900));
        sAux3.add(new Country("suriname", "americasul", 0.04, "paramaribo", 5.8663800, -55.1668200));
        sAux3.add(new Country("guiana", "americasul", 0.07, "georgetwon", 6.8044800, -58.1552700));
        sAux3.add(new Country("chile", "americasul", 16.80, "santiago", -33.4569400, -70.6482700));
        sAux3.add(new Country("paraguai", "americasul", 6.24, "assuncao", -25.3006600, -57.6359100));
        expResultAux.put(3, sAux3);

        Set<Country> sAux2 = new HashSet<>();
        sAux2.add(new Country("uruguai", "americasul", 3.35, "montevideu", -34.9032800, -56.1881600));
        sAux2.add(new Country("equador", "americasul", 14.88, "quito", -0.2298500, -78.5249500));
        sAux2.add(new Country("guianafrancesa", "americasul", 2.88, "caiena", 4.9333300, -52.3333300));
        expResultAux.put(2, sAux2);

        expResultAux.put(1, sAux);
        
        expResultAux.put(0, sAux);
        sortByNumberOfBorders sort = new sortByNumberOfBorders();
        TreeMap<Integer, Set<Country>> expResult = new TreeMap<Integer, Set<Country>>(sort);
        expResult.putAll(expResultAux);
        Map<Integer, Set<Country>> result = CountBorders.countBorders(m);
        assertEquals(expResult, result);
    }

}
