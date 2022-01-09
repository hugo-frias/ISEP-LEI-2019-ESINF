/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphbase;

import com.mycompany.esinf.de.Country;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import static javafx.scene.input.KeyCode.V;

/**
 *
 * @author Hugo
 */
public class RegistoGrafo {
    private static Graph<Country, String> g;

    

    

    public RegistoGrafo() {
        g = new Graph<Country, String>(false);
    }

    public  Graph<Country, String> getG() {
        return g;
    }
    
    
    
     public void loadPaises() throws FileNotFoundException, IOException {
 
        Set<Country> scountries = new HashSet<>();
        File directory = new File("./");
        String path = directory.getAbsolutePath();
        path = path.substring(0, path.length() - 1) + "\\src\\main\\java\\com\\mycompany\\esinf\\de\\resources\\";
        String fileNamePais = path + "paises.txt";
        List<String> list = Files.lines(Paths.get(fileNamePais)).collect(Collectors.toList());
        for (String list1 : list) {
            if (list1.length() > 0) {
                String temp[] = list1.split(",");
                Country country = new Country(-1, temp[0].trim(), temp[1].trim(), Double.parseDouble(temp[2]), temp[3].trim(),
                        Double.parseDouble(temp[4]), Double.parseDouble(temp[5]));
                g.insertVertex(country);
                scountries.add(country);

            }
        }

        String fileNameFronteira = path + "fronteiras.txt";
        List<String> listF = Files.lines(Paths.get(fileNameFronteira)).collect(Collectors.toList());
        for (String list2 : listF) {
            if (list2.length() > 0) {
                String temp2[] = list2.split(",");
                Country a = getCountry(temp2[0].trim(), scountries);
                Country b = getCountry(temp2[1].trim(), scountries);
                g.insertEdge(a, b, null, distance(a.getLatitude(), a.getLongitude(), b.getLatitude(), b.getLongitude()));
            }
        }

    }

    
 //ex2   
public void colouring() {
        //array com o status de cada vertex (-1 caso estejam por colorir)
        int[] resultado = new int[g.numVertices()];
        //por predefinição, todos os vetores estão por colorir
        Arrays.fill(resultado, -1);
        resultado[0]=0;
        getCountryByKey(0).setCor(0);
        // array temporario para guardar as cores disponiveis. 
        //Os valores ficam falsos caso algum dos vetores adjacentes 
        //do vetor em questão esteja colorido
        boolean disponivel[] = new boolean[g.numVertices()];

        // Inicialmente, todas as cores estão disponiveis
        Arrays.fill(disponivel, true);

        // ciclo para atribuir cores aos vertices
       
        for(Country vertice : g.vertices()){
            if (vertice != getCountryByKey(0)){
               Iterable<Country> vAdjs = (Iterable<Country>) g.adjVertices(vertice);
            
                 //verifica as cores dos vertices adjacentes e mete-as a falso
            for (Country vertex : vAdjs) {
                
                int j = g.getKey(vertex);
                if (resultado[j] != -1) {
                    disponivel[resultado[j]] = false;
                }
            }
                //percorre o vetor das cores até encontrar uma disponivel
                int cor;
                for (cor = 0; cor < g.numVertices(); cor++) {
                    if (disponivel[cor]) {
                        break;
                    }
                }
                //preenche o vertex com a tal cor
                vertice.setCor(cor);
                resultado[g.getKey(vertice)] = cor;

                // Reset aos valores para a proxima iteração
                Arrays.fill(disponivel, true); 
            }
        }
        
    }
    //ex3 
public static double shortestPath(Country cOrig, Country cDest, LinkedList<String> capitaisPassadas) {
        if (!g.validVertex(cOrig) || !g.validVertex(cDest)) {
            return 0;
        }
        int nVerts = g.numVertices();
        boolean[] visited = new boolean[nVerts];
        int[] pathKeys = new int[nVerts];
        double[] dist = new double[nVerts];
        Country[] vertices = (Country[]) g.allkeyVerts();
        for (int i = 0; i < nVerts; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }
        shortestPathLength(cOrig, vertices, visited, pathKeys, dist);


        double lengthPath = dist[g.getKey(cDest)];

        if (lengthPath != Double.MAX_VALUE) {
            getPath(cOrig, cDest, vertices, pathKeys, capitaisPassadas);
            return lengthPath;
        }
        return 0;
    }

