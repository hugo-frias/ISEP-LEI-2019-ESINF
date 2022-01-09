/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esinf.de;

import java.util.Comparator;

/**
 *
 * @author Hugo
 */
public class sortByPopulation implements Comparator<Country>{
    //Comparator para o exercício 2
    @Override
    public int compare(Country a, Country b) {
        return ((int)a.getPopulacao()-(int)b.getPopulacao());
    }
    
}
