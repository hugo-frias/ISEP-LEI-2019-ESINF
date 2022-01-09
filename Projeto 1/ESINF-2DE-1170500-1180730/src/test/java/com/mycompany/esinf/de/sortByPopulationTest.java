/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esinf.de;

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
public class sortByPopulationTest {
    
    public sortByPopulationTest() {
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
     * Test of compare method, of class sortByPopulation.
     */
    @Ignore
    public void testCompare() {
        System.out.println("compare");
        Country a = null;
        Country b = null;
        sortByPopulation instance = new sortByPopulation();
        int expResult = 0;
        int result = instance.compare(a, b);
        assertEquals(expResult, result);
    }
    
}
