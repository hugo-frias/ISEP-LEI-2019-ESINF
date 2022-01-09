/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esinf.de;

import java.util.Objects;

/**
 *
 * @author Hugo
 */
public class Country {
    private String nome;
    private String continente;
    private double populacao;
    private String capital;
    private double latitude;
    private double longitude;

    public Country(String nome, String continente, double populacao, String capital, double latitude, double longitude) {
        this.nome = nome;
        this.continente = continente;
        this.populacao = populacao;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNome() {
        return nome;
    }
    public String getContinente() {
        return continente;
    }
    public double getPopulacao() {
        return populacao;
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
        this.nome = nome;
    }
    public void setContinente(String continente) {
        this.continente = continente;
    }
    public void setCapital(String capital) {
        this.capital = capital;
    }
    public void setPopulacao(double populacao) {
        this.populacao = populacao;
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
        hash = 47 * hash + Objects.hashCode(this.nome);
        hash = 47 * hash + Objects.hashCode(this.continente);
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.populacao) ^ (Double.doubleToLongBits(this.populacao) >>> 32));
        hash = 47 * hash + Objects.hashCode(this.capital);
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
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
        if (this.populacao != other.populacao) {
            return false;
        }
        if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude)) {
            return false;
        }
        if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.continente, other.continente)) {
            return false;
        }
        if (!Objects.equals(this.capital, other.capital)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{" + "\nNome: " + nome + "\nContinente: " + continente + "\nPopulação: " + populacao + "\nCapital: " + capital + "\nLatitude: " + latitude + "\nLongitude: " + longitude+ '}' +"\n";
    }
    
}
