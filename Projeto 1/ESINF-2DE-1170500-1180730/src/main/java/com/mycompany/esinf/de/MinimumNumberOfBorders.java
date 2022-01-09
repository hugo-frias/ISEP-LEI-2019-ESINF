/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esinf.de;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Vera Pinto
 */
public class MinimumNumberOfBorders {
    //Exercício 4
          public static int numFronteiras(List<Country> paisesPassados, Map<Country, Set<Country>> m, Country paisOrigem, Country paisDestino, int numFronteiras) {

        if (distance(paisOrigem.getLatitude(), paisOrigem.getLongitude(), paisDestino.getLatitude(), paisDestino.getLongitude()) == 0) {
            return numFronteiras;
        }
        if (!paisOrigem.getContinente().equals(paisDestino.getContinente())) {
            return -1;
        }
        if (m.get(paisOrigem) == null || m.get(paisDestino) == null) {
            return -1;
        }
        double distanciaAux = distance(paisOrigem.getLatitude(), paisOrigem.getLongitude(), paisDestino.getLatitude(), paisDestino.getLongitude());
        Country aux = null;
        Set<Country> fronteiras = m.get(paisOrigem);
        for (Country fronteira : fronteiras) {
            double distancia = distance(fronteira.getLatitude(), fronteira.getLongitude(), paisDestino.getLatitude(), paisDestino.getLongitude());
            if (distancia < distanciaAux && !paisesPassados.contains(fronteira)) {
                distanciaAux = distancia;
                aux = fronteira;
            }
        }
        numFronteiras++;
        paisesPassados.add(aux);
        return numFronteiras(paisesPassados, m, aux, paisDestino, numFronteiras);

    }

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            return (dist);
        }
    }

}