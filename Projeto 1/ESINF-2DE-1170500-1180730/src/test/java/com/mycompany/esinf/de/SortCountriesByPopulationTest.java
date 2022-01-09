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
public class SortCountriesByPopulationTest {
    
    public SortCountriesByPopulationTest() {
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
     * Test of ListCountries method, of class SortCountriesByPopulation.
     */
    @Test
    public void testListCountries() throws IOException {
        System.out.println("ListCountries");
        Map<Country, Set<Country>> m = new HashMap<>();
        LoadFiles.loadPaises(m);
        String continente = "americasul";
        double populacao = 45;
        List<Country> expResult = new ArrayList<>();
        expResult.add(new Country("colombia", "americasul", 46.86, "bogota", 4.6097100, -74.0817500));
        expResult.add(new Country("brasil", "americasul", 206.12, "brasilia", -15.7797200, -47.9297200));
        List result = SortCountriesByPopulation.ListCountries(m, continente, populacao);
        assertEquals(expResult, result);
        
    }
    
}
