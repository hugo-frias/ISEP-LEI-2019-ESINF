/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectClasses;

import java.util.Objects;

/**
 *
 * @author Hugo
 */
public class Country implements Comparable<Country>{
    private String name;
    private String continent;
    private double population;
    private String capital;
    private double latitude;
    private double longitude;
   

    public Country(String nome, String continente, double populacao, String capital, double latitude, double longitude) {
        this.name = nome;
        this.continent = continente;
        this.population = populacao;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
        
    }

    public Country(double latitude, double longitude) {
        this.name = "";
        this.continent = "";
        this.population = 0;
        this.capital = "";
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNome() {
        return name;
    }
    public String getContinente() {
        return continent;
    }
    public double getPopulacao() {
        return population;
    }
    public String getCapital() {
        return capital;
    }
    public double getLatitude() {
        return latitude;
    }
   
    public double getLongitude() {
        return longitude;
    }
    
       public void setNome(String nome) {
        this.name = nome;
    }
    public void setContinente(String continente) {
        this.continent = continente;
    }
    public void setCapital(String capital) {
        this.capital = capital;
    }
    public void setPopulacao(double populacao) {
        this.population = populacao;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.continent);
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.population) ^ (Double.doubleToLongBits(this.population) >>> 32));
        hash = 29 * hash + Objects.hashCode(this.capital);
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
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
        final Country other = (Country) obj;
        if (Double.doubleToLongBits(this.population) != Double.doubleToLongBits(other.population)) {
            return false;
        }
        if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude)) {
            return false;
        }
        if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.continent, other.continent)) {
            return false;
        }
        if (!Objects.equals(this.capital, other.capital)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Country{" + "name=" + name + ", continent=" + continent + ", population=" + population + ", capital=" + capital + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }    
   

    @Override
    public int compareTo(Country t) {
        return(name.compareTo(t.getNome()));
    }
    
}