    private static void shortestPathLength(Country cOrig, Country[] vertices, boolean[] visited, int[] pathKeys, double[] dist) {
        Country aux = null;
        int key = 0;
        for (Country c : vertices) {
            if (c.getCapital().equalsIgnoreCase(cOrig.getCapital())) {
                key = g.getKey(c);
                aux = c;
            }
        }
        dist[key] = 0;
        while (key != -1) {
            visited[key] = true;
            for (Country c1 : g.adjVertices(aux)) {
                int adjKey = g.getKey(c1);
                if (!visited[adjKey] && dist[adjKey] > dist[key] + g.getEdge(aux, c1).getWeight()) {
                    dist[adjKey] = dist[key] + g.getEdge(aux, c1).getWeight();
                    pathKeys[adjKey] = key;
                }
            }
            key = getVertMinDist(dist, visited);
            for (Country vert : g.vertices()) {
                if (g.getKey(vert) == key) {
                    aux = vert;
                }
            }
        }
    }

    private static int getVertMinDist(double[] dist, boolean[] visited) {
        int key = 0;
        double min = dist[0];
        for (key = 0; key < visited.length; key++) {
            if (!visited[key] && dist[key] < min) {
                min = dist[key];
                return key;
            }
        }
        return -1;
    }
   
    private static void getPath(Country cOrig, Country cDest, Country[] vertices, int[] pathKeys, LinkedList<String> capitaisPassadas) {
        if(!cOrig.equals(cDest)) {
            capitaisPassadas.push(cDest.getCapital());
            int vKey = g.getKey(cDest);
            int prevVKey = pathKeys[vKey];
            cDest = vertices[prevVKey];
            getPath(cOrig, cDest, vertices, pathKeys, capitaisPassadas);
        } else {
            capitaisPassadas.push(cOrig.getCapital());
        }
    }
    //ex 4
    
     public static double shortestPathPassingByCapitals(String capitalOrig, String capitalDest, LinkedList<String> capitaisAPassar) {
        double distanciaTotal = 0;
        Country cOrig = getCountryByCapital(capitalOrig);
        Country cDest = getCountryByCapital(capitalDest);
        LinkedList<Country> paisesAPassar = convertStringListToCountries(capitaisAPassar);
        return findShortestPathPassingByCapitals(cOrig,cDest,paisesAPassar,distanciaTotal);
    }
    private static LinkedList<Country> convertStringListToCountries(LinkedList<String> path) {
        LinkedList<Country> finalPath = new LinkedList<>();
        for (String s : path){
            for (Country c : g.vertices()){            
             if(c.getCapital().equalsIgnoreCase(s)){
                finalPath.push(c);
                break;
            }
        }        
        }
        return finalPath;
    }
    public static double findShortestPathPassingByCapitals(Country cOrig, Country cDest, LinkedList<Country> paisesAPassar, double distanciaTotal) {
        
        ArrayList<Double> distanciasSorted = new ArrayList<>();
        LinkedList<String> pathFinal = new LinkedList<>();
        if (paisesAPassar.isEmpty()) {
            double distAux = shortestPath(cOrig, cDest, pathFinal);
            if (distAux == 0) {
                return 0;
            } else {
                return distanciaTotal + distAux;
            }
        }
        ArrayList<Double> distanciasIndex = orderDistancias(paisesAPassar, cOrig, distanciasSorted);
        for (int i = 0; i < distanciasIndex.size(); i++) {
            if (distanciasSorted.get(0).equals(distanciasIndex.get(i))) {
                distanciaTotal = distanciaTotal + distanciasSorted.get(0);
                cOrig = paisesAPassar.get(i);
                paisesAPassar.remove(i);
                break;
            }
        }
        return findShortestPathPassingByCapitals(cOrig,cDest,paisesAPassar,distanciaTotal);
    }

    public static ArrayList orderDistancias(LinkedList<Country> paisesAPassar, Country cOrig, ArrayList<Double> distanciasSorted) {
        double dist;
        LinkedList<String> pathAux = new LinkedList<>();
        ArrayList<Double> distanciasIndex = new ArrayList<>();
        for (int i = 0; i < paisesAPassar.size(); i++) {
            dist = shortestPath(cOrig, paisesAPassar.get(i), pathAux);
            distanciasIndex.add(dist);
            distanciasSorted.add(dist);
            pathAux.clear();
        }
        Collections.sort(distanciasSorted);
        return distanciasIndex;
    }
    
    //ex5
         
     
    
