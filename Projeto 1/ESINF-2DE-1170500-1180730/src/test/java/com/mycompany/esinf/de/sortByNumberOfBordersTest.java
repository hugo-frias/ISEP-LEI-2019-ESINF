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
public class sortByNumberOfBordersTest {
    
    public sortByNumberOfBordersTest() {
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
     * Test of compare method, of class sortByNumberOfBorders.
     */
    @Ignore
    public void testCompare() {
        System.out.println("compare");
        Integer a = 9;
        Integer b = 10;
        sortByNumberOfBorders instance = new sortByNumberOfBorders();
        int expResult = 1;
        int result = instance.compare(a, b);
        assertEquals(expResult, result);
    }
    
}
