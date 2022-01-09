/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esinf.de;

import java.util.Comparator;

/**
 *
 * @author Vera Pinto
 */
public class sortByNumberOfBorders implements Comparator<Integer>{

    @Override
    public int compare(Integer a, Integer b) {
        return -(a-b);
    }
}
    

