/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esinf.de;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Vera Pinto
 */
public class LoadFiles {
    //Exercicio 1
    public static void loadPaises(Map<Country, Set<Country>> m) throws FileNotFoundException, IOException {
         Set<Country> scountries = new HashSet<>();
        File directory = new File("./");
        String path = directory.getAbsolutePath();
        path = path.substring(0, path.length() - 1) + "\\src\\main\\java\\com\\mycompany\\esinf\\de\\resources\\";
        String fileNamePais = path + "paises2.txt";
        List<String> list = Files.lines(Paths.get(fileNamePais)).collect(Collectors.toList());
        for (String list1 : list) {
            if (list1.length() > 0) {
                String temp[] = list1.split(",");
                Country country = new Country(temp[0].trim(), temp[1].trim(), Double.parseDouble(temp[2]), temp[3].trim(),
                        Double.parseDouble(temp[4]), Double.parseDouble(temp[5]));
                scountries.add(country);
            }
        }
        for (String list2 : list) {
            if (list2.length() > 0) {
                String temp[] = list2.split(",");
                Country auxCountry = new Country(temp[0].trim(), temp[1].trim(), Double.parseDouble(temp[2]), temp[3].trim(),
                        Double.parseDouble(temp[4]), Double.parseDouble(temp[5]));
                Set<Country> sc = loadFronteiras(path, auxCountry, scountries);
                m.put(auxCountry, sc);
            }
        }
    }

    public static Set<Country> loadFronteiras(String path, Country country, Set<Country> scountries) throws FileNotFoundException, IOException {
        //String fileNameFronteira = path +"fronteiras.txt";
        String fileNameFronteira = path +"fronteiras2.txt";
        List<String> list = Files.lines(Paths.get(fileNameFronteira)).collect(Collectors.toList());
        Set<Country> sc = new HashSet<>();
        for (String list1 : list) {
            if (list1.length() > 0) {
                String temp2[] = list1.split(",");
                if (temp2[0].trim().equals(country.getNome())) {
                    for (Country c : scountries) {
                        if (c.getNome().equalsIgnoreCase(temp2[1].trim())) {
                            sc.add(c);
                        }
                    }
                } else if (temp2[1].trim().equals(country.getNome())) {
                    for (Country c : scountries) {
                        if (c.getNome().equalsIgnoreCase(temp2[0].trim())) {
                            sc.add(c);
                        }
                    }
                }
            }
        }
        return sc;
    }
}