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
public class CountryTest {
    
    public CountryTest() {
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
     * Test of getNome method, of class Country.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        Country instance = new Country("brasil", "americasul", 206.12, "brasilia", -15.7797200, -47.9297200);
        String expResult = "brasil";
        String result = instance.getNome();
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of getContinente method, of class Country.
     */
    @Test
    public void testGetContinente() {
        System.out.println("getContinente");
        Country instance = new Country("brasil", "americasul", 206.12, "brasilia", -15.7797200, -47.9297200);
        String expResult = "americasul";
        String result = instance.getContinente();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getPopulacao method, of class Country.
     */
    @Test
    public void testGetPopulacao() {
        System.out.println("getPopulacao");
        Country instance = new Country("brasil", "americasul", 206.12, "brasilia", -15.7797200, -47.9297200);
        double expResult = 206.12;
        double result = instance.getPopulacao();
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of getLatitude method, of class Country.
     */
    @Test
    public void testGetLatitude() {
        System.out.println("getLatitude");
        Country instance = new Country("brasil", "americasul", 206.12, "brasilia", -15.7797200, -47.9297200);
        double expResult = -15.7797200;
        double result = instance.getLatitude();
        assertEquals(expResult, result, 0.0);
        
    }


    /**
     * Test of getLongitude method, of class Country.
     */
    @Test
    public void testGetLongitude() {
        System.out.println("getLongitude");
        Country instance = new Country("brasil", "americasul", 206.12, "brasilia", -15.7797200, -47.9297200);
        double expResult = -47.9297200;
        double result = instance.getLongitude();
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of hashCode method, of class Country.
     */
    @Ignore
    public void testHashCode() {
        System.out.println("hashCode");
        Country instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of equals method, of class Country.
     */
    @Ignore
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Country instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of toString method, of class Country.
     */
    @Ignore
    public void testToString() {
        System.out.println("toString");
        Country instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
       
    }
    
}
