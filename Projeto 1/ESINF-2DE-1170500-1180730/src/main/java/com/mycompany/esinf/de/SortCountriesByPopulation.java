/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esinf.de;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Vera Pinto
 */
public class SortCountriesByPopulation {
    //Exercício 2
    public static List ListCountries(Map<Country, Set<Country>> m, String continente, double populacao) {
        List<Country> countries = new ArrayList<>();
        for (Country c : m.keySet()) {
            if (c.getContinente().equals(continente) && c.getPopulacao() >= populacao) {
                countries.add(c);
            }
        }
        Collections.sort(countries, new sortByPopulation());
        return countries;
    }
}
