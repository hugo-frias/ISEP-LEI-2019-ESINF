/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esinf.de;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Vera Pinto
 */
public class CountBorders {
    
    public static Map<Integer, Set<Country>> countBorders (Map<Country,Set<Country>> m){
        sortByNumberOfBorders sort=new sortByNumberOfBorders();
        Map <Integer, Set<Country>> bordersMap= new HashMap<>();
         
        int maxFronteiras =0;
        for(int i =0; i<m.size();i++){
            for(Country c : m.keySet()){
                Set<Country> s = m.get(c);
                if(s.size()>maxFronteiras) maxFronteiras=s.size();
            }
        }
        for(int j=0; j<=maxFronteiras; j++){
            Set<Country> sAux = new HashSet<>();
            for(Country c: m.keySet()){
                if(m.get(c).size()==j){
                    sAux.add(c);
                }
            }
            bordersMap.put(j,sAux);
        }
          TreeMap<Integer, Set<Country>> sortedBorders = new TreeMap<Integer, Set<Country>>(sort);
        sortedBorders.putAll(bordersMap);
        return sortedBorders;
    }
}
