/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esinf.de;

import java.util.Map;
import java.util.Set;
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
public class LoadFilesTest {
    
    public LoadFilesTest() {
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
     * Test of loadPaises method, of class LoadFiles.
     */
    @Ignore
    public void testLoadPaises() throws Exception {
        System.out.println("loadPaises");
        Map<Country, Set<Country>> m = null;
        LoadFiles.loadPaises(m);
    }

    /**
     * Test of loadFronteiras method, of class LoadFiles.
     */
    @Ignore
    public void testLoadFronteiras() throws Exception {
        System.out.println("loadFronteiras");
        String path = "";
        Country country = null;
        Set<Country> scountries = null;
        Set<Country> expResult = null;
        Set<Country> result = LoadFiles.loadFronteiras(path, country, scountries);
        assertEquals(expResult, result);
    }
    
}