     public static LinkedList maiorCircuito(){
         LinkedList<Country> maiorCircuito = new LinkedList<>();         
         Country[] paises = g.allkeyVerts();
         int maior = 0;
         for (Country c : paises){             
             LinkedList<Country> maiorCircuitoAux = findMaiorCircuitoDeUmPais(c);
             if(maiorCircuitoAux!=null){
             if (maiorCircuitoAux.size()>maior){
                 maiorCircuito = maiorCircuitoAux; 
                 maior = maiorCircuitoAux.size();
             }
             }
         }        
         return maiorCircuito;
         
     }
     public static LinkedList<Country> findMaiorCircuitoDeUmPais(Country cOrig){
         LinkedList<Country> maiorCircuito = new LinkedList<>();
         boolean[] visited = new boolean[g.numVertices()]; 
         maiorCircuito.add(cOrig);
         visited[g.getKey(cOrig)] = true;         
         LinkedList<Country> maiorCircuitoAux = findMaiorCircuito(cOrig, visited, maiorCircuito);
         if(maiorCircuitoAux.size()>0){
            maiorCircuito = encontrarUltimaFronteira(maiorCircuitoAux, cOrig);            
         }
         return maiorCircuito;        
     }
     
     public static LinkedList<Country> findMaiorCircuito(Country cOrig, boolean[] visited, LinkedList<Country> maiorCircuito){
         Iterable fronteiras =  g.adjVertices(cOrig); 
         double minDist = Double.MAX_VALUE;
         Country nextCountry = null;
         int count = 0;
         int count2 = 0;
         for( Country cAdj : g.adjVertices(cOrig)){
             count2++;
             if (visited[g.getKey(cAdj)] == false){
                 count++;
                 break;
             }
         }
         if(count2>0 && count!=0){
         for(Country cAdj : g.adjVertices(cOrig)){
             double distance = distance(cOrig.getLatitude(), cOrig.getLongitude(), cAdj.getLatitude(), cAdj.getLongitude());
             if(distance<minDist && visited[g.getKey(cAdj)]==false){
                 minDist = distance;
                 nextCountry = cAdj;
             }
         }
         visited[g.getKey(nextCountry)] = true;
         maiorCircuito.add(nextCountry);        
         return findMaiorCircuito(nextCountry, visited, maiorCircuito);         
         } else{
             return maiorCircuito;
         } 
     }
     private static LinkedList<Country> encontrarUltimaFronteira(LinkedList<Country> maiorCircuitoAux, Country cOrig) {
         if(maiorCircuitoAux.size()>0){
         Country aux = maiorCircuitoAux.getLast();
        if(isFronteira(cOrig, aux)){
            return maiorCircuitoAux;
        } else{
            maiorCircuitoAux.remove(maiorCircuitoAux.getLast());
            return encontrarUltimaFronteira(maiorCircuitoAux, cOrig);
        }
         }
         return null;
    }
     
     
     private static boolean isFronteira(Country cOrig, Country cUlt){
         Iterable<Country> fronteiras =  g.adjVertices(cUlt);
         for(Country c : fronteiras){
             if(c.equals(cOrig)){
                 return true;
             }
         }
         return false;
     }
     
      // Utils
    
     
    public static Country getCountryByCapital(String capital1) {
        for (Country c : g.vertices()){
            
            if(c.getCapital().equalsIgnoreCase(capital1)){
                return c;
            }
        }
        return null;
    }
    public static Country getCountryByKey(int key){
        for (Country c : g.vertices()){
            if(g.getKey(c)==key){
                return c;
            }
        }
        return null;
    }
    public static double distance(double lat1, double lon1, double lat2, double lon2) {    
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            final double R = 6371e3;
            double x = lat2 - lat1;
            double y = lon2 - lon1;
            double theta1 = Math.toRadians(lat1);
            double theta2 = Math.toRadians(lat2);
            double deltaTheta = Math.toRadians(x);
            double deltaLambda = Math.toRadians(y);
            double a = Math.sin(deltaTheta / 2) * Math.sin(deltaTheta / 2) + Math.cos(theta1) * Math.cos(theta2) * Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double d = R * c * Math.pow(10, -3);
            return d;
        }
    }
     
   
   public  Country getCountryByName(String nome){
        for(Country pais: g.allkeyVerts()){
            if(pais.getNome().equals(nome)){
                return pais;
            }
        }
        return null;
    }
   
   private static Country getCountry(String name, Set<Country> scountries) {
        for (Country c : scountries) {
            if (c.getNome().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }
}
