/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectClasses;

import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author Hugo
 */
public class Pair  implements Comparable<Pair>{
    private LinkedList countryBorders;
    private int numBorders;
    private Country country;

    public Pair(LinkedList paisesFronteira, int numFronteiras, Country country) {
        this.countryBorders = paisesFronteira;
        this.numBorders = numFronteiras;
        this.country = country;
    }

   public Pair(Country c) {
        countryBorders = new LinkedList();
        numBorders = 0;
        country = c;
    }

    public Pair() {
        countryBorders = new LinkedList();
        numBorders = 0;
        country = null;
    }
    

    public LinkedList getPaisesFronteira() {
        return countryBorders;
    }

    public void setPaisesFronteira(LinkedList paisesFronteira) {
        this.countryBorders = paisesFronteira;
    }

    public int getNumFronteiras() {
        return numBorders;
    }

    public void setNumFronteiras(int numFronteiras) {
        this.numBorders = numFronteiras;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
    
    public void addFronteira(Country fronteira){
        countryBorders.add(fronteira);
        numBorders++;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.countryBorders);
        hash = 97 * hash + this.numBorders;
        hash = 97 * hash + Objects.hashCode(this.country);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        if (this.numBorders != other.numBorders) {
            return false;
        }
        if (!Objects.equals(this.countryBorders, other.countryBorders)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pair{" + "countryBorders=" + countryBorders + ", numBorders=" + numBorders + ", country=" + country + '}';
    }
   

    @Override
    public int compareTo(Pair b) {
        return(country.getNome().compareTo(b.getCountry().getNome()));
    }

    
    
}
