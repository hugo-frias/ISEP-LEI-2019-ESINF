/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphbase;

import com.mycompany.esinf.de.Country;
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
public class RegistoGrafoTest {
    
    public RegistoGrafoTest() {
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
     * Test of colouring method, of class RegistoGrafo.
     */
    @Test
    public void testColouring() throws IOException {
        System.out.println("colouring");
        RegistoGrafo regs = new RegistoGrafo();
        regs.loadPaises();
        regs.colouring();
        boolean aux=true;
        boolean result=true;
        Graph<Country, String> teste = regs.getG();
        for(Country vertice : teste.vertices()){
                for(Country vert : teste.adjVertices(vertice)){
                    if(vert.getCor()==vertice.getCor()){
                        aux = false;
                    }
        }
        }
        assertEquals(result,aux);
    }

    /**
     * Test of shortestPath method, of class RegistoGrafo.
     */
    @Test
    public void testShortestPath() throws IOException {
        System.out.println("shortestPath");
        Country cOrig = new Country(-1, "portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        Country cDest = new Country(-1,"aaa", "aaa", 66.99, "aaa", 48.8566667, 2.3509871);
        RegistoGrafo regs = new RegistoGrafo();
        regs.loadPaises();
        LinkedList<String> capitaisPassadas = new LinkedList<>();
        double result;
        
        result = RegistoGrafo.shortestPath(cOrig, cDest, capitaisPassadas);
        assertTrue("Result deve ser 0 se o vertex não existir", result==0);
        
        Country cOrig1 = new Country(-1, "portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        Country cDest1 = new Country(-1,"argentina", "americasul", 41.67,"buenosaires", -34.6131500, -58.3772300);;
        result = RegistoGrafo.shortestPath(cOrig1, cDest1, capitaisPassadas);
        assertTrue("Result deve ser 0 se o path nao existir", result==0);
        
        Country cOrig2 = new Country(-1, "portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        result = RegistoGrafo.shortestPath(cOrig2, cOrig2, capitaisPassadas);
        assertTrue("Result deve ser 0 se o vertice origem for igual ao vertice final", result==0);
        
        Country cOrig3 = new Country(-1, "franca", "europa", 66.99, "paris", 48.8566667, 2.3509871 );
        Country cDest3 = new Country(-1,"alemanha", "europa", 82.8,"berlim", 52.5234051, 13.4113999);
        result = RegistoGrafo.shortestPath(cOrig3, cDest3, capitaisPassadas);
        assertEquals(result,878.0821338709682,0.0);
        
        Country cOrig4 = new Country(-1,"brasil", "americasul", 206.12, "brasilia", -15.7797200, -47.9297200);
        Country cDest4 = new Country(-1,"chile", "americasul", 16.80, "santiago", -33.4569400, -70.6482700);
        result = RegistoGrafo.shortestPath(cOrig4, cDest4, capitaisPassadas);
        assertEquals(result,4062.4296400836147,0.0);
        
        
        
    }

    /**
     * Test of shortestPathPassingByCapitals method, of class RegistoGrafo.
     */
    @Test
    public void testShortestPathPassingByCapitals() throws IOException {
        System.out.println("shortestPathPassingByCapitals");
        LinkedList<String> capitaisAPassar = new LinkedList<>();
        RegistoGrafo regs = new RegistoGrafo();
        double result;
        regs.loadPaises();
        result = regs.shortestPathPassingByCapitals("Paris", "shs", capitaisAPassar);
        assertTrue("Result deve ser 0 se a capital não existir", result==0);
        result = regs.shortestPathPassingByCapitals("Paris", "santiago", capitaisAPassar);
        assertTrue("Result deve ser 0 se o path nao existir", result==0);
        result = regs.shortestPathPassingByCapitals("Paris", "Paris", capitaisAPassar);
        assertTrue("Result deve ser 0 se a capital origem for igual à capital destino", result==0);
        result = regs.shortestPathPassingByCapitals("Paris", "Paris", capitaisAPassar);
        assertEquals(result,0,0.0);
        capitaisAPassar.add("santiago");
        result = regs.shortestPathPassingByCapitals("Lisboa", "Berlim", capitaisAPassar);
        assertEquals(result,0,0.0);
         capitaisAPassar.clear();
         
         
        capitaisAPassar.add("Paris");
        result = regs.shortestPathPassingByCapitals("Lisboa", "Berlim", capitaisAPassar);
        assertEquals(result,2433.9219715486047,0.0);
    }

    /**
     * Test of maiorCircuito method, of class RegistoGrafo.
     */
    @Test
    public void testMaiorCircuito() throws IOException {
        System.out.println("maiorCircuito");
        RegistoGrafo regs = new RegistoGrafo();
        regs.loadPaises();
        LinkedList expResult = new LinkedList<>();
        expResult.add(new Country(-1,"estonia", "europa", 1.32, "tallinn", 59.4388619, 24.7544715));
        expResult.add(new Country(-1,"letonia","europa", 1.98, "riga", 56.9465346, 24.1048525));
        expResult.add(new Country(-1,"lituania", "europa", 2.85, "vilnius", 54.6893865, 25.2800243));
        expResult.add(new Country(-1,"bielorussia","europa", 9.48, "minsk", 53.905117, 27.5611845));
        expResult.add(new Country(-1,"ucrania", "europa", 42.59, "kiev", 50.440951, 30.5271814));
        expResult.add(new Country(-1,"moldavia", "europa", 3.55, "chisinau", 47.026859, 28.841551));
        expResult.add(new Country(-1,"romenia", "europa", 19.64,"bucareste", 44.430481, 26.12298));
        expResult.add(new Country(-1,"bulgaria", "europa", 7.1, "sofia", 42.6976246, 23.3222924));
        expResult.add(new Country(-1,"macedonia", "europa", 2.07, "escopia", 41.9964600, 21.4314100));
        expResult.add(new Country(-1,"kosovo", "europa", 1.77, "pristina", 42.672421, 21.164539));
        expResult.add(new Country(-1,"montenegro", "europa", 0.62,"podgorica", 42.442575, 19.268646));
        expResult.add(new Country(-1,"albania", "europa", 2.88, "tirana", 41.33165, 19.8318));
        expResult.add(new Country(-1,"grecia", "europa", 10.76, "atenas", 37.97918, 23.716647));
        expResult.add(new Country(-1,"turquia", "europa", 79.81, "ancara", 39.9198700, 32.8542700));
        expResult.add(new Country(-1,"armenia", "europa", 3.01, "erevan", 40.1811100, 44.5136100));
        expResult.add(new Country(-1,"georgia", "europa", 3.71, "tbilisi", 41.709981, 44.792998));
        expResult.add(new Country(-1,"russia", "europa", 146.5, "moscovo", 55.755786, 37.617633));
        
        
        LinkedList result = RegistoGrafo.maiorCircuito();
        assertEquals(expResult, result);
    }

    /**
     * Test of findMaiorCircuitoDeUmPais method for europe, of class RegistoGrafo.
     */
    @Test
    public void testFindMaiorCircuitoDeUmPais1() throws IOException {
        System.out.println("encontrarMaiorCircuitoDeUmPais");
        RegistoGrafo regs = new RegistoGrafo();
        regs.loadPaises();
        Country cOrig = new Country(-1, "alemanha", "europa", 82.8, "berlim", 52.5234051, 13.4113999);
        LinkedList<Country> expResult = new LinkedList<>();
        expResult.add(cOrig);
        expResult.add(new Country(-1,"republicacheca", "europa", 10.57,"praga", 50.0878114, 14.4204598));
        expResult.add(new Country(-1,"austria", "europa", 8.77, "viena", 48.2092062, 16.3727778));
        expResult.add(new Country(-1,"eslovaquia", "europa", 5.44, "bratislava", 48.1483765, 17.1073105));
        expResult.add(new Country(-1,"hungria", "europa", 9.8, "budapeste",47.4984056,19.0407578));
        expResult.add(new Country(-1,"croacia", "europa", 4.15, "zagreb", 45.8150053, 15.9785014));
        expResult.add(new Country(-1,"eslovenia", "europa", 2.06, "liubliana", 46.0514263, 14.5059655));
        expResult.add(new Country(-1,"italia", "europa", 60.59, "roma", 41.8954656, 12.4823243));
        expResult.add(new Country(-1,"suica", "europa", 8.42, "berna", 46.9479986, 7.4481481));
        LinkedList<Country> result = RegistoGrafo.findMaiorCircuitoDeUmPais(cOrig);
        assertEquals(expResult, result);
    }
    /**
     * Test of findMaiorCircuitoDeUmPais method for americasul, of class RegistoGrafo.
     */
    @Test
    public void testfindMaiorCircuitoDeUmPais2() throws IOException {
        System.out.println("encontrarMaiorCircuitoDeUmPais");
        RegistoGrafo regs = new RegistoGrafo();
        regs.loadPaises();
        Country cOrig = new Country(-1, "brasil", "americasul", 206.12, "brasilia", -15.7797200, -47.9297200);
        LinkedList<Country> expResult = new LinkedList<>();
        expResult.add(cOrig);
        expResult.add(new Country(-1,"paraguai", "americasul", 6.24, "assuncao", -25.3006600, -57.6359100));
        expResult.add(new Country(-1,"argentina", "americasul", 41.67, "buenosaires", -34.6131500, -58.3772300));
        expResult.add(new Country(-1,"uruguai", "americasul", 3.35, "montevideu", -34.9032800, -56.1881600));
        LinkedList<Country> result = RegistoGrafo.findMaiorCircuitoDeUmPais(cOrig);
        assertEquals(expResult, result);

    }


}