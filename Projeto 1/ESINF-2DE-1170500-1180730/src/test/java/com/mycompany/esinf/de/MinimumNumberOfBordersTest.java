/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esinf.de;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
public class MinimumNumberOfBordersTest {

    public MinimumNumberOfBordersTest() {
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
     * Test of numFronteiras method, of class MinimumNumberOfBorders.
     */
    @Test
    public void testNumFronteiras1() throws IOException {
        System.out.println("numFronteiras");
        List<Country> paisesPassados = new ArrayList<Country>();
        Map<Country, Set<Country>> m = new HashMap<>();
        LoadFiles.loadPaises(m);
        Country paisOrigem = new Country("argentina", "americasul", 41.67, "buenosaires", -34.6131500, -58.3772300);
        Country paisDestino = new Country("republicacheca", "europa", 10.57, "praga", 50.0878114, 14.4204598);
        int numFronteiras = 0;
        int expResult = -1;
        int result = MinimumNumberOfBorders.numFronteiras(paisesPassados, m, paisOrigem, paisDestino, numFronteiras);
        System.out.println("\n A argentina e republica checa não são do mesmo continente. ");
        assertEquals(expResult, result);
    }

    /**
     * Test of numFronteiras method, of class MinimumNumberOfBorders.
     */
    @Test
    public void testNumFronteiras2() throws IOException {
        System.out.println("numFronteiras");
        List<Country> paisesPassados = new ArrayList<Country>();
        Map<Country, Set<Country>> m = new HashMap<>();
        LoadFiles.loadPaises(m);
        Country paisOrigem = new Country("argentina", "americasul", 41.67, "buenosaires", -34.6131500, -58.3772300);
        Country paisDestino = new Country("brasil", "americasul", 206.12, "brasilia", -15.7797200, -47.9297200);
        int numFronteiras = 0;
        int expResult = 1;
        int result = MinimumNumberOfBorders.numFronteiras(paisesPassados, m, paisOrigem, paisDestino, numFronteiras);
        System.out.println("\n O número minimo de fronteiras entre argentina e brasil é de: "+numFronteiras);
        assertEquals(expResult, result);
    }

    /**
     * Test of distance method, of class MinimumNumberOfBorders.
     */
    @Test
    public void testDistance() {
        System.out.println("distance");
        double lat1 = 48.8566667;
        double lon1 = 2.3509871;
        double lat2 = 41.709981;
        double lon2 = 44.792998;
        double expResult = 2092.411821511874;
        double result = MinimumNumberOfBorders.distance(lat1, lon1, lat2, lon2);
        assertEquals(expResult, result, 0.0);
    }

}
