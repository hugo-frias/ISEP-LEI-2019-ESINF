/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BSTBase;

import ProjectClasses.Country;
import ProjectClasses.Pair;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Vera Pinto
 */
public class TreeAlgorithms {
    private static BST tree;
    private static TwoDTree tdTree;
    
    public TreeAlgorithms(){
        tree = new BST();
        tdTree = new TwoDTree();
    }

 public static BST getTree(){
        return tree;
    }

    public static TwoDTree getTdTree() {
        return tdTree;
    }
    
    
     public BST loadFicheiros(String fileNameCountry, String fileNameBorders) throws FileNotFoundException, IOException {
 
        
        File directory = new File("./");
        String path = directory.getAbsolutePath();
        path = path.substring(0, path.length() - 1) + "\\src\\main\\java\\resources\\";
        String fileNamePais = path + fileNameCountry+".txt";
        List<String> list = Files.lines(Paths.get(fileNamePais)).collect(Collectors.toList());
        for (String list1 : list) {
            if (list1.length() > 0) {
                String temp[] = list1.split(",");
                Country country = new Country(temp[0].trim(), temp[1].trim(), Double.parseDouble(temp[2]), temp[3].trim(),
                        Double.parseDouble(temp[4]), Double.parseDouble(temp[5]));                
                //scountries.add(country);
                Pair associacao = new Pair(country);
                tree.insert(associacao);
                
            }
        }

        String fileNameFronteira = path + fileNameBorders+".txt";
        List<String> listF = Files.lines(Paths.get(fileNameFronteira)).collect(Collectors.toList());
        for (String list2 : listF) {
            if (list2.length() > 0) {
                String temp2[] = list2.split(",");
                addFronteiras(temp2[0].trim(), temp2[1].trim());
            }
        }
        return tree;
    }
    
     public LinkedList listOfBorders(String nome){
         Country c1 = getCountryByName(nome);
         return getBordersByCountry(c1);
     }
     
     public Iterable OrderedCountries(String continent){
         LinkedList<Pair> associationsFromContinent = getAssociationsFromContinent(continent);
         BSTB tree2 = new BSTB();
         for(Pair a : associationsFromContinent){
             tree2.insert(a);
         }
         Iterable orderedList = tree2.inOrder();
         
         return orderedList;
     }
    
    private static void addFronteiras(String namePais1, String namePais2){
        Pair a1 = null;
        Pair a2 = null;
        Country c1=null, c2=null;
        for(Object o : tree.inOrder()){
            Pair a = (Pair) o;
            if(a.getCountry().getNome().equalsIgnoreCase(namePais1)){
                a1 = a;
                c1 = a.getCountry();
            }
            if(a.getCountry().getNome().equalsIgnoreCase(namePais2)){
                a2 = a;
                c2 = a.getCountry();
            }
        }
        a1.addFronteira(c2);
        a2.addFronteira(c1);
        
    }
    private LinkedList getBordersByCountry(Country c1) {
        for(Object o : tree.inOrder()){
            Pair a = (Pair) o;
            if(a.getCountry()==c1){
                return a.getPaisesFronteira();
            }
        }
        return null;
    }
    
    
    private static Country getCountryByName(String name) {
        for(Object o : tree.inOrder()){
            Pair a = (Pair) o;
            if(a.getCountry().getNome().equalsIgnoreCase(name)){
                return a.getCountry();
            }
        }
        return null;
    }

    private LinkedList getAssociationsFromContinent(String continent) {
        LinkedList Associations = new LinkedList<>();
        for(Object o : tree.inOrder()){
            Pair a = (Pair) o;
            if(a.getCountry().getContinente().equalsIgnoreCase(continent)){
                Associations.add(a);
            }
        }
        return Associations;
    }
    
    public  TwoDTree loadTDTree(){
        LinkedList<Pair> associations = getAllAssociations();
        for(Pair c : associations){
            tdTree.insert(c);
        }
        return tdTree;
    }
    
    private LinkedList getAllAssociations(){
        LinkedList<Pair> Associations = new LinkedList<>();
        for(Object o : tree.inOrder()){
            Pair a = (Pair) o;
            Associations.add(a);
            
        }
        return Associations;
    }
    public Country findCountryByCoordenates(double lat, double log){
        Node a = tdTree.findCountry(lat, log);
        if (a == null){
            return null;
        }
        Pair b = (Pair) a.getElement();
        return b.getCountry();
    }
    public Country findNearestCountry(double lat, double log){
        Country closestCountry = tdTree.findNearest(lat, log);
        return closestCountry;
    }
    public LinkedList<Country> findCountriesByArea(double latMax,double latMin, double logMax, double logMin){
        return tdTree.getCountriesByArea(latMax, latMin, logMax, logMin);
    }
   
}